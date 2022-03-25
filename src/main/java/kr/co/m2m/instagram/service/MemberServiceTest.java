package kr.co.m2m.instagram.service;

import java.util.List;

import kr.co.m2m.instagram.model.MemberVO;

public class MemberServiceTest {
	
	private MemberServiceImpl memberService;

	public void getAllMembers() {
		List<MemberVO> members = memberService.getAllMembers();
		System.out.println(members);
	}
}
