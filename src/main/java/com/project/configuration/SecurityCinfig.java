//package com.project.configuration;
//
//import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
//import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//
//import lombok.RequiredArgsConstructor;
//
//@Configuration(proxyBeanMethods = false)
//@RequiredArgsConstructor
//@ConditionalOnDefaultWebSecurity
//@ConditionalOnWebApplication(type=ConditionalOnWebApplication.Type.SERVLET)
//
//public class SecurityCinfig {
//	
//	@Bean
//	@Order(SecurityProperties.BASIC_AUTH_ORDER)
//	protected void configure(HttpSecurity http) throws Exception{
//		http
//		
//		.csrf().disable()
//        .authorizeRequests()
//            .antMatchers("/", "/register/**", "/Test/**").permitAll()
//            .anyRequest().authenticated()
//            .and()
//        .formLogin()
//            .loginPage("/register")
//            .loginProcessingUrl("/register")
//            .defaultSuccessUrl("/")
//            .and()
//        .logout()
//        	.logoutSuccessUrl("/")
//        	.invalidateHttpSession(true);
//		
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
//    }
//	
//
//	
//}
