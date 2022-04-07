package kr.co.m2m.instagram.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import kr.co.m2m.instagram.common.model.LoginSO;
import kr.co.m2m.instagram.common.model.LoginVO;
import kr.co.m2m.instagram.member.model.MemberVO;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.instagram.member.mapper
 * 파일명		: MemberMapper.java
 * 작성일		: 2022-04-07
 * 작성자		: bkJung
 * 설명		 	: 회원가입의 매퍼파일입니다.
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	bkJung - 최초생성
 * </pre>
 */
@Mapper
public interface MemberMapper {

  // 회원 검색
  LoginVO getLoginMember(LoginSO so);

  // 회원 가입
  void insertMember(MemberVO memberVO);

  // 회원정보 수정
  int updateMember(MemberVO memberVO);

  // 회원정보 조회
  MemberVO selectMember(int memberNo);

  // 아이디 체크
  int idCheck(MemberVO memberVO);

  // 비밀번호 수정
  int updatePassword(MemberVO memberVO);

  // 비밀번호 조회
  String selectPassword(int memberNo);

}
