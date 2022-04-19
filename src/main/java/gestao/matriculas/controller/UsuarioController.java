package gestao.matriculas.controller;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.dto.CredenciaisDto;
import gestao.matriculas.dto.TokenDto;
import gestao.matriculas.repository.UsuarioRepository;
import gestao.matriculas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor

public class UsuarioController {

    @Autowired
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioRepository usuarioRepository;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuario-autenticado")
    public String getUsername() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return securityContext.getAuthentication().getName();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario create(@RequestBody Usuario usuario) {
        String senhaEncriptada = usuario.getSenha();
        usuario.setSenha(passwordEncoder().encode(senhaEncriptada));
        return usuarioService.save(usuario);
    }

    @PostMapping("/auth")
    public TokenDto autenticacao(@RequestBody CredenciaisDto credenciais){
    return null;
    }


}