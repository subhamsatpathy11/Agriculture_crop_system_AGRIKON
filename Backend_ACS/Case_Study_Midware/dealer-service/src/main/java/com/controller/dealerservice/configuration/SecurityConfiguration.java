package com.controller.dealerservice.configuration;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.controller.dealerservice.repository.DealerRepository;
import com.controller.dealerservice.services.JwtFilterRequest;
import com.controller.dealerservice.services.UserService;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtFilterRequest jwtFilterRequest;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
	}

	private static final String[] AUTH_WHITELIST = {
			"/v2/api-docs",
			"/swagger-resources",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/swagger-ui.html#",
			"/webjars/**",
			"/v3/api-docs/**",
			"/swagger-ui/**"
			};
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/register",
				"/authenticate")
		.permitAll().antMatchers(AUTH_WHITELIST).permitAll().antMatchers(AUTH).permitAll()
		.anyRequest().authenticated().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	
		http.addFilterBefore(jwtFilterRequest, UsernamePasswordAuthenticationFilter.class);
	}
	//@Configuration
	
	/*@EnableWebMvc
	public class WebConfig implements WebMvcConfigurer {
		protected void addCorsMappings(CorsRegistry corsRegistry) {
	        corsRegistry.addMapping("/**")
	                .allowedOrigins("http://localhost:4200")
	                .allowedMethods("*")
	                .maxAge(3600L)
	                .allowedHeaders("*")
	                .exposedHeaders("Authorization")
	                .allowCredentials(true);
	    }*/
	
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	
	
	
	
	
	
	
	//urls here
	private static final String[] AUTH = 
		{
				"/dealerCrops/{randomid}","/{dealerid}","/listfarmers","/dealer/{dealeremail}",
				"/dealercrops/{randomid}","/dcrops/{randomid}"
		};
	
	
}
