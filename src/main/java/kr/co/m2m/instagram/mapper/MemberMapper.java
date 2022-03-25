package kr.co.m2m.instagram.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.instagram.model.MemberVO;

@Mapper
public interface MemberMapper {
	
	//회원 검색
	List<MemberVO> selectAllMembers();
	
	//회원 가입
	void insertMember(MemberVO memberVO);
	
}
