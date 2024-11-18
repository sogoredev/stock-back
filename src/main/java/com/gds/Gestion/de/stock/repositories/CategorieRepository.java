package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Approvision;
import com.gds.Gestion.de.stock.entites.CategorieStock;
import com.gds.Gestion.de.stock.entites.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategorieRepository extends JpaRepository<CategorieStock, Long> {
    CategorieStock findByNom(String nom);

    @Query("SELECT c FROM CategorieStock c WHERE c.supprimerStatus = 'FALSE'")
    List<CategorieStock> findAllBySupprimerStatusFalse();
}
