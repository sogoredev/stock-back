package com.gds.Gestion.de.stock.DTOs;


import com.gds.Gestion.de.stock.entites.Produit;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.StatusVente;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;


@Data
public class VenteDTO {


    private String idVente;
    private String description;
    private int montant;
    private int quantite;
    private int reduction;
    private LocalDate dateVente;
    private String note;
    private List<ProduitDTO> produitsVend;
    private ClientDTO clientDTO;
    private Utilisateur utilisateurVente;
    private StatusVente status;
    private SupprimerStatus supprimerStatus;

}
