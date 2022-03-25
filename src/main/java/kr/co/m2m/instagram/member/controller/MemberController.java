package kr.co.m2m.instagram.member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import kr.co.m2m.instagram.member.model.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequiredArgsConstructor
public class MemberController {

	@Autowired
	MemberServiceImpl memberService;
	
	//로그인 
	@RequestMapping("/login")
	public String login() {
		System.out.println("로그인");
		return"login";
	}
		
	

	//회원 가입 진입
	@GetMapping("/signUp")
	public String signUpForm() {
		return "signUp";
	}
	
	
	//회원 가입 폼
	@PostMapping("/signUp")
	public String signUp(@Valid MemberVO memberVO, Errors errors, Model model) {
		System.out.println(memberVO);
		memberService.insertMember(memberVO);
		
		return "redirect:/login"; 

//		if (errors.hasErrors()) {
//			// 회원가입 실패시, 입력 데이터를 유지
//			model.addAttribute("memberVO", memberVO);
//
//			// 유효성 통과 못한 필드와 메시지를 핸들링
//			Map<String, String> validatorResult = memberService.validateHandling(errors);
//			for (String key : validatorResult.keySet()) {
//				model.addAttribute(key, validatorResult.get(key));
//			}
//
//			return "/signUp";
//		}
//
//		memberService.insertMember(memberVO);
//		return "redirect:/login";
	}

}
