package com.nexushr.security;
import java.security.Key;
//import java.security.Permission;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//import java.util.Set;

import org.springframework.stereotype.Component;

import com.nexushr.entity.UserAuth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Component

public class JWTUtil {
	private final Key key;
	private final long expireTokenTime= 1000L *60 * 60 * 12;
	
	public JWTUtil() {
		
		String secretKey = System.getenv("JWT_SECRET_KEY");

		if (secretKey == null || secretKey.isEmpty()) {
		    throw new IllegalStateException("JWT_SECRET_KEY is not configured");
		}

		key = Keys.hmacShaKeyFor(secretKey.getBytes());
		
	}
	
	public String generatedToken(UserAuth user) {
		Map<String,Object>claims= new HashMap<>();
		claims.put("role",user.getRole().name());
//		Set<Permission>Permissions= RoleBasedPermission.getRoleBasedPermission().get(user.getRole());
		
		Date now = new Date ();
		Date expire = new Date(now.getTime()+expireTokenTime);
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUserEmail())
				.setIssuedAt(now)
				.setExpiration(expire)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
	}

	public boolean validToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;

        } catch (JwtException e) {

            return false;
        }
    }

    public Claims getClaims(String token) {

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String getUserEmail(String token) {

        return getClaims(token).getSubject();
    }
    
    public String extractToken(String header) {
    	if (header != null && header.startsWith("Bearer ")) {
    		return header.substring(7);
    	}
    	return null;
    }
    
}
