package com.uisarel.institucion.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.uisarel.institucion.servicio.impl.CustomUserDetailService;


@Configuration
@EnableWebSecurity
public class DataWebSecurity {
	
	@Autowired
	private CustomUserDetailService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	    @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	        http	        
			        .csrf()
		            .disable()
	                .httpBasic()
	                .and()
	                .authorizeHttpRequests()	                
	                .requestMatchers("/themes/**").permitAll()	                
	                .anyRequest().authenticated()
	                .and()
	                .formLogin()
	                .loginPage("/login")
	                .defaultSuccessUrl("/dashboard", true)
	                .permitAll()
	                .failureUrl("/login?error=true")
					.and().logout()
					.permitAll()
					.logoutSuccessUrl("/login?logout");

	        return http.build();
	    }

	    @Bean
	    public AuthenticationManager authenticationManager() {

	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	        authProvider.setUserDetailsService(usuarioService);
	        authProvider.setPasswordEncoder(encoder());

	        List<AuthenticationProvider> providers =  List.of(authProvider);

	        return new ProviderManager(providers);
	    }

}
