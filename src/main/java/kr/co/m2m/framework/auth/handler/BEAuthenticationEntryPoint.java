package kr.co.m2m.framework.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BEAuthenticationEntryPoint implements AuthenticationEntryPoint {

	private static final String[] ALLOW_PATHS = new String[] { "/login", "/error", "/favicon.ico" };

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ex) throws IOException, ServletException {
		String uri = request.getRequestURI();
		// log.debug("check uri:: '{}'", uri);
		for (String allowPath : ALLOW_PATHS) {
			if (uri.startsWith(allowPath)) {
				// log.debug("ignore : {}", uri);
				return;
			}
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
	}
}
