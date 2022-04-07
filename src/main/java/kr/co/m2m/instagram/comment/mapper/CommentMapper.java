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

  // 댓글 목록
  public List<CommentVO> listComment(CommentVO cv);

  // 댓글 작성
  public int insertComment(CommentPO cp);

  // 댓글 수정
  public int updateComment(CommentPO cp);

  // 댓글 삭제
  public int deleteComment(CommentPO cp);

  // 좋아요 1씩 증가만 됨
  public int updateLikes(CommentPO vo);

  // 좋아요 중복방지 적용
  public int getLikesCnt(CommentSO vo);
}


