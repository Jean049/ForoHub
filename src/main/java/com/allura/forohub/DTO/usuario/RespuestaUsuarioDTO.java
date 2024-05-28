package com.allura.forohub.DTO.usuario;

import com.allura.forohub.model.Usuario;

public record RespuestaUsuarioDTO(
        String nombre,
        String email
) {
    public RespuestaUsuarioDTO(Usuario usuario) {
        this(   usuario.getNombre(),
                usuario.getEmail());
    }
}
