package kr.co.m2m.instagram.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.model.MemberVO;

@Mapper
public interface MemberMapper {
	
	//회원 검색
    LoginVO getLoginMember(LoginSO so);
	
	//회원 가입
	void insertMember(MemberVO memberVO);
	
	//회원정보 수정
	int updateMember(MemberVO memberVO);
	
	//회원정보 조회
	MemberVO selectMember(int memberNo);
	
	//아이디 체크
	int idCheck(MemberVO memberVO);
	
	//비밀번호 수정
	int updatePassword(MemberVO memberVO);
	
	//비밀번호 조회
	String selectPassword(int memberNo);
	
}
