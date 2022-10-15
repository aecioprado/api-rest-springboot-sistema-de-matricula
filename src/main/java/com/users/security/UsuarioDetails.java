package com.users.security;

import com.users.domain.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// Implementa o objeto UserDetails usado para incluir o usuario no contexto de seguranca do Spring Security
public class UsuarioDetails implements UserDetails {
    @Autowired
    private Usuario usuario;

    public UsuarioDetails(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        usuario.getPermissoes().forEach(roles -> new SimpleGrantedAuthority(roles.getDescricao()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return this.usuario.getLogin();
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
