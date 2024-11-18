package com.gds.Gestion.de.stock.DTOs;


import com.gds.Gestion.de.stock.entites.UserRole;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.enums.TypeActive;
import com.gds.Gestion.de.stock.enums.TypeAuth;
import com.gds.Gestion.de.stock.enums.TypeRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data @AllArgsConstructor @NoArgsConstructor
public class UtilisateurDTO {


    private Long id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private String password;
    private LocalDate date;
    private boolean authentification;
    private TypeActive activation;
    private SupprimerStatus supprimerStatus;
    private List<UserRole> roles;

}
