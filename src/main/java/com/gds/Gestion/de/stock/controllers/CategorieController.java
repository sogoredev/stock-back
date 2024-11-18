package com.gds.Gestion.de.stock.controllers;


import com.gds.Gestion.de.stock.DTOs.CategorieStockDTO;
import com.gds.Gestion.de.stock.exceptions.CategorieDuplicateException;
import com.gds.Gestion.de.stock.exceptions.CategorieNotFoundException;
import com.gds.Gestion.de.stock.exceptions.EmptyException;
import com.gds.Gestion.de.stock.services.InterfaceCategorie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/categorie")
public class CategorieController {

    private InterfaceCategorie interfaceCategorie;

    @PostMapping("/creerCat")
    private CategorieStockDTO save(@Valid @RequestBody CategorieStockDTO categorieStockDTO) throws CategorieDuplicateException, EmptyException {
        return interfaceCategorie.creerCat(categorieStockDTO);
    };

    @PutMapping("/modifierCat/{idCat}")
    private CategorieStockDTO update(@Valid @RequestBody CategorieStockDTO categorieStockDTO, @PathVariable("idCat") Long idCat) throws CategorieDuplicateException, EmptyException {
        categorieStockDTO.setIdCat(idCat);
        return interfaceCategorie.modifierCat(categorieStockDTO);
    }

    @GetMapping("/afficherCat/{idCat}")
    private CategorieStockDTO getIdCat(@Valid @PathVariable("idCat") Long idCat) throws CategorieNotFoundException {
        return interfaceCategorie.afficher(idCat);
    }

    @PutMapping("/supprimerCat")
    private void delete(@Valid @RequestBody Long idCat) throws CategorieNotFoundException {
        interfaceCategorie.supprimerCat(idCat);
    }

    @GetMapping(value="/listeCat" , consumes = { "application/json", "application/xml" })
    private List<CategorieStockDTO> getAllCat(  ) {
        return interfaceCategorie.listCat();
    }
}
