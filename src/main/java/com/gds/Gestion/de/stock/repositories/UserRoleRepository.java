package com.gds.Gestion.de.stock.repositories;


import com.gds.Gestion.de.stock.entites.UserRole;
import com.gds.Gestion.de.stock.enums.TypeRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    UserRole findByName(TypeRole name);

}
