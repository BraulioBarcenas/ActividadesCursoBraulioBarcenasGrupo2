package com.braulio.tienda.config;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenFilter extends OncePerRequestFilter{
    
    private String secret;

    public JwtTokenFilter(String s){
        this.secret = s;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

                String tokenHeader = request.getHeader(HttpHeaders.AUTHORIZATION);


                if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                    String token = tokenHeader.split(" ")[1].trim();

                    try {
                        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

                        Jws<Claims> claims = Jwts.parserBuilder()
                            .setSigningKey(secretKeyBytes)
                            .build()
                            .parseClaimsJws(token);


                        String username = claims.getBody().getSubject();

                        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null, Collections.emptyList());

                            SecurityContextHolder.getContext().setAuthentication(auth);
                        }
                    } catch (Exception e) {
                        
                        e.printStackTrace();
                        SecurityContextHolder.clearContext();
                    }

                }

                filterChain.doFilter(request, response);
            }
    
}
