package com.allura.forohub.DTO.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutenticacionUsuarioDTO(
        @NotBlank
        @Email
        String email,
        @NotBlank
        String contrasena
) {
}
