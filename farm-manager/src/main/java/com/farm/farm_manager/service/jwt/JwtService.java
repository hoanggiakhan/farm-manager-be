package com.farm.farm_manager.service.jwt;

import com.farm.farm_manager.entity.Employee;
import com.farm.farm_manager.entity.Role;
import com.farm.farm_manager.service.user.UserSecurityServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
@Component
public class JwtService {
    @NonFinal
    @Value("${jwt.signerKey}")
    private String signerKey;
    @Autowired
    UserSecurityServiceImpl userSecurityService;
    public String generateToken(String username){
        Map<String,Object> claims = new HashMap<>();
        Employee user = userSecurityService.findByUsername(username);
        claims.put("id", user.getEmployeeId());
        claims.put("lastName", user.getLastName());
        Set<Role> roles = user.getRoles();
        if (roles.size() > 0) {
            for (Role role : roles) {
                if (role.getRoleName().equals("ADMIN")) {
                    claims.put("role", "ADMIN");
                    break;
                }
                if (role.getRoleName().equals("CUSTOMER")) {
                    claims.put("role", "CUSTOMER");
                    break;
                }
            }
        }
        return createToken(claims , username);
    }

    public String createToken(Map<String,Object> claims , String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(SignatureAlgorithm.HS256,signerKey)
                .compact();
    }

    public String extractUsername(String token){
        return extractClaim(token , Claims :: getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(signerKey).parseClaimsJws(token).getBody();
    }

   public Boolean validateToken(String token, String username){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
