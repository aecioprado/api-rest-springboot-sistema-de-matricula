package gestao.matriculas.service;

import gestao.matriculas.domain.Role;
import gestao.matriculas.domain.Usuario;
import gestao.matriculas.domain.UsuarioDetails;
import gestao.matriculas.repository.UsuarioRepository;
import javassist.Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements UserDetailsService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;



    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }


    public Optional<Usuario> getOneById(Long id) {
        return usuarioRepository.findById(id);
    }


    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }


    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioCarregado = usuarioRepository.findById(id);
        usuario.setId(usuarioCarregado.get().getId());
        return usuarioRepository.save(usuario);
    }



    public boolean delete(Long id) {
        usuarioRepository.deleteById(id);
        return usuarioRepository.existsById(id);
        }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            Usuario usuario = usuarioRepository.findByUsername(username);

            if (usuario == null){
                throw new UsernameNotFoundException("Usuário não encontrado");
            }
            else{
                return new UsuarioDetails(usuario);
            }

        }

}
