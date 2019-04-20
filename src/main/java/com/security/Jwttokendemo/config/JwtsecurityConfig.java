package com.security.Jwttokendemo.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.Jwttokendemo.JwtTokenDemoApplication;
import com.security.Jwttokendemo.security.JwtAuthenticationEntryPoint;
import com.security.Jwttokendemo.security.JwtAuthenticationProvider;
import com.security.Jwttokendemo.security.JwtAuthenticationTokenFilter;
import com.security.Jwttokendemo.security.JwtSucessHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class JwtsecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationProvider authenticationProvider;
	@Autowired
	private JwtAuthenticationEntryPoint entrypoint;

	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilter() {
	 	JwtAuthenticationTokenFilter filter = new JwtAuthenticationTokenFilter();
	 	filter.setAuthenticationManager(authenticationManager());
	 	filter.setAuthenticationSuccessHandler(new JwtSucessHandler());
	 	
	 	return filter;
	}
	
	@Bean
	public AuthenticationManager authenticationManager() {
		
		return new ProviderManager(Collections.singletonList(authenticationProvider));
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeRequests().antMatchers("/**/rest/").authenticated()
		.and().exceptionHandling().authenticationEntryPoint(entrypoint)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		http.headers().cacheControl();
	}
	
	
}
