package gestao.matriculas.service;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UserDetails autenticacao(Usuario usuario){
       UserDetails usuarioCarregado = loadUserByUsername(usuario.getLogin());
       boolean isPasswordMatches = passwordEncoder.matches(usuario.getSenha(), usuarioCarregado.getPassword());
       if(isPasswordMatches){
           return usuarioCarregado;
       }
       throw new RuntimeException("Login/senha inválidos.");
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado"));

        Collection<SimpleGrantedAuthority> perfis = usuario.getPerfis().stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.getNome())).collect(toList());

        System.out.println(perfis);

        User user = new User(usuario.getLogin(), usuario.getSenha(), perfis);
        return user;
        };

    }
