package com.gds.Gestion.de.stock.controllers;

import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.services.InterfaceProduit;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/produit")
public class ProduitController {

    private InterfaceProduit interfaceProduit;


    @PostMapping("/enregistrerProd")
    private ProduitDTO creerProd(@Valid @RequestBody ProduitDTO produitDTO) throws MontantQuantiteNullException, ProduitDupicateException, EmptyException {
        return interfaceProduit.enregistrerProd(produitDTO);
    }

    @PutMapping("/modifierProd/{idProd}")
    private ProduitDTO modifierProd(@Valid @RequestBody ProduitDTO produitDTO, @PathVariable("idProd") String idProd) throws  EmptyException {
        produitDTO.setIdProd(idProd);
        return interfaceProduit.modifierProd(produitDTO);
    }

    @PutMapping("/supprimerProd")
    private void supprimerProd(@Valid @RequestBody String idProd) throws ProduitNotFoundException{
        interfaceProduit.suppressionProd(idProd);
    }

    @GetMapping("/listeProd")
    private List<ProduitDTO> produitDTOList(){
        return interfaceProduit.ListerProd();
    }

    @GetMapping("/afficherProd/{idProd}")
    private ProduitDTO afficherProd(@Valid @PathVariable String idProd) throws ProduitNotFoundException{
        return interfaceProduit.afficherProd(idProd);
    }


}
