//package com.project.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import com.project.service.UserService;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration
//@RequiredArgsConstructor
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type=ConditionalOnWebApplication.Type.SERVLET)
//
//public class SecurityConfig {
//	
//	@Autowired
//	UserService userService;
//	
//	@Bean
//	 public void configure(WebSecurity web) throws Exception {
//	        web.ignoring().antMatchers("/resources/**"); //static 디렉터리 하위 파일 목윽은 인증 무시
//	    }
//	
//	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//		
//		.csrf().disable()
//        .authorizeRequests()
//            .antMatchers("/", "/register/**", "/login/**").permitAll() //누구나 접근 가능
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//            .loginPage("/login") //로그인 페이지 링크
//            //.loginProcessingUrl("/register")
//            .defaultSuccessUrl("/") //로그인 성공시 이동하는 페이지 등록
//            .and()
//        .logout()
//        	.logoutRequestMatcher(new AntPathRequestMatcher("/")) //로그아웃 경로를 지정
//        	.logoutSuccessUrl("/")  //로그아웃 성공시 경로를 지정
//        	.invalidateHttpSession(true); //로그아웃 성공시 세션을 제거
//		
//	}
//	
//	
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//   
//	
//
//	
//}