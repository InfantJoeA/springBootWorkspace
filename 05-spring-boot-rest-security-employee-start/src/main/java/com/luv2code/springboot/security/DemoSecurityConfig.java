package com.luv2code.springboot.security;

import javax.sql.DataSource;

import org.hibernate.mapping.UserDefinedType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import aj.org.objectweb.asm.commons.Method;

@Configuration
public class DemoSecurityConfig {
	
	//add support for JDBC .. no more hardcoded users ..
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		JdbcUserDetailsManager theUserDetailsManager=new JdbcUserDetailsManager(dataSource);
		
		theUserDetailsManager
		   .setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");
		
		theUserDetailsManager
		   .setAuthoritiesByUsernameQuery("select user_id, roles from role where user_id=?");
		
		return theUserDetailsManager;
	}
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsManager() {
//		
//		UserDetails john = User.builder().username("John").password("{noop}test123").roles("EMPLOYEE").build();
//		
//		UserDetails kevin = User.builder().username("Kevin").password("{noop}test123").roles("EMPLOYEE", "MANAGER").build();
//		
//		UserDetails mike = User.builder().username("Mike").password("{noop}test123").roles("EMPLOYEE", "MANAGER", "ADMIN").build();
//		
//		return new InMemoryUserDetailsManager(john, kevin, mike);
//	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests(configurer -> 
		configurer
		.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
		.requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
		.requestMatchers(HttpMethod.PATCH, "/api/employees/**").hasRole("MANAGER")
		.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN"));
		
		//use http basic authentication
		http.httpBasic(Customizer.withDefaults());
		
		//disable Cross site Request Forgery (CSRF)
		//in general not required for stateless REST APIs .
		
		http.csrf(csrf ->csrf.disable());
		
		return http.build();
	}
}
