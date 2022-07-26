package com.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.project.service.UserService;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type=ConditionalOnWebApplication.Type.SERVLET)

public class SecurityConfig {
	
	@Autowired
	UserService userService;
	
	
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() throws Exception {
	        return (web) -> web.ignoring().antMatchers
					("/assets/**", "/css/**","/fonts/**", "/img/**", "/js/**", "/plugin/**", 
					"/vendor/**","/scripts/**", "/productImgs/**", "/store/**", "/uploadFile/**", "/error"); //static 디렉터리 하위 파일 목윽은 인증 무시
	}
	
	@Bean
	@Order(SecurityProperties.BASIC_AUTH_ORDER)
	protected  SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http
		.csrf().disable()
        .authorizeRequests()
            .antMatchers("/", "/idCheck", "/register", "/login", "/main.do", "/mypage", "/detail", "/index", "/pick", "/update", "/updatePost", "/UpdateUser", "/write").permitAll() //누구나 접근 가능
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login").permitAll() //로그인 페이지 링크
            .loginProcessingUrl("/login")
            .usernameParameter("userid")
            .defaultSuccessUrl("/main.do") //로그인 성공시 이동하는 페이지 등록
            .failureUrl("/login")
            .and()
        .logout()
        	.logoutRequestMatcher(new AntPathRequestMatcher("/logout")) //로그아웃 경로를 지정
        	.logoutSuccessUrl("/main.do")  //로그아웃 성공시 경로를 지정
        	.invalidateHttpSession(true).deleteCookies("JSESSIONID"); //로그아웃 성공시 세션을 제거
		
		http.rememberMe()
		.key("remember-me")
		.rememberMeParameter("remember-me")
		.tokenValiditySeconds(86400*30)
		.userDetailsService(userService);
			
		
		return http.build();	
		}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Bean
//	public AuthenticationManager authenticationManagerBean(AuthenticationManagerBuilder builder) throws Exception{
//		return builder.userDetailsService(userService).passwordEncoder(passwordEncoder()).and().build();
//	}
//	
//	@Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
	

	
	
   
	

	
}
