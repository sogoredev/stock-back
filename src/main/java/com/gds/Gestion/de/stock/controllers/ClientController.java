package com.gds.Gestion.de.stock.controllers;


import com.gds.Gestion.de.stock.DTOs.ClientDTO;
import com.gds.Gestion.de.stock.exceptions.ClientDupicateException;
import com.gds.Gestion.de.stock.exceptions.ClientNotFoundException;
import com.gds.Gestion.de.stock.exceptions.EmailIncorrectException;
import com.gds.Gestion.de.stock.services.InterfaceClient;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private InterfaceClient interfaceClient;

    @PostMapping("/ajouterClient")
    private ClientDTO ajouterClient(@Valid @RequestBody ClientDTO clientDTO) throws ClientDupicateException, EmailIncorrectException {
        return interfaceClient.ajouterClient(clientDTO);
    }

    @PutMapping("/modifierClient/{idClient}")
    private ClientDTO modifierClient(@Valid @RequestBody ClientDTO clientDTO, @PathVariable("idClient") String idClient) throws ClientNotFoundException {
        clientDTO.setIdClient(idClient);
        return interfaceClient.modifierClient(clientDTO);
    }

    @GetMapping("/afficherClient/{idClient}")
    public ClientDTO afficherClient(@Valid @PathVariable("idClient") String idClient) throws ClientNotFoundException {
        return interfaceClient.afficher(idClient);
    }

    @GetMapping("/totalClient")
    private long totalClient() {
        return interfaceClient.totalClient();
    }

    @GetMapping("/listeClient")
    private List<ClientDTO> listClient() {
        return interfaceClient.listClient();
    }

    @PutMapping("/supprimerClient")
    private void supprimerClient(@Valid @RequestBody String idClient) throws ClientNotFoundException {
        interfaceClient.supprimer(idClient);
    }
}
