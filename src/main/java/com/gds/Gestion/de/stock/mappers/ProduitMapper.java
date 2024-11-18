package com.gds.Gestion.de.stock.mappers;


import com.gds.Gestion.de.stock.DTOs.ProduitDTO;
import com.gds.Gestion.de.stock.entites.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProduitMapper {

    @Autowired
    private CategorieMapper categorieMapper;
    private UtilisateurMapper utilisateurMapper;

    public ProduitDTO mapDeProdADto(Produit produit) {
        ProduitDTO produitDTO = new ProduitDTO();
        BeanUtils.copyProperties(produit, produitDTO);
        produitDTO.setCategorieStockProdDTO(categorieMapper.mapDeCategorieADto(produit.getCategorieStock()));
//        produitDTO.setUtilisateurProdDto(utilisateurMapper.mapDeUserADto(produit.getUtilisateurProd()));
        return produitDTO;
    }


    public Produit mapDeDtoAProd(ProduitDTO produitDTO) {
        Produit produit = new Produit();
        BeanUtils.copyProperties(produitDTO, produit);
        produit.setCategorieStock(categorieMapper.mapDeDtoACategorie(produitDTO.getCategorieStockProdDTO()));
//        produit.setUtilisateurProd(utilisateurMapper.mapDeDtoAUser(produitDTO.getUtilisateurProdDto()));
        return produit;
    }
}
