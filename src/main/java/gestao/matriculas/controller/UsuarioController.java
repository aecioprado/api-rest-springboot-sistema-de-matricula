package gestao.matriculas.controller;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("usuarios")
@AllArgsConstructor
public class UsuarioController {

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<Usuario> listarUsuarios(){
        return usuarioService.getAll();
    }

    @GetMapping("/listar/{usuarioId}")
    @ResponseStatus(HttpStatus.OK)
    public Usuario listarUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getOneById(usuarioId).get();
    }

    @PostMapping("/novo")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario criarUsuario(@RequestBody Usuario usuario) {

        return usuarioService.save(usuario);
    }

    @PutMapping("/{usuarioId}/editar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Usuario editarUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody Usuario usuarioEditado) {
        return usuarioService.update(usuarioId, usuarioEditado);
    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String excluirUsuario(@PathVariable("usuarioId") Long usuarioId) {
        var isRemoved = usuarioService.delete(usuarioId);

        if(isRemoved){
            return "Usuário id #"+usuarioId+" excluido com sucesso";
        }
        else{
            return "Usuário não excluido";
        }
    }

    /*
    @GetMapping("/perfis")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDto> listarPerfis() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarios.stream().map(e -> UsuarioDto.semSenha(e)).collect(Collectors.toList());
        return usuarioDtos;

    }

    @GetMapping("/perfis/{id}/editar")
    @ResponseStatus(HttpStatus.OK)
    public List<UsuarioDto> listarPerfilPorId() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarios.stream().map(e -> UsuarioDto.semSenha(e)).collect(Collectors.toList());
        return usuarioDtos;
    }

    @DeleteMapping("/perfis/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirPerfil(@PathVariable("usuarioId") Integer usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId).get();
        if (usuario == null) {
            throw new RuntimeException("Recurso " + usuario + " não existe.");
        }
        usuarioRepository.delete(usuario);
    }
    */

}

