package kr.co.m2m.instagram.profile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 프로필(회원 정보) 조회
	@RequestMapping("getProfile")
	public MemberVO selectMember(@RequestParam(value = "memberNo") int memberNo) {
		MemberVO mvo = memberService.selectMember(memberNo);
		log.info(mvo.toString());
		return mvo;
	}
	
	// 프로필(회원 정보) 수정
	@RequestMapping(value = "editProfile", method = RequestMethod.POST)
	public ModelAndView updateProfile(@RequestBody MemberVO mvo) {
		log.info(mvo.toString());
		String msg = memberService.updateMember(mvo);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:profile");
		return modelAndView;
	}
	
	// 회원 비밀번호 수정
	@RequestMapping(value = "editPassword", method = RequestMethod.POST)
	public String updatePassword(@RequestBody Map<String, String> map, @RequestParam int memberNo) {
		String dbPassword = memberService.selectPassword(memberNo);
		boolean test = passwordEncoder.matches(map.get("oldPassword"), dbPassword);
		if (test) {
			String newPassword = passwordEncoder.encode(map.get("newPassword"));
			MemberVO mvo = new MemberVO();
			mvo.setMemberNo(memberNo);
			mvo.setPassword(newPassword);
			return memberService.updatePassword(mvo);
		} else {
			return "incorect old password";
		}
	}
}
