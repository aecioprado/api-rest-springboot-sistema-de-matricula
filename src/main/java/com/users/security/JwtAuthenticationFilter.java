package com.users.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// representa o filtro de autenticação executado em cada requisição disparado ao servlet
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        // verifica se na requisição o cabeçalho AUTHORIZATION é nulo ou começa diferente de Bearer
        if (authorizationHeader == null || !authorizationHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        // tenta verificar se o token é valido
        try {
            String token = authorizationHeader.split(" ")[1];
            String username = jwtTokenProvider.getSubject(token);
            if (jwtTokenProvider.isTokenValid(username, token)) {
                List<GrantedAuthority> roles = jwtTokenProvider.getRoles(token);
                Authentication authentication = jwtTokenProvider.getAuthentication(username, roles, request);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        } catch (JWTVerificationException exception){
            throw new JWTVerificationException("Erro ao processar o token");
        }

        filterChain.doFilter(request, response);
    }

}
