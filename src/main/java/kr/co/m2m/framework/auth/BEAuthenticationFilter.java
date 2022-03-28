package kr.co.m2m.framework.auth;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BEAuthenticationFilter extends OncePerRequestFilter {

	private static final String BEARER_TOKEN_PREFIX = "Bearer ";
	private static final String[] ALLOW_PATHS = new String[] { "/login", "/error", "/favicon.ico" };

	private AuthenticationManager authMgr;
	private AuthenticationEntryPoint authEntry;

	public BEAuthenticationFilter(AuthenticationManager authMgr, AuthenticationEntryPoint authEntry) {
		super();
		this.authMgr = authMgr;
		this.authEntry = authEntry;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		try {
			String reqHost = request.getHeader("X-Forwareded-Host");
			String uri = request.getRequestURI();
			log.info("## [AuthenticationFilter] - check uri:: '{}'", uri);
			for (String allowPath : ALLOW_PATHS) {
				if (uri.startsWith(allowPath)) {
					log.debug("## [AuthenticationFilter] - ignore : {}", uri);
					chain.doFilter(request, response);
					return;
				}
			}
			if (StringUtils.isEmpty(reqHost)) {
				reqHost = request.getHeader("Host");
				if (!StringUtils.isEmpty(reqHost)) {
					reqHost = reqHost.split(Pattern.quote(":"))[0];
				}
			}
			if (StringUtils.isEmpty(reqHost)) {
				try {
					reqHost = new URI(request.getRequestURI()).getHost();
				} catch (URISyntaxException ignore) {

				}
			}
			// log.debug("## [AuthenticationFilter] - BEAuthenticationFilter - reqHost :
			// {}", reqHost);
			final String requestUri = request.getRequestURI();
			BEWorkingContext.setRequestUri(requestUri);
			String header = request.getHeader("Authorization");
			log.debug("## [AuthenticationFilter] - header : {}", header);
			if (header == null || !header.startsWith(BEARER_TOKEN_PREFIX)) {
				chain.doFilter(request, response);
				return;
			}

			String token = header.substring(BEARER_TOKEN_PREFIX.length());
			log.debug("## [AuthenticationFilter] - token : {}", token);
			try {
				if (authenticationIsRequired(token)) {
					PreAuthenticatedAuthenticationToken preAuth = new PreAuthenticatedAuthenticationToken(null, token);
					BEAuthentication authResult = (BEAuthentication) this.authMgr.authenticate(preAuth);
					BEAuthDetailModel authDetailModel = (BEAuthDetailModel) authResult.getDetails();
					log.debug("## [AuthenticationFilter] - authenticate success... BEAuthentication : {}", authResult);
					SecurityContextHolder.getContext().setAuthentication(authResult);
					BEWorkingContext.setAuthToken(token);
					BEWorkingContext.setAuthDetail(authDetailModel);
				}
			} catch (AuthenticationException failed) {
				SecurityContextHolder.clearContext();
				if (log.isDebugEnabled()) {
					log.debug("## [AuthenticationFilter] - Authentication request for failed.", failed);
				} else {
					log.info("## [AuthenticationFilter] - Authentication request for failed.");
				}

				if (this.authEntry != null) {
					this.authEntry.commence(request, response, failed);
				}
			}
			chain.doFilter(request, response);
		} finally {
			BEWorkingContext.clear();
		}
	}

	private boolean authenticationIsRequired(String token) {
		Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		if (existingAuth instanceof BEAuthentication && !token.equals(existingAuth.getCredentials())) {
			return true;
		}

		if (existingAuth instanceof AnonymousAuthenticationToken) {
			return true;
		}

		return false;
	}
}
