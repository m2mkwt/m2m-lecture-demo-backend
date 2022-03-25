package kr.co.m2m.instagram.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.instagram.mapper.MemberMapper;
import kr.co.m2m.instagram.model.MemberVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
		
	//회원 검색
	@Override
	public List<MemberVO> getAllMembers() {
		return memberMapper.selectAllMembers();
	}
	
	//회원 가입
	@Override
	@Transactional
	public void insertMember(MemberVO memberVO) {
		memberMapper.insertMember(memberVO);
	}
	
}
