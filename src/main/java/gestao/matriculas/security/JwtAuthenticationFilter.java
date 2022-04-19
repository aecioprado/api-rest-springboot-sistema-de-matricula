package gestao.matriculas.security;

import gestao.matriculas.service.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsServiceImpl userDetailsService;

    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws ServletException, IOException {

            String authorization = request.getHeader("Authorization");

            if(authorization != null && authorization.startsWith("Bearer")){
                String token = authorization.split(" ")[1];
                boolean isValido = JwtTokenService.isTokenValid(token);

                if(isValido){
                   String loginUsuario = JwtTokenService.getLoginFromToken(token);

                   UserDetails usuario = userDetailsService.loadUserByUsername(loginUsuario);

                   UsernamePasswordAuthenticationToken user = new
                   UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());

                   user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                   SecurityContextHolder.getContext().setAuthentication(user);

                }
            }

            filterChain.doFilter(request,response);
    }
}
