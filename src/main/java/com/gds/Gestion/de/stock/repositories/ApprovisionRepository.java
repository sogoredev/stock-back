package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Approvision;
import com.gds.Gestion.de.stock.entites.Client;
import com.gds.Gestion.de.stock.entites.Dette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovisionRepository extends JpaRepository<Approvision, String> {
    Approvision findByDesignation(String designation);

    @Query("SELECT a FROM Approvision a WHERE a.supprimerStatus = 'FALSE'")
    List<Approvision> findAllBySupprimerStatusFalse();
}
