package com.allura.forohub.DTO.topico;

import com.allura.forohub.model.Topico;

public record ListadoTopicoDTO(
        Long id,
        String titulo,
        String mensaje,
        String autor
) {
    public ListadoTopicoDTO(Topico topico){
        this(topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre());
    }
}
