package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.DetteDTO;
import com.gds.Gestion.de.stock.DTOs.VenteDTO;
import com.gds.Gestion.de.stock.exceptions.*;
import jakarta.validation.Valid;

import java.util.List;

public interface InterfaceDette {

    void enregistrerDette(DetteDTO detteDTO) throws DetteDuplicateException, EmptyException;

    void paiementDette(String idDette) throws EmptyException, DetteNotFoundException;

    List<DetteDTO> listerDette();

    void modifierDette(DetteDTO detteDTO) throws ApprovNotFoundException, DetteNotFoundException, EmptyException;

    DetteDTO afficher(String idDette) throws DetteNotFoundException;

    void supprimerDette(@Valid String idDette) throws DetteNotFoundException;
}
