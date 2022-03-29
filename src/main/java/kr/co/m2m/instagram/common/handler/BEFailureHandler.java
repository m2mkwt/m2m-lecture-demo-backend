package kr.co.m2m.instagram.common.handler;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import kr.co.m2m.framework.exception.WithdrawalAccountException;
import kr.co.m2m.framework.util.MessageUtils;
import kr.co.m2m.instagram.common.exception.BEMessageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BEFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> m = new HashMap<>();

		HttpStatus httpStatus = HttpStatus.FORBIDDEN; // 403
		m.put("isSuccess", false);
		m.put("timestamp", new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		m.put("code", httpStatus.value());
		m.put("status", httpStatus.name());
		
		if (exception instanceof UsernameNotFoundException) {
			m.put("message", MessageUtils.getMessage("error.message.username"));
		} else if (exception instanceof BadCredentialsException) {
			m.put("message", MessageUtils.getMessage("error.message.credentials"));
		} else if (exception instanceof WithdrawalAccountException) {
			m.put("message", MessageUtils.getMessage("error.message.withdrawal"));
		} else {
			throw new BEMessageException("server.common.exmaple.error-during-save-update");
		}
		log.info("onAuthenticationFailure - m : {}", m);

		response.setCharacterEncoding("UTF-16");
		response.setContentType("application/json");
		response.setStatus(httpStatus.value());        
		response.getWriter().write(mapper.writeValueAsString(m));
		response.getWriter().flush();
	}
}
