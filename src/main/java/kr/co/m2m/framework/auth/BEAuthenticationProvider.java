package kr.co.m2m.framework.auth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BEAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private BEAuthTokenResolver authTokenResolver;

	@Autowired
	AuthTempKeyRedisManager authTempKeyRedisManager;

//	@Autowired
//	private LoginService loginService;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BEAuthentication authResult;
		String authToken;
		BEAuthDetailModel authDetail;
		List<GrantedAuthority> grantedList;
//		LoginSO so = new LoginSO();
//		LoginVO vo;
//
//		log.info("### [AuthenticationProvider] - authentication : {}", authentication);
//		authToken = (String) authentication.getCredentials();
//		log.info("### [AuthenticationProvider] - AUTH from provider (token : {})", authToken);
//
//		authDetail = this.authTokenResolver.getAuthDetail(authToken, true);
//		if (authDetail == null && authentication.getPrincipal() != null) {
//			String username = (String) authentication.getPrincipal();
//
//			// 회원 조회
//			so.setId(username);
////			vo = loginService.selectMember(so);
//			if (vo == null)
//				throw new UsernameNotFoundException("UsernameNotFoundException");
//
//			boolean pwdBoolean = passwordEncoder.matches(authToken, vo.getPw());
//
//			// 패스워드 확인
//			if (!pwdBoolean)
//				throw new BadCredentialsException("BadCredentialsException");
//
//			// 퇴직여부
//			if ("Y".equals(vo.getRetire()))
//				throw new WithdrawalAccountException("WithdrawalAccountException");
//
//			String accessToken = authTempKeyRedisManager.createTempkey(username);
//			log.info("### [AuthenticationProvider] - Generated Token : {}", accessToken);
//
//			authDetail = new BEAuthDetailModel();
//			authDetail.setId(username);
//			authDetail.setAuthToken(accessToken);
//			authDetail.setName(vo.getName());
//			authDetail.setDept(vo.getDept());
//			authDetail.setJikgub(vo.getJikgub());
//			authDetail.setsDate(vo.getSDate());
//			authDetail.setChiefYn(vo.getChiefYn());
//			authDetail.setRetire(vo.getRetire());
//			authDetail.setRetireDt(vo.getRetire());
//			authDetail.setEmail(vo.getEmail());
//			authDetail.setDeptNm(vo.getDeptNm());
//			authDetail.setJikgubNm(vo.getJikgubNm());
//
//			grantedList = new ArrayList<>();
//			// log.info("authDetail : {}", authDetail);
//			// log.info("grantedList : {}", grantedList);
//
//			authResult = new BEAuthentication(authDetail, grantedList);
//
//			SecurityContextHolder.getContext().setAuthentication(authResult);
//			log.info("### [AuthenticationProvider] - authResult : {}", authResult);
//			BEWorkingContext.setAuthToken(accessToken);
//			BEWorkingContext.setAuthDetail(authDetail);
//			return authResult;
//		}
//
//		if (authDetail == null) {
//			return null;
//		}
//		grantedList = new ArrayList<>();
//		// 일단은 권한리스트는 설정없음
//		authResult = new BEAuthentication(authDetail, grantedList);
//		return authResult;
		return new BEAuthentication("empty");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return Authentication.class.isAssignableFrom(authentication);
	}
}
