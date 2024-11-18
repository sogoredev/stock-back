package com.gds.Gestion.de.stock.entites;

import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorieStock {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCat;
    @Column(length = 60)
    private String nom;
    @Column(length = 100)
    private String description;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private SupprimerStatus supprimerStatus;
    @ManyToOne
    private Utilisateur utilisateurCat;
}
