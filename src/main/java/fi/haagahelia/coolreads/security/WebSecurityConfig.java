package fi.haagahelia.coolreads.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
				.requestMatchers(
								antMatcher("/"), 
								antMatcher("/register"), 
								antMatcher("/frontend/**"),
								antMatcher("/api/**"), 
								antMatcher("/error"), 
								antMatcher("/recommendationlist"),
								antMatcher("/categorylist"))
				.permitAll().anyRequest().authenticated());

		http.formLogin((form) -> form.loginPage("/login").permitAll());
		http.logout((logout) -> logout
									.logoutRequestMatcher(antMatcher("/logout"))
									.permitAll());

		return http.build();
	}
}