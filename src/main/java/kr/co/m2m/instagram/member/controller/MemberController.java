package kr.co.m2m.instagram.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import kr.co.m2m.framework.util.SecurityUtil;
import kr.co.m2m.instagram.member.model.MemberVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

	@Autowired
	MemberServiceImpl memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;
	

	// 로그인
	@RequestMapping("login")
	public String login() {
		System.out.println("로그인");
		return "login";
	}

	// 회원 가입 진입
	@GetMapping("signup")
	public String signUpForm() {
		return "signup";
	}

	// 아이디 중복검사
	@ResponseBody
	@RequestMapping(value = "idCheck", produces ="application/json")
	public int idCheck(@RequestBody MemberVO memberVO) {
		int result = memberService.idCheck(memberVO);
		return result;
	}

	// 패스워드 검사
	@ResponseBody
	@RequestMapping(value = "pwCheck", produces ="application/json")
	public int pwCheck(@RequestBody MemberVO memberVO) {
		int result = SecurityUtil.checkPassword(memberVO.getPassword());
		return result;
	}
	
    //회원 가입 폼
	@PostMapping("signup")
	public String signup(@RequestBody MemberVO memberVO) {
		//패스워드 암호화
		String enc = passwordEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(enc);
		//회원정보 등록
		memberService.insertMember(memberVO);
		
		return "redirect:/member/login";

	}

}
