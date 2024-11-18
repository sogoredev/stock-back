package com.gds.Gestion.de.stock.controllers;


import com.gds.Gestion.de.stock.DTOs.UtilisateurDTO;
import com.gds.Gestion.de.stock.entites.UserRole;
import com.gds.Gestion.de.stock.exceptions.EmailIncorrectException;
import com.gds.Gestion.de.stock.exceptions.UtilisateurDuplicateException;
import com.gds.Gestion.de.stock.exceptions.EmptyException;
import com.gds.Gestion.de.stock.exceptions.UtilisateurNotFoundException;
import com.gds.Gestion.de.stock.repositories.UserRoleRepository;
import com.gds.Gestion.de.stock.services.UtilisateurImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/user")
public class UtilisateurController {

    @Autowired
    private UtilisateurImpl interfaceUtilisateur;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @PostMapping(value = "/creer", consumes = MediaType.APPLICATION_JSON_VALUE)
    private UtilisateurDTO creerUser(@Valid @RequestBody UtilisateurDTO utilisateurDTO) throws UtilisateurDuplicateException, EmptyException, EmailIncorrectException {
        return interfaceUtilisateur.creerUser(utilisateurDTO);
    }

    @PutMapping("/modifier/{idUser}")
    private void modifierUser(@Valid @RequestBody UtilisateurDTO utilisateurDTO, @PathVariable("idUser") Long idUser) throws UtilisateurNotFoundException {
        utilisateurDTO.setId(idUser);
         interfaceUtilisateur.modifierUser(utilisateurDTO);
    }

    @PutMapping("/supprimer")
    private void supprimerUser(@Valid @RequestBody Long idUser) throws UtilisateurNotFoundException {
         interfaceUtilisateur.supprimerUser(idUser);
    }

    @GetMapping("/user/{idUser}")
    private UtilisateurDTO afficherUserId(@Valid @PathVariable("idUser") Long idUser) throws UtilisateurNotFoundException {
        return interfaceUtilisateur.afficherUsersId(idUser);
    }

    @GetMapping(value = "/userListe",consumes = { "application/json", "application/xml" })
    private List<UtilisateurDTO> afficherUsers(){
        List<UtilisateurDTO> utilisateurDTOS = interfaceUtilisateur.afficherUsers();
        return utilisateurDTOS;
    }

    @GetMapping(value = "/roleListe",consumes = { "application/json", "application/xml" })
    private List<UserRole> afficherRole(){
        return userRoleRepository.findAll();
    }
}
