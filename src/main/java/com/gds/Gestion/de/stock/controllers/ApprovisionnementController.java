package com.gds.Gestion.de.stock.controllers;


import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.DTOs.ApprovisionDTO;
import com.gds.Gestion.de.stock.exceptions.*;
import com.gds.Gestion.de.stock.services.InterfaceApprovision;
import com.gds.Gestion.de.stock.services.InterfaceCategorie;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/approvision")
public class ApprovisionnementController {

    private InterfaceApprovision interfaceApprovision;

    @PostMapping("/creerApprov")
    private ApprovisionDTO save(@Valid @RequestBody ApprovisionDTO approvisionDTO) throws EmptyException, ApprovisionDupicateException {
        return interfaceApprovision.enregistrerApprovision(approvisionDTO);
    };

    @PostMapping("/traiterApprov")
    private void traiter(@Valid @RequestBody String idApprov) throws EmptyException, ApprovisionDupicateException, MontantQuantiteNullException, ProduitDupicateException, ApprovNotFoundException {
         interfaceApprovision.traiterApprovision(idApprov);
    };

    @PutMapping("/modifierApprov/{idApprov}")
    private ApprovisionDTO update(@Valid @RequestBody ApprovisionDTO ApprovisionDTO, @PathVariable("idApprov") String idApprov) throws ApprovNotFoundException {
        ApprovisionDTO.setIdApprov(idApprov);
        return interfaceApprovision.modifierApprov(ApprovisionDTO);
    }

    @GetMapping("/afficherApprov/{idApprov}")
    private ApprovisionDTO getIdCat(@Valid @PathVariable("idApprov") String idApprov) throws ApprovNotFoundException {
        return interfaceApprovision.afficher(idApprov);
    }

    @GetMapping("/listeApprov")
    private List<ApprovisionDTO> listApprov(){
        return interfaceApprovision.listerApprovision();
    }

    @PutMapping("/supprimerApprov")
    private void supprimerApprov(@Valid @RequestBody String idApprov) throws DetteNotFoundException{
        interfaceApprovision.supprimerApprov(idApprov);
    }
    
    

}
