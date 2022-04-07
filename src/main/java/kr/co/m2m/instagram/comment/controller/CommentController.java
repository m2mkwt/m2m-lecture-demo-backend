package kr.co.m2m.instagram.comment.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import kr.co.m2m.framework.auth.BEAuthDetailModel;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.CommentService;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;


/**
 * <pre>
 * 프로젝트명	: instagram
 * 패키지명		: kr.co.m2m.instagram.comment.controller
 * 파일명		: CommentController.java
 * 작성일		: 2022-04-07
 * 작성자		: "gwLee"
 * 설명		 	: 댓글 Controller 
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"gwLee" - 최초생성
 * </pre>
 */
@Slf4j
@RestController // JSON데이터 요청,응답
@RequestMapping("/api/v1/comment") // vue. uri 설정
@CrossOrigin("*") // vue, axios 사용을 위한
public class CommentController {

  @Autowired
  private CommentService commentService;
  private PostService postService;

  @GetMapping("selectCommentlist") // 댓글 리스트
  public ResponseEntity<? extends BasicResponse> selectCommentlist(CommentVO cv, Model model) {
    List<CommentVO> commentList = commentService.listComment(cv);
    log.info("listComment select Parameter (VO) : {}" + cv);
    model.addAttribute("commentList", commentList);
    return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
  }

  @ResponseBody
  @PostMapping("addComment") // 댓글 작성
  public ResponseEntity<? extends BasicResponse> addComment(@Valid @RequestBody CommentPO cp,
      Model model) {
    String result = commentService.insertComment(cp);
    if (result.contentEquals("insert Success")) {
      CommentVO cv = new CommentVO();
      cv.setPostNo(cp.getPostNo());
      List<CommentVO> commentList = commentService.listComment(cv);
      return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse(result));
    }
  }

  @ResponseBody
  @PostMapping(value = "editComment") // 댓글 수정
  public ResponseEntity<? extends BasicResponse> editComment(@RequestBody CommentPO cp) {
    String result = commentService.updateComment(cp);
    if (result.contentEquals("update Success")) {
      CommentVO cv = new CommentVO();
      cv.setPostNo(cp.getPostNo());
      List<CommentVO> commentList = commentService.listComment(cv);
      return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse(result));
    }
  }

  @ResponseBody
  @PostMapping(value = "removeComment") // 댓글 삭제
  public ResponseEntity<? extends BasicResponse> removeComment(@RequestBody CommentPO cp) {
    String result = commentService.deleteComment(cp);
    if (result.contentEquals("delete Success")) {
      CommentVO cv = new CommentVO();
      cv.setPostNo(cp.getPostNo());
      List<CommentVO> commentList = commentService.listComment(cv);
      return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse(result));
    }
  }

  @ResponseBody
  @PostMapping(value = "likePost") // 좋아요 증가
  public ResponseEntity<? extends BasicResponse> likePost(@RequestBody CommentPO po) {

    BEAuthDetailModel userInfo = null;
    Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
    if (details instanceof BEAuthDetailModel) {
      userInfo = (BEAuthDetailModel) details;
    }
    log.info("userInfo : {}", userInfo);
    po.setMemberNo(userInfo.getMemberNo());

    String result = commentService.updateLikes(po);
    if (result.contentEquals("like Success")) {
      CommentSO so = new CommentSO();
      so.setPostNo(po.getPostNo());
      Integer cnt = commentService.getLikesCnt(so);
      return ResponseEntity.ok().body(new CommonResponse<Integer>(cnt));
    } else {
      return ResponseEntity.internalServerError().body(new ErrorResponse(result));
    }
  }
}
