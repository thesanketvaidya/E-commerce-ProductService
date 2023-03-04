package com.app.productService.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.productService.dto.RequestProductDto;
import com.app.productService.dto.RequestValidateToken;
import com.app.productService.service_communication.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;
	
	@Autowired
	ObjectMapper jsonMapper;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		try {
			
			List<String> list=new ArrayList<>();
			list.add("PUT");
			list.add("POST");
			list.add("DELETE");
			
			if(list.contains(request.getMethod()))
			{
				System.err.println("Auth Header: "+ request.getHeader("Authorization"));
				System.err.println("user header: "+request.getHeader("username") );
				
				String authToken= request.getHeader("Authorization").split("Bearer ")[1];
				String username=request.getHeader("username");
				if((boolean) userService.validateToken(new RequestValidateToken(username,
						authToken))
				.getBody())
				{
					
					filterChain.doFilter(request, response);
				}
				else
				{
					response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
				}
				
			}
			else
			{
				filterChain.doFilter(request, response);
			}
			
			
					
		}
		 catch (Exception e) {
			e.printStackTrace();
			throw new ServletException("Error in Authentication Filter!",e);
		}
//			filterChain.doFilter(request, response);
	}
}
