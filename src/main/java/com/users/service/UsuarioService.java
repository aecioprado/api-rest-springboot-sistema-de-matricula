package com.users.service;

import com.users.domain.model.Usuario;
import com.users.security.UsuarioDetails;
import com.users.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findByLogin(String username){

        Usuario usuarioCarregado = usuarioRepository.findByLogin(username);

        if(usuarioCarregado == null){
            throw new UsernameNotFoundException("Usuário não encontrado.");
        }

        return usuarioCarregado;
    }


    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> getOneById(Long id) {
        return usuarioRepository.findById(id);
    }


    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }






    public boolean delete(Long id) {
        usuarioRepository.deleteById(id);
        return usuarioRepository.existsById(id);
        }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = usuarioRepository.findByLogin(username);

            if (usuario == null){
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
            else{
                return new UsuarioDetails(usuario);
            }

        }

}
