package com.allura.forohub.DTO.respuesta;

import com.allura.forohub.DTO.topico.RespuestaTopicoDTO;
import com.allura.forohub.DTO.usuario.RespuestaUsuarioDTO;
import com.allura.forohub.model.Respuesta;

public record RespuestaRespuestaIdDTO(
        String mensaje,
        RespuestaTopicoDTO topico,
        String fechaCreacion,
        RespuestaUsuarioDTO autor,
        String solucion
) {
    public RespuestaRespuestaIdDTO(Respuesta respuesta){
        this(   respuesta.getMensaje(),
                new RespuestaTopicoDTO(respuesta.getTopico()),
                respuesta.getFechaCreacion().toString(),
                new RespuestaUsuarioDTO(respuesta.getAutor()),
                respuesta.getSolucion().toString());

    }
}
