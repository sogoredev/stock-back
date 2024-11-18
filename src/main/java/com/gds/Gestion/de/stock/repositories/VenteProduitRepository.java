package com.gds.Gestion.de.stock.repositories;

import com.gds.Gestion.de.stock.entites.Produit;
import com.gds.Gestion.de.stock.entites.VenteProduit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VenteProduitRepository extends JpaRepository<VenteProduit, Long> {

    @Query("SELECT vp FROM VenteProduit vp WHERE vp.vente.idVente = :idVente")
    List<VenteProduit> findVenteProduitsByVenteId(String idVente);




}
