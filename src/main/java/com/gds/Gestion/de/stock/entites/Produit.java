package com.gds.Gestion.de.stock.entites;

import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produit {

    @Id
    private String idProd;
    @Column(length = 60)
    private String designation;
    @Column(length = 10)
    private int quantite;
    @Column(length = 100)
    private int prixUnitaire;
    @Column(length = 100)
    private int montant;
    private String image;
    private LocalDate date;
    @Column(length = 255)
    private String note;
    @Enumerated(EnumType.STRING)
    private SupprimerStatus supprimerStatus;

//    SET FOREIGN_KEY_CHECKS=0;

    @ManyToOne
    private Utilisateur utilisateurProd;

    @ManyToOne
    private CategorieStock categorieStock;
}
