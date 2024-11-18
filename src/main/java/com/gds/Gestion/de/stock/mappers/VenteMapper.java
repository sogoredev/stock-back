package com.gds.Gestion.de.stock.mappers;

import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.DTOs.VenteDTO;
import com.gds.Gestion.de.stock.entites.Produit;
import com.gds.Gestion.de.stock.entites.Vente;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenteMapper {

    @Autowired
    private ClientMapper clientMapper;

    public Vente mapDeDtoAVente(VenteDTO venteDTO) {
        Vente vente = new Vente();
        BeanUtils.copyProperties(venteDTO, vente);
        vente.setClientsVente(clientMapper.mapDeDtoAClient(venteDTO.getClientDTO()));
        return vente;
    }

    public VenteDTO mapDeVenteADTO(Vente vente) {
        VenteDTO venteDTO = new VenteDTO();
        BeanUtils.copyProperties(vente, venteDTO);
        venteDTO.setClientDTO(clientMapper.mapDeClientADto(vente.getClientsVente()));
        return venteDTO;
    }
}
