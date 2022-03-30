package kr.co.m2m.instagram.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import kr.co.m2m.instagram.post.service.impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private PostServiceImpl postService;
	
//	@RequestMapping("selectAll") // post내용물 나오는 페이지
//	public ResponseEntity<? extends BasicResponse> selectAll(PostVO vo,Model model) {
//		ResultListModel<PostVO> result = postService.selectAll(vo);
//		return ResponseEntity.ok().body(new CommonResponse<ResultListModel<PostVO>>(result));
//	}
	
	@Autowired
	private MemberService memberService;
	
	@ResponseBody
	@RequestMapping("profile")
	public MemberVO updateMember(@RequestParam(value = "memberNo") int memberNo) {
		MemberVO mvo = memberService.selectMember(memberNo);
		System.out.println(mvo);
		return mvo;
	}
	
	@RequestMapping(value = "updateProfile", method = RequestMethod.POST)
	public String updateProfile(@RequestBody MemberVO mvo, RedirectAttributes rattr) {
		System.out.println(mvo);
		String msg = memberService.updateMember(mvo);
		rattr.addAttribute("msg", msg);
		rattr.addAttribute("memberNo", mvo.getMemberNo());
		return "redirect:profile";
	}
}
