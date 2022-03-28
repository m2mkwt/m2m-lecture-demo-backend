package kr.co.m2m.framework.auth;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

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
		this.userId		= authDetail.getMemberId();
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