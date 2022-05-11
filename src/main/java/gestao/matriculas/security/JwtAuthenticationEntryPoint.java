package gestao.matriculas.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// dá um resposta personalizada a falha na autenticação
@Component
public class JwtAuthenticationEntryPoint extends Http403ForbiddenEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException {
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Usuário não autenticado.");
    }
}
