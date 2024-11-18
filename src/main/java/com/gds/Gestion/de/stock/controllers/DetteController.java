package com.gds.Gestion.de.stock.controllers;

import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.DTOs.DetteDTO;
import com.gds.Gestion.de.stock.DTOs.DetteDTO;
import com.gds.Gestion.de.stock.DTOs.VenteDTO;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.services.InterfaceDette;
import com.gds.Gestion.de.stock.services.InterfaceVente;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/dette")
public class DetteController {

    private InterfaceDette interfaceDette;

    @PostMapping("/enregistrerDette")
    private void effectuerDette(@Valid @RequestBody DetteDTO detteDTO) throws Exception {
        interfaceDette.enregistrerDette(detteDTO);
    }

    @PutMapping("/payerDette")
    private void payerDette(@Valid @RequestBody String idDette) throws EmptyException, DetteNotFoundException {
         interfaceDette.paiementDette(idDette);
    };

    @PutMapping("/modifierDette/{idDette}")
    private void update(@Valid @RequestBody DetteDTO DetteDTO, @PathVariable("idDette") String idDette) throws DetteNotFoundException, ApprovNotFoundException, EmptyException {
        DetteDTO.setIdDette(idDette);
         interfaceDette.modifierDette(DetteDTO);
    }

    @GetMapping("/afficherDette/{idDette}")
    private DetteDTO afficher(@Valid @PathVariable("idDette") String idDette) throws DetteNotFoundException {
        return interfaceDette.afficher(idDette);
    }


    @PutMapping("/supprimerDette")
    private void supprimerDette(@Valid @RequestBody String idDette) throws DetteNotFoundException{
        interfaceDette.supprimerDette(idDette);
    }

    @GetMapping("/listeDette")
    private List<DetteDTO> listDette() {
        return interfaceDette.listerDette();
    }
}
