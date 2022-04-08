package kr.co.m2m.instagram.profile.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kr.co.m2m.framework.auth.BEAuthDetailModel;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.media.service.MediaService;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.profile.controller
 * 파일명		: ProfileController.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 프로필 설정을 위한 컨트롤러
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@RestController // 레스트 컨트롤러 (데이터 json 처리)
@RequestMapping("/api/v1/profile") // 호출 URL
public class ProfileController {

  @Autowired
  private MediaService mediaService;

  @Autowired
  private MemberService memberService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  /**
   * <pre>
   * 작성일 : 2022-04-07
   * 작성자 : "cshwang"
   * 설명   : 프로필(회원 정보) 조회
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-07. "cshwang" - 최초생성
   * </pre>
   *
   * @param memberNo (회원번호)
   * @return result(맵) (mvo(회원정보), filename(회원 프로필 이미지 파일명))
   */
  @RequestMapping("getProfile")
  public ResponseEntity<? extends BasicResponse> selectMember(@RequestParam int memberNo) {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    BEAuthDetailModel user = null;
    if (userDetails instanceof BEAuthDetailModel) {
      user = (BEAuthDetailModel) userDetails;
    }
    Map<String, Object> result = null;
    MemberVO mvo = null;
    if (user.getMemberNo() == memberNo) {
      result = new HashMap<>();
      mvo = memberService.selectMember(memberNo);
      log.info("Get Member Info : {}", mvo);
      int mediaNo = mvo.getMediaNo();
      String filename = "";
      if (mediaNo != 0)
        filename = mediaService.selectMedia(mediaNo).getFilename();
      result.put("mvo", mvo);
      result.put("filename", filename);
      return ResponseEntity.ok().body(new CommonResponse<Map<String, Object>>(result));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse("인증실패"));
    }
  }

  /**
   * <pre>
   * 작성일 : 2022-04-07
   * 작성자 : "cshwang"
   * 설명   : 프로필(회원 정보) 수정
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-07. "cshwang" - 최초생성
   * </pre>
   *
   * @param mvo (수정할 회원 정보)
   * @return result (문자열 : 수정 성공)
   */
  @RequestMapping(value = "editProfile", method = RequestMethod.POST)
  public ResponseEntity<? extends BasicResponse> updateProfile(@RequestBody MemberVO mvo) {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    BEAuthDetailModel user = null;
    if (userDetails instanceof BEAuthDetailModel) {
      user = (BEAuthDetailModel) userDetails;
    }
    if (user.getMemberNo() == mvo.getMemberNo()) {
      String result = memberService.updateMember(mvo);
      log.info("Update Member Profile : {}", result);
      return ResponseEntity.ok().body(new CommonResponse<String>(result));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse("인증실패"));
    }
  }

  /**
   * <pre>
   * 작성일 : 2022-04-07
   * 작성자 : "cshwang"
   * 설명   : 회원 비밀번호 수정
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-07. "cshwang" - 최초생성
   * </pre>
   *
   * @param map
   * @return result (문자열 : 수정 성공, 비밀번호 불일치, 인증 실패)
   */
  @RequestMapping(value = "editPassword", method = RequestMethod.POST)
  public ResponseEntity<? extends BasicResponse> updatePassword(
      @RequestBody Map<String, Object> map) {
    Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
    BEAuthDetailModel user = null;
    int memberNo = 0;
    if (userDetails instanceof BEAuthDetailModel) {
      user = (BEAuthDetailModel) userDetails;
    }
    memberNo = Integer.parseInt(map.get("memberNo").toString());
    if (user.getMemberNo() == memberNo) {
      String oldPassword = map.get("oldPassword").toString();
      String dbPassword = memberService.selectPassword(memberNo);
      if (passwordEncoder.matches(oldPassword, dbPassword)) {
        String newPassword = passwordEncoder.encode(map.get("newPassword").toString());
        MemberVO mvo = new MemberVO();
        mvo.setMemberNo(memberNo);
        mvo.setPassword(newPassword);
        String result = memberService.updatePassword(mvo);
        return ResponseEntity.ok().body(new CommonResponse<String>(result));
      } else {
        return ResponseEntity.internalServerError().body(new ErrorResponse("비밀번호가 일치하지 않습니다."));
      }
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse("인증 실패"));
    }
  }
}
