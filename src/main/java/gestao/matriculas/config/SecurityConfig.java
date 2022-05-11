package gestao.matriculas.config;

import gestao.matriculas.security.JwtAcessDeniedHandler;
import gestao.matriculas.security.JwtAuthenticationEntryPoint;
import gestao.matriculas.security.JwtAuthenticationFilter;
import gestao.matriculas.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAcessDeniedHandler jwtAcessDeniedHandler;
    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtAcessDeniedHandler jwtAcessDeniedHandler, UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAcessDeniedHandler = jwtAcessDeniedHandler;
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // configura a autenticação
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService).passwordEncoder(bCryptPasswordEncoder);
    }

    // Objeto que gerencia as autenticações
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().cors().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests().antMatchers("/login/**","/login/cadastro","/login/recuperarsenha").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(jwtAcessDeniedHandler) //acesso negado
                .authenticationEntryPoint(jwtAuthenticationEntryPoint) //acesso não autenticado
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
