//package com.example.javazon.security;
//
//import com.example.javazon.entities.User;
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//@Component
//public class JwtUtil {
//    @Autowired
//    User user;
//// need to be moved in a secured path
//    private final String SECRET_KEY = "mySecretKey123";
//    private final long EXPIRATION = 1000 * 60 * 60;
//
//    public String generateToken(String username) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("roles", Arrays.asList("USER","ADMIN"));
//        claims.put("userId", user.getUserId());
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
//                .compact();
//   }
//
//   //    check of token validation
//    public boolean validateToken(String token, String username) {
//        return extractUsername(token).equals(username) && !isTokenExpired(token);
//    }
//
//
//    public String extractUsername(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    private boolean isTokenExpired(String token) {
//        return Jwts.parser()
//                .setSigningKey(SECRET_KEY)
//                .parseClaimsJws(token)
//                .getBody()
//                .getExpiration()
//                .before(new Date());
//    }
//}
