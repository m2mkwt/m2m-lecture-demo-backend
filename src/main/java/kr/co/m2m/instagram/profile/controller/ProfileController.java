package kr.co.m2m.instagram.profile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import kr.co.m2m.instagram.media.model.MediaVO;
import kr.co.m2m.instagram.media.service.MediaService;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
	
	@Autowired
	private MediaService mediaService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// 프로필(회원 정보) 조회
	@RequestMapping("getProfile")
	public Map<String, Object> selectMember(@RequestParam int memberNo) {
		Map<String, Object> result = new HashMap<>();
		MemberVO mvo = memberService.selectMember(memberNo);
		log.info("Get Member Info : {}", mvo);
		int mediaNo = mvo.getMediaNo();
		String filename = "";
		if (mediaNo != 0) filename = mediaService.selectMedia(mediaNo).getFilename();
		result.put("mvo", mvo);
		result.put("filename", filename);
		return result;
	}
//	public MemberVO selectMember(@RequestParam int memberNo) {
//		MemberVO mvo = memberService.selectMember(memberNo);
//		int mediaNo = mvo.getMediaNo();
//		MediaVO mediaVO;
//		if (mediaNo != 0) mediaVO = mediaService.selectMedia(mediaNo);
//		
//		log.info(mvo.toString());
//		return mvo;
//	}
	
	// 프로필(회원 정보) 수정
	@RequestMapping(value = "editProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestBody MemberVO mvo) {
		log.info(mvo.toString());
		String msg = memberService.updateMember(mvo);
		log.info("Update Member Profile : {}", msg);
		return msg;
	}
	
	// 회원 비밀번호 수정
	@RequestMapping(value = "editPassword", method = RequestMethod.POST)
	public String updatePassword(@RequestBody Map<String, Object> map) {
		String oldPassword = map.get("oldPassword").toString();
		int memberNo = (Integer)map.get("memberNo");
		log.info("Map info : {}", map.toString());
		String dbPassword = memberService.selectPassword(memberNo);
		boolean test = passwordEncoder.matches(oldPassword, dbPassword);
		if (test) {
			String newPassword = passwordEncoder.encode((String)map.get("newPassword"));
			MemberVO mvo = new MemberVO();
			mvo.setMemberNo(memberNo);
			mvo.setPassword(newPassword);
			return memberService.updatePassword(mvo);
		} else {
			return "incorect old password";
		}
	}
}
