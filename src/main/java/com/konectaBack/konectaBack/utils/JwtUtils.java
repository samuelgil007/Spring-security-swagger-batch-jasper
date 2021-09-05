package com.konectaBack.konectaBack.utils;

import com.konectaBack.konectaBack.Constants.UtilConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;

import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
public class JwtUtils {

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(UtilConstants.JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpirationDate(token).before(new Date());
    }

    public static String generarToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        Calendar fechaExpiracion = Calendar.getInstance();
        fechaExpiracion.setTime(new Date());
        fechaExpiracion.add(Calendar.HOUR_OF_DAY, 1);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(fechaExpiracion.getTime())
                .signWith(SignatureAlgorithm.HS256, UtilConstants.JWT_SECRET)
                .compact();
    }

    public static boolean validarToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}