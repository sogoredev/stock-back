package com.gds.Gestion.de.stock.repositories;


import com.gds.Gestion.de.stock.entites.Client;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Utilisateur findByTelephoneOrEmail(String tel, String email);
    Utilisateur findByEmail(String email);
    @Query("SELECT u FROM Utilisateur u WHERE u.supprimerStatus = 'FALSE'")
    List<Utilisateur> findAllBySupprimerStatusFalse();
}
