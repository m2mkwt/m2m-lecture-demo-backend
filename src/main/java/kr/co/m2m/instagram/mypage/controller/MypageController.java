package kr.co.m2m.instagram.mypage.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/mypage")
public class MypageController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	MemberService memberService;
	
	// 회원정보 조회
	@GetMapping("getMember")
	public ResponseEntity<? extends BasicResponse> getMember(int memberNo) {
		MemberVO result = memberService.selectMember(memberNo);
		log.info("count My Post : total {}", result);
		return ResponseEntity.ok().body(new CommonResponse<MemberVO>(result));
	}
	
	// 내 게시글 목록(게시글번호,파일명) 리스트 조회
	@GetMapping("searchPostList")
	public ResponseEntity<? extends BasicResponse> countPost(int memberNo) {
		MemberVO result = memberService.selectMember(memberNo);
		log.info("count My Post : total {}", result);
		return ResponseEntity.ok().body(new CommonResponse<MemberVO>(result));
	}
	
	// 내 게시글 총 개수 조회
	@GetMapping("getPostCnt")
	public ResponseEntity<? extends BasicResponse> selectMyPost(int memberNo) {
		List<Map<String, String>> result = postService.selectMyPost(memberNo);
		log.info("select Count MembeNo : {}", memberNo);
		return ResponseEntity.ok().body(new CommonResponse<List<Map<String, String>>>(result));
	}
}
