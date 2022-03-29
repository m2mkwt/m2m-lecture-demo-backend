package kr.co.m2m.instagram.member.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.mapper.MemberMapper;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	//회원 검색
	@Override
	public LoginVO getLoginMember(LoginSO so){
		return memberMapper.getLoginMember(so);
	}

	
	//회원 가입
	@Override
//	@Transactional
	public void insertMember(MemberVO memberVO) {
		memberMapper.insertMember(memberVO);
	}
	
	//회원 수정
	@Override
	public void updateMember(MemberVO memberVO) {
		
		
	}

	
	//아이디 체크
	@Override
	public int idCheck(MemberVO memberVO) {
		int result = memberMapper.idCheck(memberVO);
		return result;
	}
	
	
	
}
