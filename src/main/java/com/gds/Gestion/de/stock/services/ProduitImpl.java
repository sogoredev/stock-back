package com.gds.Gestion.de.stock.services;


import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.entites.Produit;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.mappers.ProduitMapper;
import com.gds.Gestion.de.stock.repositories.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class ProduitImpl implements InterfaceProduit {

    private ProduitRepository produitRepository;
    private ProduitMapper produitMapper;


    @Override
    public ProduitDTO enregistrerProd(ProduitDTO produitDTO) throws MontantQuantiteNullException, ProduitDupicateException, EmptyException {
        Produit produit = produitMapper.mapDeDtoAProd(produitDTO);

        if (produitDTO.getPrixUnitaire() <= 0 || produitDTO.getQuantite() <= 0)
            throw new MontantQuantiteNullException(produitDTO.getPrixUnitaire()+" ou"+produitDTO.getQuantite() +"doivent etre superieur a zero");

        int montant = produitDTO.getPrixUnitaire() * produitDTO.getQuantite();
        produit.setMontant(montant);
        produit.setDate(LocalDate.now());
        Utilisateur userConnecter = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        produit.setUtilisateurProd(userConnecter);
        produit.setIdProd("GDS "+UUID.randomUUID());
        produit.setDate(LocalDate.now());
        produit.setSupprimerStatus(SupprimerStatus.FALSE);
        return produitMapper.mapDeProdADto(produitRepository.save(produit));
    }

    @Override
    public ProduitDTO enregistrerVenteProd(ProduitDTO produitDTO) {
        Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
        Utilisateur userConnecter = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        produit.setUtilisateurProd(userConnecter);
        return produitMapper.mapDeProdADto(produitRepository.save(produit));
    }

    @Override
    public ProduitDTO enregistrerDetteProd(ProduitDTO produitDTO) {
        Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
        Utilisateur userConnecter = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        produit.setUtilisateurProd(userConnecter);
        return produitMapper.mapDeProdADto(produitRepository.save(produit));
    }

    @Override
    public ProduitDTO modifierProd(ProduitDTO produitDTO) throws EmptyException {

        Produit produitExist = produitRepository.findById(produitDTO.getIdProd())
                .orElseThrow(()-> new EmptyException("Cet produits n'existe pas"));

        Produit produit = produitMapper.mapDeDtoAProd(produitDTO);

        produit.setIdProd(produitExist.getIdProd());
        produit.setUtilisateurProd(produitExist.getUtilisateurProd());
        int montant = produitDTO.getPrixUnitaire() * produitDTO.getQuantite();
        produit.setMontant(montant);
        produit.setDate(produitExist.getDate());
        produit.setSupprimerStatus(SupprimerStatus.FALSE);
        return produitMapper.mapDeProdADto(produitRepository.save(produit));
    }

    @Override
    public void suppressionProd(String idProd) throws ProduitNotFoundException {
        Produit produit = produitRepository.findById(idProd)
                .orElseThrow(() -> new ProduitNotFoundException("Cet produit n'existe pas"));
        if (produit.getSupprimerStatus() == SupprimerStatus.TRUE)
            throw new ProduitNotFoundException("Ce produit est supprimer ! ");
        produit.setSupprimerStatus(SupprimerStatus.TRUE);
        produitRepository.save(produit);

    }

    @Override
    public ProduitDTO afficherProd(String idStock) throws ProduitNotFoundException {
       return produitMapper.mapDeProdADto(produitRepository.findById(idStock).orElseThrow(()->
               new ProduitNotFoundException(" Cet stock n'existe pas")));

    }

    @Override
    public List<ProduitDTO> ListerProd() {
        List<Produit> produits = produitRepository.findAllBySupprimerStatusFalse();
        return produits.stream().map(produit -> produitMapper.mapDeProdADto(produit))
                .collect(Collectors.toList());
    }
}
