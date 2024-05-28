package com.allura.forohub.DTO.topico;

import com.allura.forohub.DTO.usuario.RespuestaUsuarioDTO;
import com.allura.forohub.model.Topico;

public record RespuestaTopicoIdDTO(
        Long id,
        String titulo,
        String mensaje,
        String fecha_creacion,
        String status,
        RespuestaUsuarioDTO autor
) {
    public RespuestaTopicoIdDTO(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFecha_creacion().toString(),
                topico.getStatus().toString(),
                new RespuestaUsuarioDTO(topico.getAutor()));
    }
}
