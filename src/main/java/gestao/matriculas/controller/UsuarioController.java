package gestao.matriculas.controller;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.dto.CredenciaisDto;
import gestao.matriculas.dto.TokenDto;
import gestao.matriculas.dto.UsuarioDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("usuarios")
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

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDto> listarTudo() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarios.stream().map(e -> UsuarioDto.semSenha(e)).collect(Collectors.toList());
        return usuarioDtos;
    }

    @GetMapping("/listar/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioDto listarPorId(@PathVariable("usuarioId") Integer usuarioId) {
        var usuarioCarregado =  usuarioRepository.findById(usuarioId).get();
        return  UsuarioDto.semSenha(usuarioCarregado);
    }

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criar(@RequestBody Usuario usuario) {
        var senhaEncriptada = usuario.getSenha();
        usuario.setSenha(passwordEncoder().encode(senhaEncriptada));
        return usuarioService.save(usuario);
    }

    @PatchMapping("/{usuarioId}/editar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Usuario editar(@PathVariable("usuarioId") Integer usuarioId, @RequestBody Usuario usuarioEditado) {
        var usuarioCarregado = usuarioRepository.findById(usuarioId).get();
        usuarioEditado.setId(usuarioCarregado.getId());
        return usuarioService.save(usuarioEditado);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable("usuarioId") Integer usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId).get();
        if(usuario == null){
            throw new RuntimeException("Recurso "+usuario+" não existe.");
        }
        usuarioRepository.delete(usuario);
    }




}