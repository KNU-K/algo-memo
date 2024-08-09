package com.knuk.algo_memo.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@PropertySource("classpath:jwt.properties")
public class JwtProvider {
    @Value("${jwt.secret.access}")
    private String accessSecretKey;

    @Value("${jwt.secret.refresh}")
    private String refreshSecretKey;

    @Value("${jwt.expiration.access}")
    private long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private long refreshExpiration;

    @PostConstruct
    protected void init() {
        // Optionally initialize anything after the properties are injected
    }

    // Generate Access Token
    public String generateAccessToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(SignatureAlgorithm.HS256, accessSecretKey)
                .compact();
    }

    // Generate Refresh Token
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(SignatureAlgorithm.HS256, refreshSecretKey)
                .compact();
    }

    // Extract Claims from Token
    private Claims getClaimsFromToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Get Username from Access Token
    public String getUsernameFromAccessToken(String token) {
        Claims claims = getClaimsFromToken(token, accessSecretKey);
        return claims.getSubject();
    }

    // Get Username from Refresh Token
    public String getUsernameFromRefreshToken(String token) {
        Claims claims = getClaimsFromToken(token, refreshSecretKey);
        return claims.getSubject();
    }

    // Validate Access Token
    public boolean validateAccessToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token, accessSecretKey);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    // Validate Refresh Token
    public boolean validateRefreshToken(String token) {
        try {
            Claims claims = getClaimsFromToken(token, refreshSecretKey);
            return !claims.getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
