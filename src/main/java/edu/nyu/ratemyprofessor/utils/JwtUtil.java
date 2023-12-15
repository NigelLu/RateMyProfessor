// package edu.nyu.ratemyprofessor.utils;

// import io.jsonwebtoken.*;
// import io.jsonwebtoken.security.Keys;
// import org.springframework.stereotype.Component;
// import java.security.Key;
// import java.util.Date;

// @Component
// public class JwtUtil {
//   private String secretKey = "super-duper-secret-key-that-no-one-will-guess"; // Should be externalized

//   private Key getSigningKey() {
//     byte[] keyBytes = secretKey.getBytes();
//     return Keys.hmacShaKeyFor(keyBytes);
//   }

//   public String generateToken(String username) {
//     long now = System.currentTimeMillis();
//     return Jwts.builder()
//         .subject(username)
//         .issuedAt(new Date(now))
//         .expiration(new Date(now + 1800000)) // Token validity (30 minutes)
//         .signWith(getSigningKey())
//         .compact();
//   }

  // public Claims extractAllClaims(String token) throws ExpiredJwtException {
  // return Jwts.parser().verifyWith((SecretKey)
  // getSigningKey()).parseClaimsJws(token).getBody();
  // }

  // public String extractUsername(String token) {
  // return extractAllClaims(token).getSubject();
  // }

  // public boolean isTokenExpired(String token) {
  // return extractAllClaims(token).getExpiration().before(new Date());
  // }

  // public boolean validateToken(String token, String username) {
  // final String tokenUsername = extractUsername(token);
  // return (username.equals(tokenUsername) && !isTokenExpired(token));
  // }
// }
