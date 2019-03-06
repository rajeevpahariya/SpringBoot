package com.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		auth.userDetailsService(userDetailService).
			passwordEncoder(encoder);
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests().
				antMatchers("/pizzaDesign", "/pizzaOrder").
				hasRole("Admin").
				antMatchers("/", "/**").permitAll().
				anyRequest().authenticated()
			.and().
				formLogin().
				loginPage("/login").
				usernameParameter("username").
				passwordParameter("password")
				//defaultSuccessUrl("/pizzaDesign", true)
			.and()
		        .logout()
		          .logoutSuccessUrl("/")
		    .and()
		          .csrf()
		            .ignoringAntMatchers("/h2-console/**")
		    .and().headers().frameOptions().disable();;
				
	}
	
	// Below code was for In Memory data
	
//	@Autowired
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// Without adding this getting below exp
//		//There is no PasswordEncoder mapped for the id "null"
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		auth.
//			inMemoryAuthentication().
//			withUser("rajeev").
//			password(encoder.encode("pahariya")).
//			authorities("ADMIN").
//		and().
//		withUser("gupta").
//		password("pahariya").
//		authorities("USER");
//	}

}
