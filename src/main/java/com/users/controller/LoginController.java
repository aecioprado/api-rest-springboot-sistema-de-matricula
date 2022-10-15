package com.users.controller;

import com.users.domain.model.Usuario;
import com.users.security.UsuarioDetails;
import com.users.security.JwtTokenProvider;
import com.users.security.JwtTokenUtil;
import com.users.domain.dto.LoginDto;
import com.users.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RequestMapping("/login")
@RestController
public class LoginController {

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    @PostMapping()
    public ResponseEntity<LoginDto> login(@RequestBody Usuario usuario, HttpServletResponse response){

        authenticate(usuario.getLogin(), usuario.getSenha());

        Usuario usuarioLogado = usuarioService.findByLogin(usuario.getLogin());


        UsuarioDetails usuarioDetails = new UsuarioDetails(usuarioLogado);

        String token = jwtTokenProvider.generateToken(usuarioDetails);

        response.addHeader(JwtTokenUtil.JWT_TOKEN_HEADER, token);

        LoginDto loginDTO = new LoginDto(usuario.getLogin(),token);

        return new ResponseEntity<>(loginDTO, HttpStatus.OK);
    }

    private void authenticate(String login, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
    }

    @PostMapping("/cadastro")
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
}
