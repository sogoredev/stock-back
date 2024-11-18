package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.exceptions.*;

import java.util.List;

public interface InterfaceProduit {


    ProduitDTO enregistrerProd(ProduitDTO produitDTO) throws MontantQuantiteNullException, ProduitDupicateException, EmptyException;

    ProduitDTO enregistrerVenteProd(ProduitDTO produitDTO);

    ProduitDTO enregistrerDetteProd(ProduitDTO produitDTO);

    ProduitDTO modifierProd(ProduitDTO produitDTO) throws EmptyException;

    void suppressionProd(String idProd) throws ProduitNotFoundException;

    ProduitDTO afficherProd(String idProd) throws ProduitNotFoundException;

    List<ProduitDTO> ListerProd();

}
