//package kr.co.m2m.instagram.config;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Configuration
//@EnableWebSecurity
//@Slf4j
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// 인가 정책
//		http.authorizeRequests() // 요청에 의한 보안을 실행한다.
//				.anyRequest().authenticated(); // 모든요청을.인증한다.
//		// 인증 정책
//		http.formLogin() // formLogin 방식과 httpBasic 방식이 있다. (formLogin 방식 사용)
//				.loginPage("/login") // 로그인 페이지 url (기본으로 제공되는 로그인 화면 페이지도 있다.)
//				.defaultSuccessUrl("/main") // 로그인 성공시 url (우선순위 마지막)
//				.failureUrl("/login") // 로그인 실패시 url (우선순위 마지막)
//				.usernameParameter("username") // id 파라미터 명
//				.passwordParameter("password") // password 파라미터 명
//				.loginProcessingUrl("/loginProcess") // form의 action 경로url
//				.successHandler(new AuthenticationSuccessHandler() {
//					@Override
//					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//							Authentication authentication) throws IOException, ServletException {
//						// 기본적으로 성공시 success 핸들러를 호출한다. 이처럼 생성하여 직접 구현 가능하다.
//						// 로그인 성공시 authentication 정보를 매개변수로 받는다.
//						log.debug("authentication : " + authentication.getName());
//						response.sendRedirect("/");
//					}
//				}).failureHandler(new AuthenticationFailureHandler() {
//					@Override
//					public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//							AuthenticationException exception) throws IOException, ServletException {
//						// 기본적으로 실패시 fail 핸들러를 호출한다. 이처럼 생성하여 직접 구현 가능하다.
//						// 로그인 실패시 exception 정보를 매개변수로 받는다.
//						log.debug("exception : " + exception.getMessage());
//						response.sendRedirect("/login");
//					}
//				}).permitAll(); // login 화면은 인증없이 누구나 접근이 가능해야 한다.
//	}
//
//}