package com.allura.forohub.DTO.usuario;

import com.allura.forohub.model.Usuario;

public record RespuestaUsuarioIdDTO(
        String nombre,
        String email,
        String contrasena
) {
    public RespuestaUsuarioIdDTO(Usuario usuario){
        this(   usuario.getNombre(),
                usuario.getEmail(),
                usuario.getContrasena());
    }
}
