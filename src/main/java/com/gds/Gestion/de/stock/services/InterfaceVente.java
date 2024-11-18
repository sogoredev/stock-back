package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.DTOs.VenteDTO;
import com.gds.Gestion.de.stock.exceptions.EmptyException;
import com.gds.Gestion.de.stock.exceptions.VenteNotFoundException;

import java.util.List;

public interface InterfaceVente {

    void effectuerVente(VenteDTO venteDTO) throws Exception;
    void modifierVente(VenteDTO venteDTO) throws EmptyException, VenteNotFoundException;
    VenteDTO afficherVente(String idVente) throws VenteNotFoundException;
    List<VenteDTO> listerVente();
    long totalVente();
    void supprimerVente(VenteDTO venteDTO) throws VenteNotFoundException;

    void annulerVente(VenteDTO venteDTO) throws EmptyException;
}
