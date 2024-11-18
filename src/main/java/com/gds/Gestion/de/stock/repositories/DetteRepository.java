package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Dette;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetteRepository extends JpaRepository<Dette, String> {
    Dette findByTitre(String string);

    @Query("SELECT d FROM Dette d WHERE d.supprimerStatus = 'FALSE'")
    List<Dette> findAllBySupprimerStatusFalse();
}
