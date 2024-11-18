package com.gds.Gestion.de.stock.mappers;


import com.gds.Gestion.de.stock.DTOs.UtilisateurDTO;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UtilisateurMapper {

    public UtilisateurDTO mapDeUserADto(Utilisateur utilisateur){
        UtilisateurDTO utilisateurDTO = new UtilisateurDTO();
        BeanUtils.copyProperties(utilisateur, utilisateurDTO);
//        utilisateurDTO.setRoles(utilisateur.getRolesUser());
        return utilisateurDTO;
    }

    public Utilisateur mapDeDtoAUser(UtilisateurDTO utilisateurDTO){
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(utilisateurDTO, utilisateur);
//        utilisateur.setRolesUser(utilisateurDTO.setRoles());
        return utilisateur;
    }


}
