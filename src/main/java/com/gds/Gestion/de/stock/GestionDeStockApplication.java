package com.gds.Gestion.de.stock;
import com.gds.Gestion.de.stock.entites.UserRole;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.enums.TypeActive;
import com.gds.Gestion.de.stock.enums.TypeAuth;
import com.gds.Gestion.de.stock.enums.TypeRole;
import com.gds.Gestion.de.stock.repositories.UserRoleRepository;
import com.gds.Gestion.de.stock.repositories.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class GestionDeStockApplication implements CommandLineRunner{

	UtilisateurRepository utilisateurRepository;
	PasswordEncoder passwordEncoder;
	UserRoleRepository userRoleRepository;

	public static void main(String[] args) {
		SpringApplication.run(GestionDeStockApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// Vérification et création des rôles
		List<UserRole> roles = List.of(
				UserRole.builder().name(TypeRole.SUPER_ADMIN).build(),
				UserRole.builder().name(TypeRole.ADMIN).build(),
				UserRole.builder().name(TypeRole.USER).build()
		);

		for (UserRole role : roles) {
			if (userRoleRepository.findByName(role.getName()) == null) {
				userRoleRepository.save(role);
			}
		}

		// Vérification si l'utilisateur SUPER_ADMIN existe déjà
		String emailSuperAdmin = "super@gmail.com";
		if (utilisateurRepository.findByEmail(emailSuperAdmin) == null) {
			// Création de l'utilisateur SUPER_ADMIN
			Utilisateur superAdmin = Utilisateur.builder()
					.nom("Super")
					.prenom("Admin")
					.email(emailSuperAdmin)
					.password(passwordEncoder.encode("sogoredev"))
					.telephone("12345678")
					.date(LocalDate.now())
					.authentification(TypeAuth.FALSE)
					.activation(TypeActive.ACTIVER)
					.supprimerStatus(SupprimerStatus.FALSE)
					.roles(Collections.singletonList(userRoleRepository.findByName(TypeRole.SUPER_ADMIN)))
					.build();

			utilisateurRepository.save(superAdmin);
			System.out.println("Utilisateur SUPER_ADMIN créé avec succès.");
		} else {
			System.out.println("Utilisateur SUPER_ADMIN existe déjà.");
		}
	}

}
