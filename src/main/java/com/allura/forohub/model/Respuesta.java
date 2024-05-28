package com.allura.forohub.model;

import com.allura.forohub.DTO.respuesta.ActualizaRespuestaDTO;
import com.allura.forohub.DTO.respuesta.RegistrarRespuestaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "Respuesta")
@Table(name = "respuestas")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private Boolean solucion = false;

    public Respuesta(RegistrarRespuestaDTO datosRegistro, Topico topico, Usuario autor) {
        this.mensaje = datosRegistro.mensaje();
        this.topico = topico;
        this.autor = autor;
        this.solucion = datosRegistro.solucion();
        if (datosRegistro.solucion()){
            this.topico.setEstado(Estado.SOLUCIONADO);
        } else {
            this.topico.setEstado(Estado.SIN_SOLUCIONAR);
        }
    }

    public void actualizaRespuesta(ActualizaRespuestaDTO actualizaDatos, Topico topico, Usuario usuario) {
        if(actualizaDatos.mensaje() != null){
            this.mensaje = actualizaDatos.mensaje();
        }
        if(topico != null){
            this.topico = topico;
        }
        if(usuario != null){
            this.autor = usuario;
        }
        if(actualizaDatos.solucion() != this.solucion){
            this.solucion =actualizaDatos.solucion();
        }
    }
}
