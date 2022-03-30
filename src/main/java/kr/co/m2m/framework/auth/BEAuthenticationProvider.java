package kr.co.m2m.framework.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kr.co.m2m.framework.exception.WithdrawalAccountException;
import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BEAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private BEAuthTokenResolver authTokenResolver;

	@Autowired
	AuthTempKeyRedisManager authTempKeyRedisManager;

	@Autowired
	private MemberService memberService;

	@Autowired
	@Lazy
	private PasswordEncoder passwordEncoder;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		BEAuthentication authResult;
		String authToken;
		BEAuthDetailModel authDetail;
		List<GrantedAuthority> grantedList;
		LoginSO so = new LoginSO();
		LoginVO vo = null;
		log.info("### [AuthenticationProvider] - authentication : {}", authentication);
		authToken = (String) authentication.getCredentials();
		log.info("### [AuthenticationProvider] - AUTH from provider (token : {})", authToken);

		authDetail = this.authTokenResolver.getAuthDetail(authToken, true);
		if (authDetail == null && authentication.getPrincipal() != null) {
			String username = (String) authentication.getPrincipal();
			log.info("### [AuthenticationProvider] - username : {}", username);

			// 회원 조회
			so.setId(username);
			vo = memberService.getLoginMember(so);
			
			if (vo == null)
				throw new UsernameNotFoundException("UsernameNotFoundException");

            log.info(" ##### [AuthenticationProvider] Encrypt authToken : {}", passwordEncoder.encode(authToken));
           
			boolean pwdBoolean = passwordEncoder.matches(authToken, vo.getPassword());
			// 패스워드 확인
			if (!pwdBoolean)
				throw new BadCredentialsException("BadCredentialsException");

			String accessToken = authTempKeyRedisManager.createTempkey(username);
			log.info("### [AuthenticationProvider] - Generated Token : {}", accessToken);

			authDetail = new BEAuthDetailModel();
			authDetail.setAuthToken(accessToken);
			authDetail.setMemberNo(vo.getMemberNo());
			authDetail.setEmail(vo.getEmail());
			authDetail.setLoginId(vo.getLoginId());
			authDetail.setPassword(vo.getPassword());
			authDetail.setGender(vo.getGender());
			authDetail.setMediaNo(vo.getMediaNo());
			
			grantedList = new ArrayList<>();
			// log.info("authDetail : {}", authDetail);
			// log.info("grantedList : {}", grantedList);

			authResult = new BEAuthentication(authDetail, grantedList);

			SecurityContextHolder.getContext().setAuthentication(authResult);
			log.info("### [AuthenticationProvider] - authResult : {}", authResult);
			BEWorkingContext.setAuthToken(accessToken);
			BEWorkingContext.setAuthDetail(authDetail);
			return authResult;
		}

		if (authDetail == null) {
			return null;
		}
		grantedList = new ArrayList<>();
		// 일단은 권한리스트는 설정없음
		authResult = new BEAuthentication(authDetail, grantedList);
		return authResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return Authentication.class.isAssignableFrom(authentication);
	}
}
