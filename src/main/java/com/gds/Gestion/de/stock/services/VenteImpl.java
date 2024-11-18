package com.gds.Gestion.de.stock.services;


import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.DTOs.VenteDTO;
import com.gds.Gestion.de.stock.entites.Produit;
import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.entites.Vente;
import com.gds.Gestion.de.stock.entites.VenteProduit;
import com.gds.Gestion.de.stock.enums.StatusVente;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.mappers.ClientMapper;
import com.gds.Gestion.de.stock.mappers.ProduitMapper;
import com.gds.Gestion.de.stock.mappers.VenteMapper;
import com.gds.Gestion.de.stock.repositories.ProduitRepository;
import com.gds.Gestion.de.stock.repositories.VenteProduitRepository;
import com.gds.Gestion.de.stock.repositories.VenteRepository;
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
@Slf4j
@AllArgsConstructor
public class VenteImpl implements InterfaceVente {


    private final ClientMapper clientMapper;
    private VenteMapper venteMapper;

    private VenteRepository venteRepository;

    private VenteProduitRepository venteProduitRepository;

    private ProduitRepository produitRepository;

    private ProduitMapper produitMapper;

    //    ajouter une vente
    @Override
    public void effectuerVente(VenteDTO venteDTO) throws Exception {

        // Validation des entrées
        if (venteDTO.getProduitsVend().isEmpty()) {
            throw new EmptyException("Sélectionner un produit !");
        }

        if (venteDTO.getClientDTO() == null) {
            throw new EmptyException("Sélectionner un client !");
        }

        // Création de la vente
        Vente vente = venteMapper.mapDeDtoAVente(venteDTO);
        Utilisateur userConnecter = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vente.setUtilisateurVente(userConnecter);
        vente.setStatus(StatusVente.TRAITER);
        vente.setSupprimerStatus(SupprimerStatus.FALSE);
        vente.setDateVente(LocalDate.now());
        vente.setIdVente("GDS" + UUID.randomUUID());

        // Initialiser le montant total et la quantité totale
        int montantTotal = 0;
        int quantiteTotale = 0;

        List<ProduitDTO> produitsVend = venteDTO.getProduitsVend();

        for (ProduitDTO produitDTO : produitsVend) {
            Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
            // Vérifier le stock
            if (produit.getQuantite() < venteDTO.getQuantite()) {
                throw new InsufficientStockException("Le stock de la quantité du produit est insuffisant ");
            }

            // Calcul du montant de produit
            int montantProduit = produit.getPrixUnitaire() * venteDTO.getQuantite();
            montantTotal += montantProduit;

            // Ajouter la quantité de ce produit à la quantité totale
            quantiteTotale += venteDTO.getQuantite();

            // Mise à jour des produits
            produit.setQuantite(produit.getQuantite() - venteDTO.getQuantite());
            produit.setMontant(produit.getMontant() - montantProduit);
            produitRepository.save(produit);
        }

        // Appliquer la réduction sur le montant total
        if (vente.getReduction() > (0.2 * montantTotal)) {
            throw new InsufficientStockException("La réduction ne peut pas dépasser 20 % du montant pour le produit ");
        }
        montantTotal -= venteDTO.getReduction();
        if (montantTotal < 0) {
            montantTotal = 0;
        }

        // Enregistrer la vente avec le montant et la quantité totale
        vente.setMontant(montantTotal);
        vente.setQuantite(quantiteTotale);
        Vente saveVente = venteRepository.save(vente);

        // Enregistrer les détails de la vente
        for (ProduitDTO produitDTO : produitsVend) {
            VenteProduit venteProduit = new VenteProduit();
            Produit produit = produitMapper.mapDeDtoAProd(produitDTO);
            venteProduit.setProduit(produit);
            venteProduit.setVente(saveVente);
            venteProduit.setMontant(produit.getPrixUnitaire() * venteDTO.getQuantite() - venteDTO.getReduction());
            venteProduit.setQuantite(venteDTO.getQuantite());
            venteProduitRepository.save(venteProduit);
        }
    }


