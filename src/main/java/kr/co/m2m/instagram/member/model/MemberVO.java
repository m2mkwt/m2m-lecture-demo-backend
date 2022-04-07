package kr.co.m2m.instagram.member.model;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.instagram.member.model
 * 파일명		: MemberVO.java
 * 작성일		: 2022-04-07
 * 작성자		: bkJung
 * 설명		 	: 회원가입의 VO파일입니다.
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	bkJung - 최초생성
 * </pre>
 */
@Data
public class MemberVO {

  // 인덱스
  private int memberNo;
  // 로그인아이디
  @NotBlank
  private String loginId;
  // 유저이름
  @NotBlank
  private String userName;
  // 이메일
  @NotBlank
  private String email;
  // 비밀번호
  @NotBlank
  private String password;
  // 성별
  private char gender;
  // 삭제y/n
  private char deleteYn;
  // 사진번호
  private int mediaNo;

}
