package com.allura.forohub.DTO.respuesta;

import com.allura.forohub.model.Respuesta;

public record ListadoRespuestasDTO(
        Long id,
        String mensaje,
        String topico,
        String autor
) {
    public ListadoRespuestasDTO(Respuesta respuesta){
        this(respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getTitulo(),
                respuesta.getAutor().getNombre());
    }
}
