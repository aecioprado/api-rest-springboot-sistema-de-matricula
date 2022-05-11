package gestao.matriculas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ApiRestMatriculaApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiRestMatriculaApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiRestMatriculaApplication.class, args);
		
		
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}