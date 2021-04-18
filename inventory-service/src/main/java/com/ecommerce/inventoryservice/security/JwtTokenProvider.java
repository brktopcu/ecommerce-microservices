package com.ecommerce.inventoryservice.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.ecommerce.inventoryservice.security.SecurityConstants.*;


@Component
@Slf4j
public class JwtTokenProvider {


    public boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        }catch (SignatureException ex ){
            System.out.println("Gecersiz guvenlik sifresi");
            log.error("Gecersiz guvenlik sifresi");
        }catch (MalformedJwtException ex){
            System.out.println("Gecersiz Jwt token");
            log.error("Gecersiz Jwt token");
        }catch (ExpiredJwtException ex){
            System.out.println("Jwt token kullanim süresi doldu");
            log.error("Jwt token kullanim süresi doldu");
        }catch (UnsupportedJwtException ex){
            System.out.println("Jwt token desteklenmiyor");
            log.error("Jwt token desteklenmiyor");
        }catch (IllegalArgumentException ex){
            System.out.println("Jwt claims dolu degil");
            log.error("Jwt claims dolu degil");
        }
        return false;
    }

    public UUID getUserIdFromJwt(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String id = (String)claims.get("id");

        return UUID.fromString(id);
    }

}
