package com.allura.forohub.controller;

import com.allura.forohub.DTO.usuario.*;
import com.allura.forohub.model.Usuario;
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
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    private UsuarioRepository usuarioRepository;

    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping
    public ResponseEntity<RespuestaUsuarioDTO> registrar(@RequestBody RegistroUsuarioDTO datosRegistro, UriComponentsBuilder uri) {
        Usuario usuario = usuarioRepository.save(new Usuario(datosRegistro));
        RespuestaUsuarioDTO respuesta = new RespuestaUsuarioDTO(usuario);
        URI url = uri.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(respuesta);
    }

    @GetMapping
    public ResponseEntity<Page<ListadoUsuariosDTO>> listado(@PageableDefault(size = 10) Pageable paginacion) {
        return ResponseEntity.ok(usuarioRepository.findAll(paginacion).map(ListadoUsuariosDTO::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RespuestaUsuarioIdDTO> retornarUsuario(@PathVariable Long id) {
        Usuario usuario = usuarioRepository.getReferenceById(id);
        return ResponseEntity.ok(new RespuestaUsuarioIdDTO(usuario));
    }

    @PutMapping
    @Transactional
    public  ResponseEntity<RespuestaUsuarioDTO> actualizar(@RequestBody ActualizarUsuarioDTO actualizardatos){
        Usuario usuario = usuarioRepository.getReferenceById(actualizardatos.id());
        usuario.actualizarUsuario(actualizardatos);
        return ResponseEntity.ok(new RespuestaUsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        Usuario usuario = usuarioRepository.getReferenceById(id);
        usuarioRepository.delete(usuario);
        return  ResponseEntity.noContent().build();
    }
}
