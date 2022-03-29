package kr.co.m2m.instagram.member.service;

import java.util.List;

import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.model.MemberVO;

public interface MemberService {
	
	//회원 로그인
	public LoginVO getLoginMember(LoginSO so);
	//회원 가입
	public void insertMember(MemberVO memberVO);
	//회원 수정
	public void updateMember(MemberVO memberVO);
	//아이디 체크
	public int idCheck(MemberVO memberVO);

}
