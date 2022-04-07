package kr.co.m2m.instagram.member.service;

import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.model.MemberVO;



/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.instagram.member.service
 * 파일명		: MemberService.java
 * 작성일		: 2022-04-07
 * 작성자		: bkJung
 * 설명		 	: 회원가입의 서비스입니다.
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	bkJung - 최초생성
 * </pre>
 */
public interface MemberService {

  // 회원 로그인
  public LoginVO getLoginMember(LoginSO so);

  // 회원 가입
  public void insertMember(MemberVO memberVO);

  // 회원 수정
  public String updateMember(MemberVO memberVO);

  // 회원 조회
  public MemberVO selectMember(int memberNo);

  // 아이디 체크
  public int idCheck(MemberVO memberVO);

  // 비밀번호 수정
  public String updatePassword(MemberVO memberVO);

  // 비밀번호 조회
  public String selectPassword(int memberNo);

}
