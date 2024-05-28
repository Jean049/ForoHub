package com.allura.forohub.controller;

import com.allura.forohub.DTO.security.JWTtokenDTO;
import com.allura.forohub.DTO.usuario.AutenticacionUsuarioDTO;
import com.allura.forohub.model.Usuario;
import com.allura.forohub.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTtokenDTO> autenticar(@RequestBody @Valid AutenticacionUsuarioDTO autenticacionUsuario){
        Authentication token = new UsernamePasswordAuthenticationToken(autenticacionUsuario.email(),
                autenticacionUsuario.contrasena());
        var usuario = authenticationManager.authenticate(token);
        var jwtToken = tokenService.generar((Usuario) usuario.getPrincipal());
        return ResponseEntity.ok(new JWTtokenDTO(jwtToken));
    }
}
