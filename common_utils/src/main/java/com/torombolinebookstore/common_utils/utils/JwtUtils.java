package com.torombolinebookstore.common_utils.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class JwtUtils {
    public static final String SECRET = "AIBrXo8ooyO67vclBurmca7levUkhucxvWin7h9I/MqNHieINSzMdVW60w/GHrqNe43Xm5rJA2Z4DB4/SBZG4g==";

    public static String generateToken(String email) { // Use email as username
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    private static String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))//expiration each 30 minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    private static Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static void proofOfConcept(){
        //I can use this to generate a key
        Key secretKeyGenerated = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        //Then store it as a string
        String custom_secret = Encoders.BASE64.encode(secretKeyGenerated.getEncoded());
        System.out.println("Custom secret: " + custom_secret);
        // And then do this to generate the key again
        byte[] keyBytes = Decoders.BASE64.decode(custom_secret);
        Key second_key= Keys.hmacShaKeyFor(keyBytes);
        //This demonstrates that the key is the same
        String custom_secret2 = Encoders.BASE64.encode(second_key.getEncoded());
        System.out.println("Custom secret2: " + custom_secret2);
    }

    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public static Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
