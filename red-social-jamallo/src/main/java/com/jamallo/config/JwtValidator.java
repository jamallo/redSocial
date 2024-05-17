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

public class JwtValidator extends OncePerRequestFilter {

    private JwtProvider jwtProvider = new JwtProvider();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith("Bearer ")) {
            try {
                String email = jwtProvider.getEmailFromJwtToken(jwt);

                if (email != null) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (Exception e) {
                throw new BadCredentialsException("Token inválido", e);
            }
        }

        chain.doFilter(request, response);
    }
}

/** package com.jamallo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidator extends OncePerRequestFilter {
	

    // Instancia de JwtProvider
    private JwtProvider jwtProvider = new JwtProvider();
    
	@Override
	public void doFilterInternal (HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain
			) throws ServletException, IOException {


		String jwt=request.getHeader(JwtConstant.JWT_HEADER);
		
		if(jwt != null) {
			
			try {
				String email = jwtProvider.getEmailFromJwtToken(jwt);
				
				List<GrantedAuthority>authorities = new ArrayList<>();
				
				Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (Exception e) {
				throw new BadCredentialsException("token inválido ...");
			}
		}
		
		
		
		chain.doFilter(request, response);
	}

}
*/
