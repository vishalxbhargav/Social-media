package com.vishalxbhargav.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key= Keys.hmacShaKeyFor(jwtConstent.SECTET_KEY.getBytes());
    public static String generateToken(Authentication auth){
        String jwt= Jwts.builder()
                .setIssuer("vishalxbhargav").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }
    
    public static String getEmailFromJwtToken(String jwt){
        //Bearer token
        jwt=jwt.substring(7);
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}
