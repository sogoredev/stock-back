package com.gds.Gestion.de.stock.DTOs;


import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import lombok.Data;

import java.time.LocalDate;


@Data
public class CategorieStockDTO {


    private Long idCat;
    private String nom;
    private String description;
    private LocalDate date;
    private SupprimerStatus supprimerStatus;
}
