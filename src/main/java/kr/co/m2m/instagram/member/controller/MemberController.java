package kr.co.m2m.instagram.member.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import kr.co.m2m.framework.util.SecurityUtil;
import kr.co.m2m.instagram.member.model.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

	@Autowired
	MemberServiceImpl memberService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	// 아이디 중복검사
	@ResponseBody
	@RequestMapping(value = "checkValidId", produces ="application/json")
	public int checkValidId(@RequestBody MemberVO memberVO) {
		int result = memberService.idCheck(memberVO);
		return result;
	}

	// 패스워드 검사
	@ResponseBody
	@RequestMapping(value = "checkValidPw", produces ="application/json")
	public int checkValidPw(@RequestBody MemberVO memberVO) {
		int result = SecurityUtil.checkPassword(memberVO.getPassword());
		return result;
	}
	
    //회원 가입 폼
	@PostMapping("registMember")
	public String registMember(@Valid@RequestBody MemberVO memberVO) {
		//패스워드 암호화
		String enc = passwordEncoder.encode(memberVO.getPassword());
		memberVO.setPassword(enc);
		//회원정보 등록
		memberService.insertMember(memberVO);
		
		return "success";

	}

}
