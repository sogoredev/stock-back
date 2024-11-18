package com.gds.Gestion.de.stock.security;

import com.gds.Gestion.de.stock.DTOs.AuthentificationDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@Builder
@AllArgsConstructor
@Slf4j
public class SecurityController {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;

    @PostMapping("/login")
  private Map<String, String> conexion(@RequestBody AuthentificationDTO authentificationDTO){
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authentificationDTO.username(), authentificationDTO.password())
        );
        if (authenticate.isAuthenticated()){
            log.info("Resulta{}",authenticate.isAuthenticated());
            return jwtService.genererToken(authentificationDTO.username());
        }

        return null;
    }
}

