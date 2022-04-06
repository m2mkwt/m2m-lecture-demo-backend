package kr.co.m2m.instagram.profile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.framework.auth.BEAuthDetailModel;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
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
	public ResponseEntity<? extends BasicResponse> selectMember(@RequestParam int memberNo) {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		BEAuthDetailModel user = null;
		if (userDetails instanceof BEAuthDetailModel) {
			user = (BEAuthDetailModel)userDetails;
		}
		Map<String, Object> result = null;
		MemberVO mvo = null;
		if (user.getMemberNo() == memberNo) {
			result = new HashMap<>();
			mvo = memberService.selectMember(memberNo);
			log.info("Get Member Info : {}", mvo);
			int mediaNo = mvo.getMediaNo();
			String filename = "";
			if (mediaNo != 0) filename = mediaService.selectMedia(mediaNo).getFilename();
			result.put("mvo", mvo);
			result.put("filename", filename);
			return ResponseEntity.ok().body(new CommonResponse<Map<String, Object>>(result));
		} else {
			return ResponseEntity.internalServerError().body(new ErrorResponse("인증실패"));
		}
	}
	
	// 프로필(회원 정보) 수정
	@RequestMapping(value = "editProfile", method = RequestMethod.POST)
	public ResponseEntity<? extends BasicResponse> updateProfile(@RequestBody MemberVO mvo) {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		BEAuthDetailModel user = null;
		if (userDetails instanceof BEAuthDetailModel) {
			user = (BEAuthDetailModel)userDetails;
		}
		if (user.getMemberNo() == mvo.getMemberNo()) {
			String result = memberService.updateMember(mvo);
			log.info("Update Member Profile : {}", result);
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		} else {
			return ResponseEntity.internalServerError().body(new ErrorResponse("인증실패"));
		}
	}
	
	// 회원 비밀번호 수정
	@RequestMapping(value = "editPassword", method = RequestMethod.POST)
	public ResponseEntity<? extends BasicResponse> updatePassword(@RequestBody Map<String, Object> map) {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		BEAuthDetailModel user = null;
		int memberNo = 0;
		if (userDetails instanceof BEAuthDetailModel) {
			user = (BEAuthDetailModel)userDetails;
		}
		memberNo = Integer.parseInt(map.get("memberNo").toString());
		if (user.getMemberNo() == memberNo) {
			String oldPassword = map.get("oldPassword").toString();		
			String dbPassword = memberService.selectPassword(memberNo);
			if (passwordEncoder.matches(oldPassword, dbPassword)) {
				String newPassword = passwordEncoder.encode(map.get("newPassword").toString());
				MemberVO mvo = new MemberVO();
				mvo.setMemberNo(memberNo);
				mvo.setPassword(newPassword);
				String result = memberService.updatePassword(mvo);
				return ResponseEntity.ok().body(new CommonResponse<String>(result));
			} else {
				return ResponseEntity.internalServerError().body(new ErrorResponse("비밀번호가 일치하지 않습니다."));
				}
		} else {
			return ResponseEntity.internalServerError().body(new ErrorResponse("인증 실패"));
		}
	}
}
