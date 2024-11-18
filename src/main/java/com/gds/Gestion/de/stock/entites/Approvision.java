package com.gds.Gestion.de.stock.entites;


import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.enums.Etat;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Approvision {

    @Id
    private String idApprov;
    @Column(length = 60)
    private String designation;
    @Column(length = 10)
    private int quantite;
    @Column(length = 100)
    private int prixUnitaire;
    @Column(length = 100)
    private int montant;
    @Column(length = 10)
    private double cbm;
    @Column(length = 100)
    private int fraisTransit;
    private LocalDate dateAchat;
    private LocalDate dateArriver;
    @Column(length = 60)
    private String adresseFrs;
    private String image;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    @Column(length = 100)
    private String description;
    @Enumerated(EnumType.STRING)
    private SupprimerStatus supprimerStatus;

    @ManyToOne
    private Produit produitsApprov;

    @ManyToOne
    private Utilisateur utilisateurAprov;

}
