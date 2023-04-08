package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/authenticate").permitAll()
            // Creating a user 
            .antMatchers(HttpMethod.POST,"/api/user").permitAll()
            // Getting the boba menu
            .antMatchers(HttpMethod.GET,"/api/menu/drinks").permitAll()
            // Getting the dish menu
            .antMatchers(HttpMethod.GET,"/api/menu/dishes").permitAll()
            // Getting a list of all the orders
            .antMatchers("/api/orders").permitAll()
            // Gettting an order by id 
            .antMatchers(HttpMethod.GET,"/api/order/").permitAll()
            // Create an order
            .antMatchers(HttpMethod.POST,"/api/order/").hasAnyRole("ADMIN", "USER")
            // Add an item to order endpoint
            .antMatchers(HttpMethod.POST,"/api/order/add").hasAnyRole("ADMIN", "USER")
            // Remove item from order endpoint
            .antMatchers(HttpMethod.DELETE, "/api/order/remove").hasAnyRole("ADMIN", "USER")
            // Gets list of all users 
            .antMatchers(HttpMethod.GET, "/api/users").hasRole("ADMIN")
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

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
