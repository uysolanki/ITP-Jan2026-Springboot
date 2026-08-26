	package com.itp.ITPJan2026Springboot.security;
	
	import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.http.HttpServletResponse;
	
	@EnableWebSecurity
	@Configuration
	public class MyWebSecurity3 {
	
	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
	        http
	            .authenticationProvider(myAuthenticationProvider())
	
	            .authorizeHttpRequests(auth -> auth
	
	                // React pages / login API
	                .requestMatchers(
	                    "/productui/login",
	                    "/productui/403"
	                ).permitAll()
	
	                // Product APIs
	                .requestMatchers(
	                    "/productui/addProductForm",
	                    "/productui/showProducts",
	                    "/productui/addSingleProduct"
	                ).hasAnyAuthority("USER", "ADMIN")
	
	                .requestMatchers(
	                    "/productui/deleteProductUI/**",
	                    "/productui/updateProductUI/**",
	                    "/productui/update-product/**"
	                ).hasAuthority("ADMIN")
	
	                .anyRequest().authenticated()
	            )
	
	            // Disable Spring Security's default login page
	            .formLogin(form -> form.disable())
	
	            // Disable browser popup/basic authentication
	            .httpBasic(basic -> basic.disable())
	
	            .logout(logout -> logout
	            	    .logoutUrl("/logout")
	            	    .logoutSuccessHandler((request, response, authentication) -> {
	            	        response.setStatus(HttpServletResponse.SC_OK);
	            	        //response.getWriter().write("Logout successful");
	            	    })
	            	    .permitAll()
	            	)
	
	            .cors(cors -> {})
	
	            .csrf(csrf -> csrf.disable());
	
	        return http.build();
	    }
	
	
	    @Bean
	    public AuthenticationProvider myAuthenticationProvider() {
	
	        DaoAuthenticationProvider dao =
	                new DaoAuthenticationProvider(mySetUserDetailsService());
	
	        dao.setPasswordEncoder(mySetPasswordEncoder());
	
	        return dao;
	    }
	
	
	    @Bean
	    public PasswordEncoder mySetPasswordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	
	
	    @Bean
	    public UserDetailsService mySetUserDetailsService() {
	        return new MyUserDetailsService();
	    }
	    
	    @Bean
	    public AuthenticationManager authenticationManager(
	            AuthenticationConfiguration configuration) throws Exception {

	        return configuration.getAuthenticationManager();
	    }
	}