package com.gds.Gestion.de.stock.DTOs;


import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.StatusVente;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class VenteProduitDTO {


    private Long idVenteProduit;
    private int montant;
    private int quantite;
    private int reduction;

}
