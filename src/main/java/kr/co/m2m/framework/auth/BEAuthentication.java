package kr.co.m2m.framework.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.framework.auth
 * 파일명		: BEAuthentication.java
 * 작성일		: 2022-04-08
 * 작성자		: wtkim
 * 설명		 	: Spring Security 인증
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	wtkim - 최초생성
 * </pre>
 */
@SuppressWarnings("serial")
public class BEAuthentication extends AbstractAuthenticationToken {

	private Object authToken;
	private int userId;
	
	public BEAuthentication(Object authToken) {
		super(null);
		this.authToken	= authToken;
		setAuthenticated(false);
	}

	public BEAuthentication(BEAuthDetailModel authDetail,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.userId		= authDetail.getMemberNo();
		this.authToken	= authDetail.getAuthToken();
		this.setDetails(authDetail);
		super.setAuthenticated(true);
	}
	
	@Override
	public Object getCredentials() {
		return this.authToken;
	}

	@Override
	public Object getPrincipal() {
		return this.userId;
	}
	
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}
		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.authToken	= null;
	}	
}