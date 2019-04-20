package com.security.Jwttokendemo.security;

import org.springframework.stereotype.Component;

import com.security.Jwttokendemo.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {

	private String secret ="Vinay";
	public JwtUser validate(String token) {
		JwtUser jwtUser = null;
		try {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		
		 jwtUser = new JwtUser();
		jwtUser.setUserName(claims.getSubject());
		jwtUser.setId(Long.parseLong((String)(claims.get("userId"))));
		jwtUser.setRole((String)claims.get("role"));
		
		System.out.println("claims"+claims);
		}catch (Exception e) {
			System.out.println(e);
		}
		return jwtUser;
	}

}