    @Override
    public void annulerVente(VenteDTO venteDTO) throws EmptyException {

        Vente venteExist = venteRepository.findById(venteDTO.getIdVente())
                .orElseThrow(() -> new EmptyException("Cette vente n'existe pas"));

        // Vérification du statut
        if (venteExist.getStatus() == StatusVente.ANNULER) {
            throw new EmptyException("La vente est déjà annulée");
        }

        // Vérification de nullité avant l'itération sur les produits
        List<VenteProduit> venteProduitsList = venteProduitRepository.findVenteProduitsByVenteId(venteDTO.getIdVente());
        if (venteProduitsList == null || venteProduitsList.isEmpty()) {
            throw new EmptyException("La vente n'a pas de produits à annuler.");
        }

        // Mise à jour des informations de la vente
        venteDTO.setDateVente(venteExist.getDateVente());
        venteDTO.setSupprimerStatus(SupprimerStatus.FALSE);
        venteDTO.setIdVente(venteExist.getIdVente());
        venteDTO.setStatus(StatusVente.ANNULER);
        venteDTO.setClientDTO(clientMapper.mapDeClientADto(venteExist.getClientsVente()));
        venteRepository.save(venteMapper.mapDeDtoAVente(venteDTO));

        for (VenteProduit venteProduit : venteProduitsList) {
            Produit produit = venteProduit.getProduit();
            produit.setQuantite(produit.getQuantite() + venteProduit.getQuantite());
            produit.setMontant(produit.getMontant() + venteProduit.getMontant() + venteProduit.getReduction());
            produitRepository.save(produit);
        }
    }

    //  modifier une vente
    @Override
    public void modifierVente(VenteDTO venteDTO) throws EmptyException, InsufficientStockException, VenteNotFoundException {
        // Récupérer la vente à modifier
        Vente venteExist = venteRepository.findById(venteDTO.getIdVente())
                .orElseThrow(() -> new VenteNotFoundException("Cette vente n'existe pas"));
        Vente vente = venteMapper.mapDeDtoAVente(venteDTO);
        // Mise à jour des informations de la vente
        vente.setUtilisateurVente(venteExist.getUtilisateurVente());
        if(venteDTO.getStatus() == StatusVente.ANNULER){
            throw new VenteNotFoundException("Vente est deja annule");
        }
        vente.setStatus(StatusVente.TRAITER);
        vente.setDateVente(LocalDate.now());
        vente.setSupprimerStatus(SupprimerStatus.FALSE);
        vente.setIdVente(venteExist.getIdVente());
        vente.setClientsVente(venteExist.getClientsVente());
        venteRepository.save(vente);
    }


    //    recuperer une vente
    @Override
    public VenteDTO afficherVente(String idVente) throws VenteNotFoundException {
        return venteMapper.mapDeVenteADTO(venteRepository.findById(idVente).orElseThrow(() -> new VenteNotFoundException("Vente n'existe pas")));
    }

    //  recperer la liste des vente
    @Override
    public List<VenteDTO> listerVente() {
        List<Vente> allVente = venteRepository.findAllBySupprimerStatusFalse();
        return allVente.stream().map(vente -> venteMapper.mapDeVenteADTO(vente)).collect(Collectors.toList());
    }

    @Override
    public long totalVente() {
        return venteRepository.countTotalVente();
    }

    //    supprimer une vente
    @Override
    public void supprimerVente(VenteDTO venteDTO) throws VenteNotFoundException {

        Vente vente = venteMapper.mapDeDtoAVente(afficherVente(venteDTO.getIdVente()));

        if (vente == null)
            throw new VenteNotFoundException("Vente n'existe pas");

        vente.setSupprimerStatus(SupprimerStatus.TRUE);
        venteRepository.save(vente);
    }
}

//    SET FOREIGN_KEY_CHECKS=0;


