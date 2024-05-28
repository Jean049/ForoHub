package com.allura.forohub.DTO.usuario;

public record ActualizarUsuarioDTO(
        Long id,
        String nombre,
        String email,
        String contrasena
) {
}
