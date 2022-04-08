package kr.co.m2m.instagram.comment.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;


// @Repository("kr.co.m2m.instagram.mapper.CommentMapper")
/**
 * <pre>
 * 프로젝트명	: instagram
 * 패키지명		: kr.co.m2m.instagram.comment.mapper
 * 파일명		: CommentMapper.java
 * 작성일		: 2022-04-07
 * 작성자		: "gwLee"
 * 설명		 	: 댓글 Mapper
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"gwLee" - 최초생성
 * </pre>
 */
@Mapper
public interface CommentMapper {

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : "gwLee"
   * 설명   : 댓글 목록
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
  public int insertComment(CommentPO cp);

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
  public int updateComment(CommentPO cp);


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
  public int deleteComment(CommentPO cp);

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
   * @param vo
   * @return
   */
  public int updateLikes(CommentPO vo);


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
   * @param vo
   * @return
   */
  public int getLikesCnt(CommentSO vo);
}


