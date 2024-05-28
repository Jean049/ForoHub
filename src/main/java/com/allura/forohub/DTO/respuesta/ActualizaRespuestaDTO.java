package com.allura.forohub.DTO.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ActualizaRespuestaDTO(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        @NotNull
        Long topicoId,
        @NotBlank
        Long autorId,
        @NotNull
        Boolean solucion
) {
}
