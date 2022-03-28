package kr.co.m2m.instagram.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import kr.co.m2m.instagram.member.model.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
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
	
	
//	회원 가입 폼
	@PostMapping("signup")
	public String signup(@RequestBody MemberVO memberVO, Errors errors, Model model) {
		System.out.println(memberVO);
		memberService.insertMember(memberVO);
		model.addAttribute(memberVO);
		
		return "redirect:/login"; 

	}

}
