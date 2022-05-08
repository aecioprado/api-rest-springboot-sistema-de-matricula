package gestao.matriculas.controller;

import gestao.matriculas.domain.Usuario;
import gestao.matriculas.dto.CredenciaisDto;
import gestao.matriculas.dto.TokenDto;
import gestao.matriculas.authentication.JwtTokenProvider;
import gestao.matriculas.service.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping("auth/token")
    public TokenDto getToken(@RequestBody CredenciaisDto credenciaisDto) {
        try {
            Usuario usuario = new Usuario();
            usuario.setEmail(credenciaisDto.getEmail());
            usuario.setSenha(credenciaisDto.getSenha());
           UserDetails usuarioAutenticado = userDetailsService.autenticacao(usuario);
           String token = JwtTokenProvider.generateToken(usuario);
           return new TokenDto(usuario.getEmail(), token);

        } catch (UsernameNotFoundException e) {
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }


}
