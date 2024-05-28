package com.allura.forohub.DTO.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistrarRespuestaDTO(
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotNull
        Long autorId,
        @NotBlank
        Boolean solucion
) {
}
