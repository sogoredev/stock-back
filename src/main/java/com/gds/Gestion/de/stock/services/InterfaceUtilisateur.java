package com.gds.Gestion.de.stock.services;

import com.gds.Gestion.de.stock.DTOs.UtilisateurDTO;
import com.gds.Gestion.de.stock.entites.UserRole;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.exceptions.UtilisateurDuplicateException;
import com.gds.Gestion.de.stock.exceptions.EmptyException;
import com.gds.Gestion.de.stock.exceptions.UtilisateurNotFoundException;

import java.util.List;

public interface InterfaceUtilisateur {

    UtilisateurDTO creerUser(UtilisateurDTO utilisateurDTO) throws UtilisateurDuplicateException, EmptyException;
    List<UtilisateurDTO> afficherUsers();
    UtilisateurDTO afficherUsersId(Long idUser) throws UtilisateurNotFoundException;
    void modifierUser(UtilisateurDTO utilisateurDTO) throws UtilisateurNotFoundException;
    void supprimerUser(Long idUser);

    UserRole addNewRole(String role) throws EmptyException;
    void addRoleToUser(String username, String role);
    void removeRoleToUser(String username, String role);
    Utilisateur loadUserByEmail(String email);

}
