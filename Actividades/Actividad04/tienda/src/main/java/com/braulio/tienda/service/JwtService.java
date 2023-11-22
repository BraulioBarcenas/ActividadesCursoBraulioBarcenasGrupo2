package com.braulio.tienda.service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {
    
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email){
        long expirationTimeInMillis = 3600000;

        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMillis))
                .signWith(SignatureAlgorithm.HS512, secretKeyBytes)
                .compact();
    }
}
