package com.allura.forohub.DTO.topico;

import com.allura.forohub.model.Respuesta;
import com.allura.forohub.model.Topico;

public record RespuestaTopicoDTO(
        String titulo,
        String mensaje,
        String autor
) {
    public RespuestaTopicoDTO(Topico topico) {
        this(   topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre());
    }
}
