package com.gds.Gestion.de.stock.DTOs;


import lombok.Data;


@Data
public class DetteProduitDTO {


    private Long idDetteProduit;
    private int montant;
    private int quantite;
    private int reduction;

}
