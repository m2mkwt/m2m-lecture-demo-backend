package kr.co.m2m.instagram.common.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.m2m.framework.auth.BEAuthDetailModel;
import kr.co.m2m.framework.auth.BEAuthentication;
import kr.co.m2m.framework.auth.RedisBEAuthManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BESuccessHandler implements AuthenticationSuccessHandler {

	private static final String BEARER_TOKEN_PREFIX = "Bearer ";

	private RedisBEAuthManager redisBEAuthManager;

	public BESuccessHandler(RedisBEAuthManager redisBEAuthManager) {
		this.redisBEAuthManager = redisBEAuthManager;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		log.info("BESuccessHandler authentication : {}", authentication);
		BEAuthDetailModel details = (BEAuthDetailModel) ((BEAuthentication) authentication).getDetails();
		String authToken = (String) details.getAuthToken();
		log.info("SET Response Header - key :{}, value : {}", BEARER_TOKEN_PREFIX, authToken);
		redisBEAuthManager.registAuth(details);
		response.setHeader(BEARER_TOKEN_PREFIX, authToken);
		// CookieUtil.createCookie(BEARER_TOKEN_PREFIX.trim(), authToken);
		// response.("/main");
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = new HashMap<>();
		m.put("isSuccess", true);
		m.put("message", "LOGIN SUCCESS.");
		m.put("messageCode", "LOGIN_SUCCESS.");
		m.put("details", details);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(mapper.writeValueAsString(m));
		response.getWriter().flush();
	}

}
