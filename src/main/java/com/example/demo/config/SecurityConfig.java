package com.example.demo.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
//	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable()
//		    .authorizeHttpRequests((authroize)->authroize.anyRequest().auth)
//		
//		return http.build();
//	}

}
