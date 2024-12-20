package com.revature.p1.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //@Value("${app.jwt-secret}")
    private Key jwtSecret= Keys.secretKeyFor(SignatureAlgorithm.HS256) ;

    //@Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate = 3600000;


    public String generateToken(Authentication authentication){

        String username = authentication.getName();

        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(jwtSecret)
                .compact();

        return token;
    }

    public String getUsername(String token){

        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parse(token);
            return true;
              

    }
}
