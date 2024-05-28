package com.allura.forohub.DTO.respuesta;

import com.allura.forohub.model.Respuesta;

public record RespuestaRespuestasDTO(
        String mensaje,
        String topico,
        String autor
) {
    public RespuestaRespuestasDTO(Respuesta respuesta) {
        this(   respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getAutor().getNombre());
    }
}
