package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, String> {

    @Query("SELECT p FROM Produit p WHERE p.supprimerStatus = 'FALSE'")
    List<Produit> findAllBySupprimerStatusFalse();
}
