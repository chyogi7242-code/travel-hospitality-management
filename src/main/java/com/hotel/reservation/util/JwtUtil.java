package com.hotel.reservation.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {

    private static final String SECRET_KEY =
            "hotelReservationSecretKeyForJwtAuthentication2026";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24; // 24 hours

    public String generateToken(String email) {

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + EXPIRATION_TIME
                        )
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY
                )
                .compact();
    }

    public String extractEmail(String token) {

        return extractClaim(
                token,
                Claims::getSubject
        );
    }

    public Date extractExpiration(String token) {

        return extractClaim(
                token,
                Claims::getExpiration
        );
    }

    public <T> T extractClaim(
            String token,
            Function<Claims, T> claimsResolver
    ) {

        final Claims claims =
                Jwts.parser()
                        .setSigningKey(SECRET_KEY)
                        .parseClaimsJws(token)
                        .getBody();

        return claimsResolver.apply(claims);
    }

    public boolean isTokenValid(
            String token,
            String email
    ) {

        return extractEmail(token).equals(email)
                && !extractExpiration(token).before(new Date());
    }
}