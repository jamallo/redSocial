package com.jamallo.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtProvider {

    private static final String SECRET_KEY = "your-secret-key-your-secret-key-your-secret-key"; // Usa una clave secreta adecuada
    private static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    public String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("Codewithjamallo")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }

    public String getEmailFromJwtToken(String jwt) {
        // Elimina el prefijo "Bearer " si está presente
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return String.valueOf(claims.get("email"));
    }
}


/**package com.jamallo.config;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtProvider {
    
    private static final String SECRET_KEY_ALGORITHM = "HmacSHA256";
    
    private SecretKey secretKey;
    
    public JwtProvider() {
        this.secretKey = generateSecretKey();
    }
    
    private SecretKey generateSecretKey() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(SECRET_KEY_ALGORITHM);
            // Configura la longitud de la clave, por ejemplo, 256 bits
            keyGen.init(256);
            return keyGen.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar la clave secreta", e);
        }
    }

    public String generateToken(Authentication auth) {
        
        String jwt = Jwts.builder()
                .setIssuer("Codewithjamallo")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .claim("email", auth.getName())
                .signWith(secretKey)
                .compact();
        
        return jwt;
    }
    
    public String getEmailFromJwtToken(String jwt) {
        // Elimina el prefijo "Bearer " si está presente
        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }
        
        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
        
        return String.valueOf(claims.get("email"));
    }
}
*/
/** package com.jamallo.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;


public class JwtProvider {
	
	private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public static String generateToken(Authentication auth) {
		
		String jwt = Jwts.builder()
				.setIssuer("Codewithjamallo")
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 86400000))
				.claim("email", auth.getName())
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public static String getEmailFromJwtToken (String jwt) {
		// portador token
		jwt = jwt.substring(7);
		
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.build()
				.parseClaimsJws(jwt)
				.getBody();
		
		String email = String.valueOf(claims.get("email"));
		
		
		return email;
	}

}

*/
