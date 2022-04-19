package gestao.matriculas.security;

import gestao.matriculas.domain.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;


public abstract class JwtTokenService {

    private static final long EXPIRATION_TIME = 86400000L; // 1 day
    private static final String SECRET_KEY = "$2a$12$WJ1VqIkxJxEH2EwGdWPf5OR4M.1gYFS/3LXLc9Q5pFEXp5M71XJuW";
    public static final String HEADER_REQUEST = "Authentication";
    public static final String TOKEN_PREFIX = "Bearer ";

    // gera um token jwt
    public static String generateToken(Usuario usuario) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("sub",usuario.getLogin());
        claims.put("email", usuario.getEmail());
        claims.put("perfis", usuario.getPerfis());
        claims.put(("exp"), new Date((System.currentTimeMillis()) + EXPIRATION_TIME));

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    // carrega os claims de um token jwt
    public static Claims getClaimsFromToken(String token) throws ExpiredJwtException {

        return Jwts
                .parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // checa se um token é válido
    public static boolean isTokenValid(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expTime = claims.getExpiration();
            Date currentTime = new Date(System.currentTimeMillis());
            return !currentTime.after(expTime);
        } catch (Exception e) {
            return false;
        }
    }

    // parseia o claim subject de um token
    public static String getLoginFromToken(String token) throws ExpiredJwtException {
        return (String) getClaimsFromToken(token).getSubject();
    }


}

