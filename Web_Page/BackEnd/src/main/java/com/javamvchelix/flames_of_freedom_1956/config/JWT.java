/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.config;

import com.javamvchelix.flames_of_freedom_1956.exception.ExceptionLogger;
import com.javamvchelix.flames_of_freedom_1956.model.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

/**
 *
 * @author shado
 */
public class JWT {
    
    private static final String SIGN = "09ce78e64c7d6667e04798aa897e2bbc194d0ce5d19aef677b4477ba0932d972";
    private static final byte[] SECRET = Base64.getDecoder().decode(SIGN);
    private static ExceptionLogger exceptionLogger = new ExceptionLogger(JWT.class);
    
    /**
     *
     * @param u
     * @return
     */
    public static String createJWT(Users u) {
        Instant now = Instant.now();
    
        String token = Jwts.builder()
                .setIssuer("FlamesOfFreedom")
                .setSubject("valamit")
                .claim("id", u.getId())
                .claim("isAdmin", u.getIsAdmin())
                .claim("registeredAt", u.getRegisteredAt())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .signWith(
                        SignatureAlgorithm.HS256,
                        Decoders.BASE64.decode(SIGN)
                )
                .compact();
        
        return token;
    }

    public static int validateJWT(String jwt) {
        try {
            Jws<Claims> result;
            result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET)).parseClaimsJws(jwt);
            
            int id = result.getBody().get("id", Integer.class);
            Users u = new Users(id);
            
            if(u.getId() == id) {
                return 1;
            } else {
                return 2;
            }
        } catch (Exception e) {
            exceptionLogger.errorLog(e);
            return 3;
        }
    }
    
    public static Boolean isAdmin(String jwt) {
        Jws<Claims> result;
        result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET)).parseClaimsJws(jwt);
        
        Boolean isAdmin = result.getBody().get("isAdmin", Boolean.class);
        
        return isAdmin;
    }
    
    public static Integer getUserIdByToken(String jwt){
        Jws<Claims> result;
        result = Jwts.parser().setSigningKey(Keys.hmacShaKeyFor(SECRET)).parseClaimsJws(jwt);
        
        int userId = result.getBody().get("id", Integer.class);
        
        return userId;
    }
}
