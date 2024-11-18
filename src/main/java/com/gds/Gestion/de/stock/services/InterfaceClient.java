package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.ClientDTO;
import com.gds.Gestion.de.stock.exceptions.ClientDupicateException;
import com.gds.Gestion.de.stock.exceptions.ClientNotFoundException;
import com.gds.Gestion.de.stock.exceptions.EmailIncorrectException;

import java.util.List;

public interface InterfaceClient {

    ClientDTO ajouterClient(ClientDTO clientDTO) throws ClientDupicateException, EmailIncorrectException;
    ClientDTO modifierClient(ClientDTO clientDTO) throws ClientNotFoundException;
    List<ClientDTO> listClient();
    long totalClient();
    ClientDTO afficher(String idClient) throws ClientNotFoundException;
    void supprimer(String idClient) throws ClientNotFoundException;
}
