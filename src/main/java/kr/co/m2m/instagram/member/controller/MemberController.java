package kr.co.m2m.instagram.member.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.m2m.framework.util.SecurityUtil;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.impl.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.instagram.member.controller
 * 파일명		: MemberController.java
 * 작성일		: 2022-04-07
 * 작성자		: bkJung
 * 설명		 	: 회원가입 컨트롤러입니다.
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	bkJung - 최초생성
 * </pre>
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

  @Autowired
  MemberServiceImpl memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  // 아이디 중복검사 mybatis 사용 API

  @RequestMapping(value = "checkValidId", produces = "application/json")
  public int checkValidId(@RequestBody MemberVO memberVO) {
    int result = memberService.idCheck(memberVO);
    return result;
  }

  // 패스워드 검사 SecurityUtil의 Static 메소드 사용 API

  @RequestMapping(value = "checkValidPw", produces = "application/json")
  public int checkValidPw(@RequestBody MemberVO memberVO) {
    int result = SecurityUtil.checkPassword(memberVO.getPassword());
    return result;
  }

  // 회원 가입 폼 API()
  @PostMapping("registMember")
  public String registMember(@Valid @RequestBody MemberVO memberVO) {
    // 패스워드 암호화
    String enc = passwordEncoder.encode(memberVO.getPassword());
    memberVO.setPassword(enc);
    // 회원정보 등록
    memberService.insertMember(memberVO);

    return "success";

  }

}
