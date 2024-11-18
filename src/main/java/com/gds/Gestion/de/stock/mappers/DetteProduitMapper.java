package com.gds.Gestion.de.stock.mappers;

import com.gds.Gestion.de.stock.DTOs.DetteProduitDTO;
import com.gds.Gestion.de.stock.entites.DetteProduit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DetteProduitMapper {

    public DetteProduit mapDeDtoADetteProduit(DetteProduitDTO DetteProduitDTO) {
        DetteProduit DetteProduit = new DetteProduit();
        BeanUtils.copyProperties(DetteProduitDTO, DetteProduit);
        return DetteProduit;
    }

    public DetteProduitDTO mapDeDetteProduitADTO(DetteProduit DetteProduit) {
        DetteProduitDTO DetteProduitDTO = new DetteProduitDTO();
        BeanUtils.copyProperties(DetteProduit, DetteProduitDTO);
        return DetteProduitDTO;
    }
}
