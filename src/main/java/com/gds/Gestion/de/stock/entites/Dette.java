package com.gds.Gestion.de.stock.entites;

import com.gds.Gestion.de.stock.enums.StatusDette;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Dette{

    @Id
    private String idDette;
    @Column(length = 50)
    private String titre;
    @Column(length = 10)
    private int quantite;
    @Column(length = 50)
    private int reduction;
    @Column(length = 100)
    private int montant;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private StatusDette status;
    @Column(length = 100)
    private String note;
    @Enumerated(EnumType.STRING)
    private SupprimerStatus supprimerStatus;


    @ManyToOne
    private Utilisateur utilisateurDette;

    @ManyToOne
    private Client clientDette;

    /*@ManyToMany(fetch = FetchType.EAGER)
    private List<Produit> produitDette = new ArrayList<>();*/
}
