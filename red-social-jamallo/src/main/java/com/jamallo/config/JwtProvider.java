package com.jamallo.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

// Clase JwtProvider para manejar la generación y validación de tokens JWT.
// Contiene métodos para generar tokens y extraer información de los mismos.

public class JwtProvider {

	// Definición de la clave secreta utilizada para firmar y verificar los tokens JWT.
	
    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key";
    
    // Generación de una clave SecretKey utilizando la clave secreta y el algoritmo HMAC SHA-256.
    
    private static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    /**
     * Generación de un token JWT para la autenticación.
     * 
     * @param auth El objeto Authentication que contiene los detalles del usuario autenticado.
     * @return El token JWT generado.
     */
    
    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("Codewithjamallo") // Establece el emisor del token
                .setIssuedAt(new Date()) // Establece la fecha de emisión del token
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // Establece la fecha de expiración del token (24 horas)
                .claim("email", auth.getName()) // Añade una reclamación con el email del usuario
                .signWith(key) // Firma el token con la clave secreta
                .compact(); // Construye y compacta el token en una cadena JWT
        return jwt;
    }

    /**
     * Extraer el email del token JWT.
     * 
     * @param jwt El token JWT del cual se extraerá el email.
     * @return El email extraído del token JWT.
     */
    
    public String getEmailFromJwtToken(String jwt) {
        // Elimina el prefijo "Bearer " si está presente en el token
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

     // Parsear el token JWT para extraer las reclamaciones (claims)
        
        Claims claims = Jwts.parser()
                .setSigningKey(key) // Establece la clave secreta para la validación del token
                .build()
                .parseClaimsJws(jwt) // Parsea el token y obtiene el cuerpo (claims)
                .getBody();

        return String.valueOf(claims.get("email")); // Retorna el email desde las reclamaciones
    }
}


