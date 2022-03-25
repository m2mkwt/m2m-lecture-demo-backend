package kr.co.m2m.framework.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

	private static RequestAttributes getRequestAttribute() {
		return RequestContextHolder.getRequestAttributes();
	}

	public static HttpServletRequest getHttpServletRequest() {
		RequestAttributes requestAttributes = getRequestAttribute();

		if (requestAttributes != null) {
			return ((ServletRequestAttributes) requestAttributes).getRequest();
		} else {
			return null;
		}
	}

	public static HttpServletResponse getHttpServletResponse() {
		RequestAttributes requestAttributes = getRequestAttribute();

		if (requestAttributes != null) {
			return ((ServletRequestAttributes) requestAttributes).getResponse();
		} else {
			return null;
		}
	}

	public static boolean isAjax(HttpServletRequest request) {

		if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
			return true;
		}

		return false;
	}

	public static String getClientIp(HttpServletRequest request) {
		String clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("ns-client-ip");
		}

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("Proxy-Client-IP");
		}

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getRemoteAddr();
		}

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("X-Forwarded-For");
		}

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("WL-Proxy-Client-IP");
		}

		if (StringUtils.isBlank(clientIp) || "unknown".equalsIgnoreCase(clientIp)) {
			clientIp = request.getHeader("HTTP_CLIENT_IP");
		}
		return clientIp;
	}

	public static boolean isAdminPage(HttpServletRequest request) {
		if (request.getRequestURI().startsWith("/admin/")) {
			return true;
		} else {
			return false;
		}
	}

	public static String getJsonByRestTemplate(String url) {

		RestTemplate template = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = MediaType.APPLICATION_FORM_URLENCODED;
		headers.setContentType(mediaType);

		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
		ResponseEntity<String> responseEntity = template.exchange(url, HttpMethod.GET, entity, String.class);
		String result = responseEntity.getBody();

		return result;
	}

	public static String getServerIp() {
		InetAddress ip;
		String ipAddr = "";
		try {
			ip = InetAddress.getLocalHost();
			// log.debug("현재 서버 IP address : " + ip.getHostAddress());
			ipAddr = ip.getHostAddress();
		} catch (UnknownHostException e) {
			log.debug("서버IP를 알수 없는 예외가 발생하였습니다.");
		}
		return ipAddr;
	}
}
