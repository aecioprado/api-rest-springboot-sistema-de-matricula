package gestao.matriculas.authentication;

public abstract class JwtUtil {
    public static final long EXPIRATION_TIME = 86400000L; // 1 day
    public static final String SECRET_KEY = "nC8g55NlrvXnHpIM4TwCgFJt55FT3eMgHG3nxYGkZuA1YUSZuzppZirQH9q9946VwoXy9RQz9KScl4sgoDBMV4ffzOOTVysVFzD4n8kS9en0xXsMDcNy6ZuNWPjJ5RRMVjImZqe2CwJ2PJNLQTIn5qBu5bimpyFfdC74W6U";
    public static final String JWT_TOKEN_HEADER = "acess_token";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_CHECK_MSG = "Token não pode ser validado";
    public static final String FORBIDDEN_MSG = "Você precisa estar logado para acessar esta página";
    public static final String DENIED_MSG = "Você não tem permissão para acessar esta página";
    public static final String[] PUBLIC_URLS = {"/","/login","login/registro","login/recupera-senha/**"};
    public static final String AUTHORITIES = "roles";



}
