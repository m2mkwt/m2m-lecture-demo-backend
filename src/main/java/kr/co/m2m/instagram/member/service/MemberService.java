package kr.co.m2m.instagram.member.service;

import java.util.List;

import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.model.MemberVO;

public interface MemberService {
	
	public LoginVO getLoginMember(LoginSO so);

	public List<MemberVO> getAllMembers();
	
	public void insertMember(MemberVO memberVO);
	
	public void updateMember(MemberVO memberVO);

}
