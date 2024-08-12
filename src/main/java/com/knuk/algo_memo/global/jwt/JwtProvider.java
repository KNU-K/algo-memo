package com.knuk.algo_memo.global.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
    private Long accessExpiration;

    @Value("${jwt.expiration.refresh}")
    private Long refreshExpiration;

    // Access Token 생성
    public String generateAccessToken(String username) {
        return generateToken(username, accessExpiration, accessSecretKey);
    }

    // Refresh Token 생성
    public String generateRefreshToken(String username) {
        return generateToken(username, refreshExpiration, refreshSecretKey);
    }

    // 토큰 생성
    private String generateToken(String username, long expiration, String secretKey) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Access Token에서 사용자 이름 추출
    public String getUsernameFromAccessToken(String token) {
        return extractUsername(token, accessSecretKey);
    }

    // Refresh Token에서 사용자 이름 추출
    public String getUsernameFromRefreshToken(String token) {
        return extractUsername(token, refreshSecretKey);
    }

    // 토큰에서 사용자 이름 추출
    private String extractUsername(String token, String secretKey) {
        return getClaimsFromToken(token, secretKey).getSubject();
    }

    // Access Token 유효성 검사
    public boolean validateAccessToken(String token) {
        return validateToken(token, accessSecretKey);
    }

    // Refresh Token 유효성 검사
    public boolean validateRefreshToken(String token) {
        return validateToken(token, refreshSecretKey);
    }

    // 토큰 유효성 검사
    private boolean validateToken(String token, String secretKey) {
        Claims claims = getClaimsFromToken(token, secretKey);
        return !claims.getExpiration().before(new Date());
    }

    // 토큰에서 Claims 추출
    private Claims getClaimsFromToken(String token, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }
}
