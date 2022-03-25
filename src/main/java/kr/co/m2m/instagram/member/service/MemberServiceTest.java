package kr.co.m2m.instagram.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;

public class MemberServiceTest {
	
	@Autowired
	private MemberServiceImpl memberService;

	public void getAllMembers() {
		List<MemberVO> members = memberService.getAllMembers();
		System.out.println(members);
	}
}
