package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.cognixia.jump.filter.JwtRequestFilter;

@Configuration
public class SecurityConfiguration {
    
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Bean
    protected UserDetailsService userDetailsService(){
        return userDetailsService;
    }

    // Authorization - what do you want?
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        //fill in the filter chain here, right now we don't have any endpoints so we don't have anything here

        return http.build();
    }

    @Bean
    protected PasswordEncoder encoder() {

        // plain text coder -> won't do any encoding
        return NoOpPasswordEncoder.getInstance();

        // bcrypt encoder -> do actual encoding, popular algorithm but there are other encoders you can use
        // return new BCryptPasswordEncoder();
    }


    @Bean
    protected DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder( encoder() );
		
		return authProvider;
	}

    @Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
		return authConfig.getAuthenticationManager();
	}
}
