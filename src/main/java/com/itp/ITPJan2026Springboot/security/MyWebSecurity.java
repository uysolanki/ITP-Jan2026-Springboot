//package com.itp.ITPJan2026Springboot.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@EnableWebSecurity
//@Configuration
//public class MyWebSecurityForInMemoryAuthentication {
//	
//	@Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails admin1 = User.builder()
//                .username("jetha")
//                .password(passwordEncoder().encode("jetha123"))
//                .authorities("ADMIN")
//                .build();
//        
//        UserDetails user1 = User.builder()
//                .username("bagha")
//                .password(passwordEncoder().encode("bagha123"))
//                .authorities("USER")
//                .build();
//        
//
//        return new InMemoryUserDetailsManager(admin1, user1);
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////   spring version 3.0    
////    @Bean  
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        
////        http.authorizeRequests()
////        .requestMatchers("/productui/showProducts","/productui/addProductForm").hasAnyAuthority("USER","ADMIN")
////        .requestMatchers("/productui/deleteProductUI/**","/productui/updateProductForm/**").hasAuthority("ADMIN")
////        .anyRequest().authenticated()
////        .and()
////        .formLogin().loginProcessingUrl("/login").successForwardUrl("/productui/showProducts").permitAll()
////        .and()
////        .logout().logoutSuccessUrl("/login").permitAll()
////        .and()
////        .exceptionHandling().accessDeniedPage("/403")
////        .and()
////        .cors().and().csrf().disable();
////        return http.build();
////    }
//    
//    //spring version 4.0
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http
//            .authorizeHttpRequests(auth -> auth
//                .requestMatchers(
//                    "/productui/showProducts",
//                    "/productui/addProductForm"
//                ).hasAnyAuthority("USER", "ADMIN")
//
//                .requestMatchers(
//                    "/productui/deleteProductUI/**",
//                    "/productui/updateProductForm/**"
//                ).hasAuthority("ADMIN")
//
//                .anyRequest().authenticated()
//            )
//
//            .formLogin(form -> form
//                .loginProcessingUrl("/login")
//                .successForwardUrl("/productui/showProducts")
//                .permitAll()
//            )
//
//            .logout(logout -> logout
//                .logoutSuccessUrl("/login")
//                .permitAll()
//            )
//
//            .exceptionHandling(exception -> exception
//                .accessDeniedPage("/productui/403")
//            )
//
//            .cors(cors -> {})
//
//            .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
//
//
//}
