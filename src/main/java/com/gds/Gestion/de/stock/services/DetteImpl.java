package com.gds.Gestion.de.stock.services;


import com.gds.Gestion.de.stock.DTOs.DetteDTO;
import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.entites.*;
import com.gds.Gestion.de.stock.enums.StatusDette;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.mappers.DetteMapper;
import com.gds.Gestion.de.stock.mappers.ProduitMapper;
import com.gds.Gestion.de.stock.repositories.DetteProduitRepository;
import com.gds.Gestion.de.stock.repositories.DetteRepository;
import com.gds.Gestion.de.stock.repositories.ProduitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class DetteImpl implements InterfaceDette {

   
    private DetteMapper detteMapper;
   
    private DetteRepository detteRepository;
   
    private ProduitMapper produitMapper;
   
    private ProduitRepository produitRepository;
    
    private DetteProduitRepository detteProduitRepository;

    @Override
    public void enregistrerDette(DetteDTO detteDTO) throws EmptyException {

        // Validation des entrées
        if (detteDTO.getProduitDTOS().isEmpty()) {
            throw new EmptyException("Sélectionner un produit !");
        }

        if (detteDTO.getClientDTO() == null) {
            throw new EmptyException("Sélectionner un client !");
        }

        Dette dette = detteMapper.mapDeDtoADette(detteDTO);

        Utilisateur principal = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        dette.setUtilisateurDette(principal);
        dette.setIdDette("GDS" + UUID.randomUUID());
        dette.setStatus(StatusDette.NON_PAYER);
        dette.setSupprimerStatus(SupprimerStatus.FALSE);

        // Initialiser le montant total et la quantité totale
        int montantTotal = 0;
        int quantiteTotale = 0;

        List<ProduitDTO> produitListDette = detteDTO.getProduitDTOS();

        for (ProduitDTO produitDTO : produitListDette) {
            Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
            // Vérifier le stock
            if (produit.getQuantite() < detteDTO.getQuantite()) {
                throw new InsufficientStockException("Le stock de la quantité du produit est insuffisant ");
            }

            // Calcul du montant de produit
            int montantProduit = produit.getPrixUnitaire() * detteDTO.getQuantite();
            montantTotal += montantProduit;

            // Ajouter la quantité de ce produit à la quantité totale
            quantiteTotale += detteDTO.getQuantite();

            // Mise à jour des produits
            produit.setQuantite(produit.getQuantite() - detteDTO.getQuantite());
            produit.setMontant(produit.getMontant() - montantProduit);
            produitRepository.save(produit);
        }

        // Appliquer la réduction sur le montant total
        if (dette.getReduction() > (0.2 * montantTotal)) {
            throw new InsufficientStockException("La réduction ne peut pas dépasser 20 % du montant pour le produit ");
        }
        montantTotal -= detteDTO.getReduction();
        if (montantTotal < 0) {
            montantTotal = 0;
        }

        // Enregistrer la vente avec le montant et la quantité totale
        dette.setMontant(montantTotal);
        dette.setQuantite(quantiteTotale);
        Dette saveDette = detteRepository.save(dette);

        // Enregistrer les détails de la vente
        for (ProduitDTO produitDTO : produitListDette) {
            DetteProduit DetteProduit = new DetteProduit();
            Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
            DetteProduit.setProduit(produit);
            DetteProduit.setDette(saveDette);
            DetteProduit.setMontant(produit.getPrixUnitaire() * detteDTO.getQuantite() - detteDTO.getReduction());
            DetteProduit.setQuantite(detteDTO.getQuantite());
            detteProduitRepository.save(DetteProduit);
        }
    }

    @Override
    public void paiementDette(String idDette) throws EmptyException, DetteNotFoundException {

        Dette dette = detteRepository.findById(idDette)
                .orElseThrow(()-> new DetteNotFoundException("Cet dette n'existe pas"));

        if (dette.getStatus() != StatusDette.NON_PAYER)
            throw new EmptyException("Dette est deja paye");

        dette.setStatus(StatusDette.PAYER);
        detteRepository.save(dette);
    }

    @Override
    public List<DetteDTO> listerDette() {
        List<Dette> dettesList = detteRepository.findAllBySupprimerStatusFalse();
        return dettesList.stream()
                .map(dette -> detteMapper.mapDeDetteADTO(dette))
                .collect(Collectors.toList());
    }

    @Override
    public void modifierDette(DetteDTO detteDTO) throws DetteNotFoundException, EmptyException {
        Dette detteExist = detteRepository.findById(detteDTO.getIdDette()).
                orElseThrow(()-> new DetteNotFoundException("Cet Dette n'existe pas"));

        Dette dette = detteMapper.mapDeDtoADette(detteDTO);
        dette.setIdDette(detteExist.getIdDette());
        dette.setStatus(detteExist.getStatus());
        dette.setDateDebut(detteExist.getDateDebut());
        dette.setDateFin(detteExist.getDateFin());
        dette.setClientDette(detteExist.getClientDette());
        dette.setUtilisateurDette(detteExist.getUtilisateurDette());
        dette.setSupprimerStatus(detteExist.getSupprimerStatus());
        detteRepository.save(dette);
    }

    @Override
    public DetteDTO afficher(String idDette) throws DetteNotFoundException {
        Dette dette = detteRepository.findById(idDette).
                orElseThrow(()-> new DetteNotFoundException("Cet dette n'existe pas"));
        return detteMapper.mapDeDetteADTO(dette);
    }

    @Override
    public void supprimerDette(String idDette) throws DetteNotFoundException {
        Dette dette = detteRepository.findById(idDette).
                orElseThrow(()-> new DetteNotFoundException("Cette dette n'existe pas"));
        if (dette.getStatus() == StatusDette.NON_PAYER)
            throw new DetteNotFoundException("Cette dette n'est pas paye. Vous ne pouvez pas la supprimer ! ");
        dette.setSupprimerStatus(SupprimerStatus.TRUE);
        detteRepository.save(dette);
    }
}

//    SET FOREIGN_KEY_CHECKS=0;


