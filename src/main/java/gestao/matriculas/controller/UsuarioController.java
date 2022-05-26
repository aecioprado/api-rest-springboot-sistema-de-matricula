package gestao.matriculas.controller;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.dto.UsuarioDTO;
import gestao.matriculas.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping()
    public List<Usuario> list(){
        return usuarioService.getAll();
    }

    @GetMapping("/pagination")
    public Page<Usuario> paginatedList(
    @RequestParam (value = "page", defaultValue = "0", required = false) Integer page,
    @RequestParam (value = "size", defaultValue = "5", required = false) Integer size){

        PageRequest pageRequest = PageRequest.of(page, size);
        //PageRequest pageRequest = PageRequest.of(Integer.parseInt(page), Integer.parseInt(size));
        return usuarioService.getWithPagination(pageRequest);
    }

    @GetMapping("/{usuarioId}")
    public Usuario listById(@PathVariable("usuarioId") Long usuarioId) {
        return usuarioService.getOneById(usuarioId);
    }

    @PostMapping()
    public Usuario save(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }

    @PutMapping("/{usuarioId}")
    public Usuario edit(@PathVariable("usuarioId") Long usuarioId, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.update(usuarioId, usuarioDTO);
    }

    @DeleteMapping("/{usuarioId}")
    public void delete(@PathVariable("usuarioId") Long usuarioId) {
       usuarioService.delete(usuarioId);
    }


}

