package com.allura.forohub.controller;

import com.allura.forohub.DTO.topico.*;
import com.allura.forohub.model.Topico;
import com.allura.forohub.model.Usuario;
import com.allura.forohub.repository.TopicoRepository;
import com.allura.forohub.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {
    private TopicoRepository topicoRepository;
    private UsuarioRepository usuarioRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository){
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaTopicoDTO> Registrar(@RequestBody @Valid RegistroTopicoDTO datosRegistro, UriComponentsBuilder uri){
        Usuario autor = usuarioRepository.getReferenceById(datosRegistro.autorid());
        Topico topico = topicoRepository.save(new Topico(datosRegistro, autor));
        RespuestaTopicoDTO respuesta = new RespuestaTopicoDTO(topico);
        URI url = uri.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoTopicoDTO>> listado(@PageableDefault(size = 10) Pageable paginacion){
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(ListadoTopicoDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaTopicoIdDTO> retornarTopico(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaTopicoIdDTO(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<RespuestaTopicoDTO> actualizar(@RequestBody @Valid ActualizarTopicoDTO actualizarDatos){
        Usuario usuario = usuarioRepository.getReferenceById(actualizarDatos.autorId());
        Topico topico = topicoRepository.getReferenceById(actualizarDatos.id());
        topico.actualizarTopico(actualizarDatos, usuario);
        return ResponseEntity.ok(new RespuestaTopicoDTO(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        topico.cerrar();
        return  ResponseEntity.noContent().build();
    }
}
