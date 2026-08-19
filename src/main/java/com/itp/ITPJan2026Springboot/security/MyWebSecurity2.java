package com.itp.ITPJan2026Springboot.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class MyWebSecurity2 {
	

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
    	http.authenticationProvider(myAuthenticationProvider());
    	
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/productui/showProducts",
                    "/productui/addProductForm"
                ).hasAnyAuthority("USER", "ADMIN")

                .requestMatchers(
                    "/productui/deleteProductUI/**",
                    "/productui/updateProductForm/**"
                ).hasAuthority("ADMIN")

                .anyRequest().authenticated()
            )

            .formLogin(form -> form
                .loginProcessingUrl("/login")
                .successForwardUrl("/productui/showProducts")
                .permitAll()
            )

            .logout(logout -> logout
                .logoutSuccessUrl("/login")
                .permitAll()
            )

            .exceptionHandling(exception -> exception
                .accessDeniedPage("/productui/403")
            )

            .cors(cors -> {})

            .csrf(csrf -> csrf.disable());

        return http.build();
    }

   
    @Bean
	public AuthenticationProvider myAuthenticationProvider() {
		DaoAuthenticationProvider dao =new DaoAuthenticationProvider(new MyUserDetailsService());
		dao.setPasswordEncoder(myPasswordEncoder());
		return dao;
	}



    @Bean
   	public PasswordEncoder myPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
