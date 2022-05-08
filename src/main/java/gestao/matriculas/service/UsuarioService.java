package gestao.matriculas.service;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.BadBinaryOpValueExpException;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService implements ServiceCrud<Usuario>{

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getOneById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioCarregado = usuarioRepository.findById(id);
        usuario.setId(usuarioCarregado.get().getId());
        return usuarioRepository.save(usuario);
    }


    @Override
    public boolean delete(Long id) {
        usuarioRepository.deleteById(id);
        var isRemoved = usuarioRepository.existsById(id);
        return isRemoved;
        }
    }
