package com.allura.forohub.model;

import com.allura.forohub.DTO.usuario.ActualizarUsuarioDTO;
import com.allura.forohub.DTO.usuario.RegistroUsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity(name = "Usuario")
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
    private String contrasena;

    public Usuario(RegistroUsuarioDTO datosRegistro) {
        this.nombre = datosRegistro.nombre();
        this.email = datosRegistro.email();
        this.contrasena = datosRegistro.contrasena();
    }

    public void actualizarUsuario(ActualizarUsuarioDTO actualizardatos) {
        if(actualizardatos.nombre() != null){
            this.nombre = actualizardatos.nombre();
        }
        if(actualizardatos.email() != null){
            this.email = actualizardatos.email();
        }
        if(actualizardatos.contrasena() != null){
            this.contrasena = actualizardatos.contrasena();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
