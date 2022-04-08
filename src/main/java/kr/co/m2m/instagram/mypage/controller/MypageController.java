package kr.co.m2m.instagram.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import kr.co.m2m.framework.auth.BEAuthDetailModel;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.media.service.MediaService;
import kr.co.m2m.instagram.member.model.MemberVO;
import kr.co.m2m.instagram.member.service.MemberService;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.mypage.controller
 * 파일명		: MypageController.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 마이페이지 데이터 처리 컨트롤러
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@RestController // 레스트 컨트롤러 (데이터 json 처리)
@RequestMapping("/api/v1/mypage") // 호출 URL
public class MypageController {

  @Autowired
  PostService postService;

  @Autowired
  MemberService memberService;

  @Autowired
  MediaService mediaService;

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "cshwang"
   * 설명  : 회원 정보 조회
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "cshwang" - 최초생성
   * </pre>
   *
   * @param memberNo
   * @return result 맵 (mvo (회원정보), imgName (이미지파일명))
   */
  @GetMapping("getMember")
  public ResponseEntity<? extends BasicResponse> getMember(int memberNo) {
    Object obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
    BEAuthDetailModel adm;
    int authMemberNo = 0;
    if (obj instanceof BEAuthDetailModel) {
      adm = (BEAuthDetailModel) obj;
      authMemberNo = adm.getMemberNo();
    }

    Map<String, Object> result = new HashMap<>();

    if (memberNo == authMemberNo) {
      MemberVO mvo = memberService.selectMember(memberNo);
      result.put("mvo", mvo);
      int mediaNo = mvo.getMediaNo();
      if (mediaNo > 0) {
        String imgName = mediaService.selectMedia(mediaNo).getFilename();
        result.put("imgName", imgName);
      }
      log.info("count My Post : total {}", result);

      return ResponseEntity.ok().body(new CommonResponse<Map<String, Object>>(result));
    } else {
      String error = "unauthorized";
      return ResponseEntity.internalServerError().body(new ErrorResponse(error));
    }
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "cshwang"
   * 설명  : 내 게시글 목록(게시글번호,파일명) 리스트 조회 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "cshwang" - 최초생성
   * </pre>
   *
   * @param memberNo
   * @return result 맵 리스트 (내 게시글 정보 리스트)
   */
  @GetMapping("searchPostList")
  public ResponseEntity<? extends BasicResponse> selectMyPost(int memberNo) {
    List<Map<String, String>> result = postService.selectMyPost(memberNo);
    log.info("count My Post : total {}", result);
    return ResponseEntity.ok().body(new CommonResponse<List<Map<String, String>>>(result));
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "cshwang"
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "cshwang" - 최초생성
   * </pre>
   *
   * @param memberNo
   * @return result (내 게시글 총 개수)
   */
  @GetMapping("getPostCnt")
  public ResponseEntity<? extends BasicResponse> countPost(int memberNo) {
    int result = postService.countPost(memberNo);
    log.info("select Count MembeNo : {}", memberNo);
    return ResponseEntity.ok().body(new CommonResponse<Integer>(result));
  }
}
