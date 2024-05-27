package com.jamallo.config;



import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

// Esta clase es una configuración de seguridad para la aplicación Spring.

@Configuration // indica que es una clase de configuración
@EnableWebSecurity //habilita la seguridad web en la aplicación
public class AppConfig {
	
	// Configuración de la cadena de filtros de seguridad para HTTP.

	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		/* -> Configuración de la política de creación de sesiones como "stateless" (sin estado), 
		 * lo cual es común en aplicaciones que usan JWT para manejar la autenticación.
		 * -> Definición de las reglas de autorización. 
		 */
		http.sessionManagement(management -> management.sessionCreationPolicy(
				SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(Authorize -> Authorize
				.requestMatchers("/api/**").authenticated() // Requiere autenticación para rutas que empiecen con "/api/"
				.anyRequest().permitAll()) // Permite todas las demás rutas sin autenticación
		.addFilterBefore(new JwtValidator(), BasicAuthenticationFilter.class) // Añade el filtro JWT antes del filtro de autenticación básica
		.csrf(csrf -> csrf.disable()) // Deshabilita la protección token CSRF (Cross Site Request Forgery)
		.cors(cors -> cors.configurationSource(corsConfigurationSource())); // Habilita la configuración CORS (Cross Origin Resource Sharing)
		// permite interacciones entre recursos de diferentes orígenes, algo que normalmente está prohibido para prevenir comportamientos maliciosos
		
		return http.build();
	}
	
	// Configuración de las políticas de CORS (Cross-Origin Resource Sharing) para la aplicación.
	
private CorsConfigurationSource corsConfigurationSource() {

		return new CorsConfigurationSource() {
			
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {

				CorsConfiguration cfg = new CorsConfiguration();
				cfg.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000",
						"http://localhost:4000",
						"http://localhost:4200",
						"http://jamallo.app")); // Permite solicitudes desde esta URL de origen
				cfg.setAllowedMethods(Collections.singletonList("*")); // Permite todos los métodos HTTP
				cfg.setAllowCredentials(true); // Permite el uso de credenciales
				cfg.setAllowedHeaders(Collections.singletonList("*")); // Permite todos los encabezados
				cfg.setExposedHeaders(Arrays.asList("Authorization")); // Expone el encabezado de autorización
				cfg.setMaxAge(3600L); // Cachea la configuración por 3600 segundos (1 hora)
				
				return cfg;
			}
		};
	}

// Definición del bean PasswordEncoder que usará BCrypt para codificar contraseñas.

@Bean	
PasswordEncoder passwordEncoder() {
	return new BCryptPasswordEncoder(); // Define BCrypt como el codificador de contraseñas
}
}
