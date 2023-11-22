package com.braulio.tienda.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;
import javax.crypto.SecretKey;

public class GenerateSecret {
    public static void main(String[] args) {
        // Genera una clave secreta usando el algoritmo HMAC SHA-512
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Codifica la clave secreta en formato Base64
        // 'key.getEncoded()' devuelve la clave en formato de bytes
        // 'Base64.getEncoder().encodeToString' convierte los bytes a una cadena en Base64
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());

        // Imprime la clave codificada en Base64 en la consola
        System.out.println(base64Key);
    }
}