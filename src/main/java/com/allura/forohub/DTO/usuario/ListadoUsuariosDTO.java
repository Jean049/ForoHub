package com.allura.forohub.DTO.usuario;

import com.allura.forohub.model.Usuario;

public record ListadoUsuariosDTO(
        Long id,
        String nombre,
        String email
) {
    public ListadoUsuariosDTO(Usuario usuario){
        this(   usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail());
    }
}
