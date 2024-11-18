package com.gds.Gestion.de.stock.services;


import com.gds.Gestion.de.stock.DTOs.ClientDTO;
import com.gds.Gestion.de.stock.entites.Client;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.exceptions.ClientDupicateException;
import com.gds.Gestion.de.stock.exceptions.ClientNotFoundException;
import com.gds.Gestion.de.stock.exceptions.EmailIncorrectException;
import com.gds.Gestion.de.stock.mappers.ClientMapper;
import com.gds.Gestion.de.stock.repositories.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ClientImpl implements InterfaceClient {


    private ClientMapper clientMapper;

    private ClientRepository clientRepository;


    @Override
    public ClientDTO ajouterClient(ClientDTO clientDTO) throws ClientDupicateException {
        Client client = clientMapper.mapDeDtoAClient(clientDTO);
        Client clientByTelephone = clientRepository.findByTelephone(clientDTO.getTelephone() );

        if(clientByTelephone != null)
            throw new ClientDupicateException(clientByTelephone.getTelephone()+" existe deja.");

    /*    if (!client.getEmail().contains("@") && !client.getEmail().contains("."))
            throw new EmailIncorrectException("Votre email "+ clientDTO.getEmail()+" est incorrect");*/

        Utilisateur userConnecter = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        client.setUtilisateurClient(userConnecter);
        client.setDateAjout(LocalDate.now());
        client.setIdClient("GDS"+UUID.randomUUID());
        client.setSupprimerStatus(SupprimerStatus.FALSE);
        Client save = clientRepository.save(client);
        return clientMapper.mapDeClientADto(save);
    }

    @Override
    public ClientDTO modifierClient(ClientDTO clientDTO) throws ClientNotFoundException {
        Client clientExist = clientRepository.findById(clientDTO.getIdClient())
                .orElseThrow(() -> new ClientNotFoundException("Cet client n'existe pas"));
        Client client = clientMapper.mapDeDtoAClient(clientDTO);
        client.setIdClient(clientExist.getIdClient());
        client.setDateAjout(clientExist.getDateAjout());
        client.setSupprimerStatus(SupprimerStatus.FALSE);
        client.setUtilisateurClient(clientExist.getUtilisateurClient());
        return clientMapper.mapDeClientADto(clientRepository.save(client));
    }

    @Override
    public List<ClientDTO> listClient() {
        List<Client> clientList = clientRepository.findAllBySupprimerStatusFalse();
        return clientList.stream()
                .map(client -> clientMapper.mapDeClientADto(client))
                .collect(Collectors.toList());
    }

    @Override
    public long totalClient() {
        return clientRepository.countTotalClient();
    }

    @Override
    public ClientDTO afficher(String idClient) throws ClientNotFoundException {
        System.out.println(idClient+"client 2");
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ClientNotFoundException("Cet client n'existe pas "));
        System.out.println(client+"client");
        return clientMapper.mapDeClientADto(client);

    }

    @Override
    public void supprimer(String idClient) throws ClientNotFoundException {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ClientNotFoundException("Cet client n'existe pas "));
        client.setSupprimerStatus(SupprimerStatus.TRUE);
        clientRepository.save(client);
    }
}
