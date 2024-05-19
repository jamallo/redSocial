package com.jamallo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Filtro JwtValidator que valida el token JWT en cada solicitud HTTP.

public class JwtValidator extends OncePerRequestFilter {

	// Instancia de JwtProvider utilizada para manejar los tokens JWT.
    private JwtProvider jwtProvider = new JwtProvider();
    
    /**
     * Método principal del filtro que se ejecuta para cada solicitud HTTP.
     * 
     * @param request -> La solicitud HTTP entrante.
     * @param response -> La respuesta HTTP saliente.
     * @param chain -> La cadena de filtros a la que se debe pasar la solicitud.
     * @throws ServletException -> Si ocurre un error en el filtro.
     * @throws IOException -> Si ocurre un error de E/S.
     */

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

    	// Obtener el token JWT del encabezado de la solicitud HTTP.
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // Verifica si el token no es nulo y tiene el prefijo "Bearer ".
        if (jwt != null && jwt.startsWith("Bearer ")) {
            try {
            	// Extraer el email del token JWT utilizando JwtProvider.
                String email = jwtProvider.getEmailFromJwtToken(jwt);

             // Si el email es válido, configurar la autenticación en el contexto de seguridad.
                if (email != null) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
            	// Lanzar una excepción de credenciales incorrectas si el token es inválido.
                throw new BadCredentialsException("Token inválido", e);
            }
        }

        // Continuar con la cadena de filtros.
        chain.doFilter(request, response);
    }
}

