package com.gds.Gestion.de.stock.DTOs;



import com.gds.Gestion.de.stock.entites.Utilisateur;
import com.gds.Gestion.de.stock.enums.Etat;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import lombok.Data;


import java.time.LocalDate;
import java.util.List;


@Data
public class ApprovisionDTO {

    private String idApprov;
    private String designation;
    private int quantite;
    private int prixUnitaire;
    private int montant;
    private int fraisTransit;
    private double cbm;
    private LocalDate dateAchat;
    private LocalDate dateArriver;
    private String adresseFrs;
    private String image;
    private Etat etat;
    private String description;
    private SupprimerStatus supprimerStatus;
    private ProduitDTO produitsApprov;
    private Utilisateur utilisateurAprov;
}




/*spring.application.name=Gestion-de-produit
server.port=8082
spring.datasource.url=jdbc:mysql://172.17.0.1:3306/dbStock?createDatabaseIfNotExist=true
spring.datasource.username=stock
spring.datasource.password=StockBackend@2024ml
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
server.servlet.context-path=/api


jwt.secret=357638792F423F4428472B4B6250655368566D597133743677397A2443264629*/
