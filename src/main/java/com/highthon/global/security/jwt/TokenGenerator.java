package com.highthon.global.security.jwt;

import com.highthon.global.security.jwt.dto.TokenDto;
import com.highthon.global.security.jwt.properties.JwtEnvironment;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class TokenGenerator {

    private final JwtEnvironment jwtEnv;

    private final String TOKEN_TYPE = "tokenType";
    private final String ACCESS_TOKEN = "accessToken";

    public TokenDto generateToken(String userId) {
        return TokenDto.builder()
                .accessToken(generateAccessToken(userId))
                .accessTokenExp(jwtEnv.accessExp())
                .build();
    }

    private String generateAccessToken(String userId) {
        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(jwtEnv.accessSecret().getBytes()), SignatureAlgorithm.HS256)
                .setSubject(userId)
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtEnv.accessExp() * 1000L))
                .compact();
    }

    public static Claims getTokenBody(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
