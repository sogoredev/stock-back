package com.gds.Gestion.de.stock.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gds.Gestion.de.stock.enums.SupprimerStatus;
import com.gds.Gestion.de.stock.enums.TypeActive;
import com.gds.Gestion.de.stock.enums.TypeAuth;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.*;

@Entity
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
public class Utilisateur implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 60)
    private String nom;
    @Column(length = 60)
    private String prenom;
    @Column(unique = true, length = 15, nullable = false)
    private String telephone;
    @Column(unique = true, length = 100, nullable = false)
    private String email;
    @Column(length = 80, nullable = false)
    private String password;
    private LocalDate date;
    @Enumerated(EnumType.STRING)
    private TypeAuth authentification;
    @Enumerated(EnumType.STRING)
    private TypeActive activation;
    @Enumerated(EnumType.STRING)
    private SupprimerStatus supprimerStatus;

    @Enumerated(EnumType.STRING)
    @ManyToMany(fetch = FetchType.EAGER)
    private List<UserRole> roles;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : this.roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
