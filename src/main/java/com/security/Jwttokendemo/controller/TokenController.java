package com.security.Jwttokendemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.Jwttokendemo.model.JwtUser;
import com.security.Jwttokendemo.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	@Autowired
	private JwtGenerator jwtGenerator;

	@PostMapping
	public String generate(@RequestBody JwtUser jwtUser) {
		
		 jwtGenerator = new JwtGenerator();
	return	jwtGenerator.generate(jwtUser);
		
	}
}
