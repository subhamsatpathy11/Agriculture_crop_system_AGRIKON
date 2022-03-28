package com.controller.farmerservice.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.controller.farmerservice.service.UserService;
import com.controller.farmerservice.utils.JwtUtils;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authHeader = request.getHeader("Authorization");
		String user = null;
		String jwtToken = null;
		
		if(authHeader!=null && authHeader.startsWith("Bearer ")) //if error comes, then its for this line OK !!!
		{
			jwtToken = authHeader.substring(7);
			user = jwtUtils.extractUsername(jwtToken);
		}
		
		if(user != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			UserDetails currentUserDetails = userService.loadUserByUsername(user);
			Boolean tokenValidated = jwtUtils.validateToken(jwtToken, currentUserDetails);
			
			if(tokenValidated)
			{
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(currentUserDetails,null
						,currentUserDetails.getAuthorities());
				upat.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(upat);
			}
		}
		
		filterChain.doFilter(request, response);
	}

}
