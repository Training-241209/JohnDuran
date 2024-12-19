package com.revature.p1.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    //@Value("${app.jwt-secret}")
    private Key jwtSecret= Keys.secretKeyFor(SignatureAlgorithm.HS256) ;

    //@Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate = 60000;


    // generate JWT token
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

    // private Key key(){
    //     return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    // }

    // get username from JWT token
    public String getUsername(String token){

        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // validate JWT token
    public boolean validateToken(String token){
            Jwts.parserBuilder()
                    .setSigningKey(jwtSecret)
                    .build()
                    .parse(token);
            return true;

    }
}
