package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.exceptions.*;
import jakarta.validation.Valid;

import java.util.List;

public interface InterfaceApprovision {

    ApprovisionDTO enregistrerApprovision(ApprovisionDTO approvisionDTO) throws ApprovisionDupicateException, EmptyException;
    void traiterApprovision(String idApprov) throws ApprovisionDupicateException, EmptyException, MontantQuantiteNullException, ProduitDupicateException, ApprovNotFoundException;

    ApprovisionDTO modifierApprov(ApprovisionDTO approvisionDTO) throws ApprovNotFoundException;
    ApprovisionDTO afficher(String idApprov) throws ApprovNotFoundException;

    List<ApprovisionDTO> listerApprovision();

    void supprimerApprov(@Valid String idApprov) throws DetteNotFoundException;
}
