package com.allura.forohub.model;

import com.allura.forohub.DTO.topico.ActualizarTopicoDTO;
import com.allura.forohub.DTO.topico.RegistroTopicoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "Topico")
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fecha_creacion = LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    private Estado status = Estado.SIN_RESPUESTA;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Respuesta> respuestas;

    public Topico(RegistroTopicoDTO datosRegistro, Usuario autor) {
        this.titulo = datosRegistro.titulo();
        this.mensaje = datosRegistro.mensaje();
        this.autor = autor;
    }

    public void setEstado(Estado estado) {
        this.status = estado;
    }

    public void postRespuesta(Respuesta respuesta) {
        this.respuestas.add(respuesta);
        if (respuesta.getSolucion()){
            this.status = Estado.SOLUCIONADO;
        } else {
            this.status = Estado.SIN_SOLUCIONAR;
        }
    }

    public void actualizarTopico(ActualizarTopicoDTO actualizarDatos, Usuario usuario) {
        if(actualizarDatos.titulo() != null){
            this.titulo = actualizarDatos.titulo();
        }
        if(actualizarDatos.mensaje() != null){
            this.mensaje = actualizarDatos.mensaje();
        }
        if(actualizarDatos.estado() != actualizarDatos.estado()){
            this.status = actualizarDatos.estado();
        }
        if(usuario != null){
            this.autor = usuario;
        }
    }

    public void cerrar() {
        this.status = Estado.CERRADO;
    }
}
