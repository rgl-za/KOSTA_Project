//package com.project.configuration;
//
//import java.io.IOException;
//
//import javax.security.sasl.AuthenticationException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//public class AuthFailureHandler implements AuthenticationFailureHandler{
//	
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{
//		String username = request.getParameter("username");
//		
//		response.sendRedirect("/failLogin");
//	}
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//			org.springframework.security.core.AuthenticationException exception) throws IOException, ServletException {
//		// TODO Auto-generated method stub
//		
//	}
//
//}
