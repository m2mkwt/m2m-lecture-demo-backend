package kr.co.m2m.instagram.comment.service;

import java.util.List;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;

/**
 * <pre>
 * 프로젝트명	: instagram
 * 패키지명		: kr.co.m2m.instagram.comment.service
 * 파일명		: CommentService.java
 * 작성일		: 2022-04-08
 * 작성자		: "gwLee"
 * 설명		 	: 
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	"gwLee" - 최초생성
 * </pre>
 */
public interface CommentService {

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 댓글 전체
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param cv
   * @return
   */
  public List<CommentVO> listComment(CommentVO cv);


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 댓글 작성
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param cp
   * @return
   */
  public String insertComment(CommentPO cp);


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 댓글 수정
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param cp
   * @return
   */
  public String updateComment(CommentPO cp);


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 댓글 삭제
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param cp
   * @return
   */
  public String deleteComment(CommentPO cp);

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 좋아요 1씩증가
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param po
   * @return
   */
  public String updateLikes(CommentPO po);


  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 좋아요 중복방지
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. "gwLee" - 최초생성
   * </pre>
   *
   * @param so
   * @return
   */
  public int getLikesCnt(CommentSO so);

}
