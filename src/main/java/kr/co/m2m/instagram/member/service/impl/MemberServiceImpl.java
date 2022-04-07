package kr.co.m2m.instagram.member.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.mapper.MemberMapper;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.instagram.member.service.impl
 * 파일명		: MemberServiceImpl.java
 * 작성일		: 2022-04-07
 * 작성자		: bkJung
 * 설명		 	: 회원가입의 서비스IMPL입니다.
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	bkJung - 최초생성
 * </pre>
 */
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

  @Autowired
  private MemberMapper memberMapper;

  // 회원 검색
  @Override
  public LoginVO getLoginMember(LoginSO so) {
    return memberMapper.getLoginMember(so);
  }


  // 회원 가입
  @Override
  // @Transactional
  public void insertMember(MemberVO memberVO) {
    memberMapper.insertMember(memberVO);
  }

  // 회원 수정
  @Override
  public String updateMember(MemberVO memberVO) {
    int result = memberMapper.updateMember(memberVO);
    String msg;
    if (result > 0)
      msg = "update success";
    else
      msg = "update failure";
    return msg;
  }


  // 아이디 체크
  @Override
  public int idCheck(MemberVO memberVO) {
    int result = memberMapper.idCheck(memberVO);
    return result;
  }

  // 회원검색-프로필 수정용
  @Override
  public MemberVO selectMember(int memberNo) {
    return memberMapper.selectMember(memberNo);
  }

  // 회원 비밀번호 업데이트 -프로필 수정용
  @Override
  public String updatePassword(MemberVO memberVO) {
    int result = memberMapper.updatePassword(memberVO);
    String msg;
    if (result > 0)
      msg = "update success";
    else
      msg = "update failure";
    return msg;
  }

  // 회원 비밀번호 검색-프로필 수정용
  @Override
  public String selectPassword(int memberNo) {
    return memberMapper.selectPassword(memberNo);
  }

}
