package com.example.apigateway;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final String SECRET = "TrV7gX9pA4eS0uFbLk6YmJzQ1rW2tHx8";
    private static final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                   .setSigningKey(SECRET_KEY)  // use SecretKey, not string
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
    }

    public static boolean isTokenValid(String token) {
        try {
            getClaims(token); // will throw if invalid
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getUsername(String token) {
        return getClaims(token).getSubject();
    }
    
    public static String generateToken(String username) {
        long expirationMillis = 1000 * 60 * 60; // 1 hour

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationMillis)) // ← here
                .signWith(SECRET_KEY)
                .compact();
    }
}