package kr.co.m2m.instagram.member.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import kr.co.m2m.framework.util.SecurityUtil;
import kr.co.m2m.instagram.member.model.MemberVO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class MemberController {

	@Autowired
	MemberServiceImpl memberService;
	
	
	
	//로그인 
	@RequestMapping("login")
	public String login() {
		System.out.println("로그인");
		return"login";
	}
		
	

	//회원 가입 진입
	@GetMapping("signup")
	public String signUpForm() {
		return "signup";
	}
	
	
	//아이디 중복검사
	@ResponseBody
	@RequestMapping(value="/idCheck",method = RequestMethod.POST)
	public int idCheck(MemberVO memberVO) {
		int result = memberService.idCheck(memberVO);
		return result;
	}
	
	//패스워드 검사
	private void checkPass(String password) {

	}
	
	
	
	
//	회원 가입 폼
	@PostMapping("signup")
	public String signup(@RequestBody MemberVO memberVO) {
		
		int result = memberService.idCheck(memberVO);
		try {
			if(result==1) {
				System.out.println("같은아이디 존재");
				return"/signup";
			}else if(result==0){
				memberService.insertMember(memberVO);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		
		return "redirect:/login"; 

	}

}
