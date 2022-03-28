package kr.co.m2m.instagram.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.member.model.MemberVO;

@Mapper
public interface MemberMapper {
	
	//회원 검색
	List<MemberVO> selectAllMembers();
	
	//회원 가입
	void insertMember(MemberVO memberVO);
	
	//회원정보 수정
	void updateMember(MemberVO memberVO);
	
}
