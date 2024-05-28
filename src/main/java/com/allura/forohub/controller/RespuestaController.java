package com.allura.forohub.controller;

import com.allura.forohub.DTO.respuesta.*;
import com.allura.forohub.model.Estado;
import com.allura.forohub.model.Respuesta;
import com.allura.forohub.model.Topico;
import com.allura.forohub.model.Usuario;
import com.allura.forohub.repository.RespuestaRepository;
import com.allura.forohub.repository.TopicoRepository;
import com.allura.forohub.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/respuestas")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    private RespuestaRepository respuestaRepository;
    private TopicoRepository topicoRepository;
    private UsuarioRepository usuarioRepository;

    public RespuestaController(RespuestaRepository respuestaRepository, TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.respuestaRepository = respuestaRepository;
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaRespuestasDTO> registro(@RequestBody RegistrarRespuestaDTO datosRegistro, UriComponentsBuilder uri){
        Topico topico = topicoRepository.getReferenceById(datosRegistro.topicoId());
        if(topico.getStatus() == Estado.CERRADO){
            return ResponseEntity.unprocessableEntity().build();
        } else {
            Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorId());
            Respuesta respuesta = respuestaRepository.save(new Respuesta(datosRegistro, topico, autor));
            topico.postRespuesta(respuesta);
            RespuestaRespuestasDTO respuestasDTO = new RespuestaRespuestasDTO(respuesta);
            URI url = uri.path("/respuestas/{id}").buildAndExpand(respuesta.getId()).toUri();
            return ResponseEntity.created(url).body(respuestasDTO);
        }
    }

    @GetMapping
    public ResponseEntity<Page<ListadoRespuestasDTO>> listado(@PageableDefault(size = 10)Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(ListadoRespuestasDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaRespuestaIdDTO> retornar(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaRespuestaIdDTO(respuesta));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaRespuestasDTO> actualizar(@RequestBody ActualizaRespuestaDTO actualizaDatos) {
        Respuesta respuesta = respuestaRepository.getReferenceById(actualizaDatos.id());
        Topico topico = topicoRepository.getReferenceById(actualizaDatos.topicoId());
        Usuario usuario = usuarioRepository.getReferenceById(actualizaDatos.autorId());
        respuesta.actualizaRespuesta(actualizaDatos, topico, usuario);
        return ResponseEntity.ok(new RespuestaRespuestasDTO(respuesta));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Respuesta respuesta = respuestaRepository.getReferenceById(id);
        respuestaRepository.delete(respuesta);
        return ResponseEntity.noContent().build();
    }
}
