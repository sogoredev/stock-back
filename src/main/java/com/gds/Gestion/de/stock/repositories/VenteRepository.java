package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Vente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, String> {

    @Query(value = "SELECT COUNT(*) FROM vente", nativeQuery = true)
    long countTotalVente();

    @Query("SELECT v FROM Vente v WHERE v.supprimerStatus = 'FALSE'")
    List<Vente> findAllBySupprimerStatusFalse();


}
