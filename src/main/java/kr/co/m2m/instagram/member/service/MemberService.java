package kr.co.m2m.instagram.member.service;

import java.util.List;

import kr.co.m2m.instagram.member.model.MemberVO;

public interface MemberService {

	public List<MemberVO> getAllMembers();
	
	public void insertMember(MemberVO memberVO);

}
