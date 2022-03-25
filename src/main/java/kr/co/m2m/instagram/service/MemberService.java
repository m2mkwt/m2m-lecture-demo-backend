package kr.co.m2m.instagram.service;

import java.util.List;

import kr.co.m2m.instagram.model.MemberVO;

public interface MemberService {

	public List<MemberVO> getAllMembers();
	
	public void insertMember(MemberVO memberVO);

}
