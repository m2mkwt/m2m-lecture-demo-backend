package kr.co.m2m.framework.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CookieUtil {

	public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 삭제");
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		Cookie[] cookies = request.getCookies();
		if (cookies == null)
			return;

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieName.toUpperCase())) {
				removeCookie(response, cookie);
			}
		}
	}

	private static void removeCookie(HttpServletResponse response, Cookie cookie) {
		cookie.setValue(null);
		cookie.setPath("/");
		cookie.setMaxAge(0);
		cookie.setDomain(getDomain());
		response.addCookie(cookie);
	}

	public static Cookie getCookieByName(HttpServletRequest request, String cookieName) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 조회(키) : {}", cookieName);

		Cookie[] cookies = request.getCookies();
		Cookie resultCookie = null;

		if (cookies == null)
			return resultCookie;

		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				resultCookie = cookie;
				break;
			}
		}

		if (resultCookie != null) {
			log.debug("쿠키 조회(값) : {}", resultCookie.getValue());
		}
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return resultCookie;
	}

	public static String getCookie(HttpServletRequest request, String cookieName) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 조회(키) : {}", cookieName);

		Cookie[] cookies = request.getCookies();
		String resultCookie = null;

		if (cookies == null)
			return resultCookie;

		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				resultCookie = cookie.getValue();
				break;
			}
		}

		if (resultCookie != null) {
			// log.debug("쿠키 조회(값) : {}", resultCookie.getValue());
			log.debug("쿠키 조회(값) : {}", resultCookie);
		}
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return resultCookie;
	}

	public static String getCookiesAsString(String cookieName) throws Exception {
		HttpServletRequest request = HttpUtil.getHttpServletRequest();
		Cookie[] cookies = request.getCookies();

		String name;
		String value;

		if (cookies != null && cookies.length > 0) {

			for (Cookie cookie : cookies) {
				name = cookie.getName();
				value = cookie.getValue();

				log.debug("CookieUtil getCookiesAsString : {}= {}", cookie.getName(), cookie.getValue());

				if (cookieName.equals(name)) {
					return value;
				}
			}
		}

		return null;
	}

	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue) {
		addCookie(response, cookieName, cookieValue, -1);
	}

	public static void addCookie(HttpServletResponse response, String cookieName, String cookieValue, int maxAge) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 추가 {}:{}:{}", cookieName, cookieValue, maxAge);
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(maxAge);
		cookie.setPath("/");
		cookie.setDomain(getDomain());
		response.addCookie(cookie);
	}

	public static void addCookie(String cookieName, String cookieValue) {
		HttpServletResponse response = HttpUtil.getHttpServletResponse();

		if (response != null) {
			addCookie(response, cookieName, cookieValue);
		} else {
			log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
			log.debug("response is null");
			log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		}
	}

	public static boolean existCookie(HttpServletRequest request, String cookieName) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 {}의 유무", cookieName);

		Cookie[] cookies = request.getCookies();
		boolean result = false;

		if (cookies == null)
			return false;

		for (Cookie cookie : cookies) {
			if (cookieName.equals(cookie.getName())) {
				result = true;
			}
		}

		log.debug("{}", result);
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		return result;
	}

	public static Cookie createCookie(String cookieName, String cookieValue) {
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		log.debug("쿠키 생성 {}:{}", cookieName, cookieValue);
		log.debug("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

		Cookie cookie = new Cookie(cookieName, cookieValue);
		cookie.setMaxAge(-1);
		cookie.setPath("/");
		cookie.setDomain(getDomain());
		return cookie;
	}

	private static String getDomain() {
		HttpServletRequest request = HttpUtil.getHttpServletRequest();
		String domain = request.getServerName();

		if (domain.startsWith("www.")) {
			domain = domain.substring(4);
		}

		return domain;
	}

}
