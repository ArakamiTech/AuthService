package com.arakamitech.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityContig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception {
		security.csrf(csrf -> {
			try {
				csrf.disable().authorizeHttpRequests(
						(authorizeHttpRequests -> authorizeHttpRequests.anyRequest().permitAll()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return security.build();
	}

	@Bean
	PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
}
