//package claiborne.blog.api.springboot.security;
//
//import claiborne.blog.api.springboot.exception.BlogAPIException;
//import io.jsonwebtoken.*;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//
//  @Value("${app.jwt.secret}")
//  private String jwtSecret;
//  @Value("${app.jwt.expiration.milliseconds}")
//  private int jwtExpireMs;
//
//  public String generateToken(Authentication authentication) {
//    String username = authentication.getName();
//    Date currentDate = new Date();
//    Date expireDate = new Date(currentDate.getTime() + jwtExpireMs);
//
//    return Jwts.builder()
//        .setSubject(username)
//        .setIssuedAt(new Date())
//        .setExpiration(expireDate)
//        .signWith(SignatureAlgorithm.HS512, jwtSecret)
//        .compact();
//  }
//
//  // get username from token
//  public String getUsernameFromJWT(String token) {
//    Claims claims = Jwts.parser()
//        .setSigningKey(jwtSecret)
//        .parseClaimsJws(token)
//        .getBody();
//    return claims.getSubject();
//  }
//
//  // validate JWT token
//  public boolean validateToken(String token) {
//    try {
//      Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
//    } catch (SignatureException e) {
//      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT signature");
//    } catch (MalformedJwtException e) {
//      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT token");
//    } catch (ExpiredJwtException e) {
//      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT token");
//    } catch (UnsupportedJwtException e) {
//      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT token");
//    } catch (IllegalArgumentException e) {
//      throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Illegal Argument or empty string");
//    }
//    return true;
//  }
//
//}
