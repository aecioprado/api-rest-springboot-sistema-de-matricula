package gestao.matriculas.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UsuarioLogin implements UserDetails{

    private final Usuario usuario;

    public UsuarioLogin(Usuario usuario) {
        this.usuario = usuario;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Perfil perfil: usuario.getPerfis()){
            authorities.add(new SimpleGrantedAuthority(perfil.getNome()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.usuario.getSenha();
    }

    @Override
    public String getUsername() {
        return this.usuario.getEmail();
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
