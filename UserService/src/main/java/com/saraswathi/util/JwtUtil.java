package com.saraswathi.util;



import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final String SECRET = "my-super-secret-key-my-super-secret-key";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // ✅ GENERATE TOKEN
    public String generateToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)   // 🔥 MUST ADD THIS
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(getKey())
                .compact();
    }
    // ✅ EXTRACT USERNAME
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())   // ✅ FIX
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}