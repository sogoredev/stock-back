package com.gds.Gestion.de.stock.entites;

import com.gds.Gestion.de.stock.enums.TypeRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRole {

    @Id
    @Enumerated(EnumType.STRING)
    private TypeRole name;
}
