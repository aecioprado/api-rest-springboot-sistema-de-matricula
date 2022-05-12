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
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public List<Usuario> listarUsuarios(){
        return usuarioService.getAll();
    }

    @GetMapping("/listar/{usuarioId}")
    public Usuario listarUsuarioPorId(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getOneById(usuarioId).get();
    }

    @PostMapping("/novo")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{usuarioId}/editar")
    public Usuario editarUsuario(@PathVariable("usuarioId") Long usuarioId, @RequestBody Usuario usuarioEditado) {
        return usuarioService.update(usuarioId, usuarioEditado);
    }

    @DeleteMapping("/{usuarioId}")
    public String excluirUsuario(@PathVariable("usuarioId") Long usuarioId) {
        var isRemoved = usuarioService.delete(usuarioId);

        if(isRemoved){
            return "Usuário id #"+usuarioId+" excluido com sucesso";
        }
        else{
            return "Usuário não excluido";
        }
    }


}

