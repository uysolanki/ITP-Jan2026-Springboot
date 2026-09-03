package com.itp.ITPJan2026Springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.itp.ITPJan2026Springboot.filter.JwtRequestFilter;

@EnableWebSecurity
@Configuration
public class MyWebSecurityForJWT {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authenticationProvider(myAuthenticationProvider())

				.authorizeHttpRequests(auth -> auth

						// React pages / login API
						.requestMatchers("/productui/login", "/productui/403", "/authenticate").permitAll()

						// Product APIs
						.requestMatchers("/productui/addProductForm", "/productui/showProducts",
								"/productui/addSingleProduct", "/jwt/readTesting")
						.hasAnyAuthority("USER", "ADMIN")

						.requestMatchers("/productui/deleteProductUI/**", "/productui/updateProductUI/**",
								"/productui/update-product/**", "/jwt/deleteTesting")
						.hasAuthority("ADMIN")

						.anyRequest().authenticated())

				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

				.csrf(csrf -> csrf.disable())

				.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	

	@Bean
	public AuthenticationProvider myAuthenticationProvider() {

		DaoAuthenticationProvider dao = new DaoAuthenticationProvider(myUserDetailsService);

		dao.setPasswordEncoder(mySetPasswordEncoder());

		return dao;
	}

	@Bean
	public PasswordEncoder mySetPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

		return configuration.getAuthenticationManager();
	}
}