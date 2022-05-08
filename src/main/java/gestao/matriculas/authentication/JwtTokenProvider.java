package gestao.matriculas.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import gestao.matriculas.domain.UsuarioLogin;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Component
public class JwtTokenProvider {

    //#1
    public String generateJwtToken(UsuarioLogin usuarioLogin) {
        String[] roles = getRolesFromUser(usuarioLogin);
        return JWT.create()
                .withIssuedAt(new Date())
                .withSubject(usuarioLogin.getUsername())
                .withArrayClaim(JwtUtil.AUTHORITIES, roles)
                .withExpiresAt(new Date(System.currentTimeMillis()+JwtUtil.EXPIRATION_TIME))
                .sign(Algorithm.HMAC512(JwtUtil.SECRET_KEY));
    }

    //#2
    public List<GrantedAuthority> getRoles(String token){
        String[] rolesFromToken = getRolesFromToken(token);
        return stream(rolesFromToken).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    //#3
    public Authentication getAuthentication(String username, List<GrantedAuthority> roles, HttpServletRequest request){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                username, null, roles);
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return usernamePasswordAuthenticationToken;
    }

    //#4
    public boolean isTokenValid(String username, String token){
        JWTVerifier verifier = getJWTVerifier();
        return StringUtils.hasLength(username) && !isTokenExpired(verifier, token);
        }

    //#5
    public String getSubject(String token){
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token){
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private String[] getRolesFromToken(String token) {
        JWTVerifier verifier = getJWTVerifier();
        return verifier.verify(token).getClaim(JwtUtil.AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWTVerifier() {
        JWTVerifier verifier;
        try {
            Algorithm algorithm = Algorithm.HMAC512(JwtUtil.SECRET_KEY);
            verifier = JWT.require(algorithm).build();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException(JwtUtil.TOKEN_CHECK_MSG);
        }
        return verifier;
    }

    private String[] getRolesFromUser(UsuarioLogin usuarioLogin) {
        List<String> roles = new ArrayList<>();
        for(GrantedAuthority grantedAuthority: usuarioLogin.getAuthorities()){
            roles.add(grantedAuthority.getAuthority());
        }
        return roles.toArray(new String[0]);
    }




}

