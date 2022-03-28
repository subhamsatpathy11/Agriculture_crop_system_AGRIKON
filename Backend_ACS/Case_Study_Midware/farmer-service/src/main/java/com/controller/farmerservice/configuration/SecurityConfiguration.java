package com.controller.farmerservice.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.controller.farmerservice.filter.JwtRequestFilter;
import com.controller.farmerservice.service.UserService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserService userService;

	@Autowired
	private JwtRequestFilter jwtFilterRequest;
	
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
		http.csrf().disable().authorizeRequests().antMatchers("/registerFarmer",
				"/authenticateFarmer")
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static final String[] AUTH = {"/listfarmers","/{fid}","/{femail}/fetchcrops","/{fid}/search/{cropid}",
			"/{fid}/addcrops","/update/{fid}","/{fid}/updateCrops/{cropid}","/delete/{fid}","/{fid}/delete/{cropid}",
			"/farmer/{femail}"};
}
	