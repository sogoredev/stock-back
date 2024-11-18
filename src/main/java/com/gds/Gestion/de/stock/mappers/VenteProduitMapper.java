package com.gds.Gestion.de.stock.mappers;

import com.gds.Gestion.de.stock.DTOs.VenteProduitDTO;
import com.gds.Gestion.de.stock.entites.VenteProduit;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VenteProduitMapper {

    public VenteProduit mapDeDtoAVenteProduit(VenteProduitDTO venteProduitDTO) {
        VenteProduit venteProduit = new VenteProduit();
        BeanUtils.copyProperties(venteProduitDTO, venteProduit);
        return venteProduit;
    }

    public VenteProduitDTO mapDeVenteADTO(VenteProduit venteProduit) {
        VenteProduitDTO venteProduitDTO = new VenteProduitDTO();
        BeanUtils.copyProperties(venteProduit, venteProduitDTO);
        return venteProduitDTO;
    }
}
