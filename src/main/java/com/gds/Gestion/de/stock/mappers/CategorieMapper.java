package com.gds.Gestion.de.stock.mappers;

import com.gds.Gestion.de.stock.DTOs.CategorieStockDTO;
import com.gds.Gestion.de.stock.entites.CategorieStock;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategorieMapper {

   private UtilisateurMapper utilisateurMapper;


//    convertir de categorie a dto
    public CategorieStockDTO mapDeCategorieADto(CategorieStock categorieStock) {
        CategorieStockDTO categorieStockDTO = new CategorieStockDTO();
        BeanUtils.copyProperties(categorieStock, categorieStockDTO);
//        categorieStockDTO.setUtilisateurDTO(utilisateurMapper.mapDeUserADto(categorieStock.getUtilisateurCat()));
        return categorieStockDTO;
    }


//    convertir de input a categorie
    public CategorieStock mapDeDtoACategorie(CategorieStockDTO categorieStockDTO) {
        CategorieStock categorieStock = new CategorieStock();
        BeanUtils.copyProperties(categorieStockDTO, categorieStock);
//        categorieStock.setUtilisateurCat(utilisateurMapper.mapDeDtoAUser(categorieStockDTO.getUtilisateurDTO()));
        return categorieStock;
    }

}
