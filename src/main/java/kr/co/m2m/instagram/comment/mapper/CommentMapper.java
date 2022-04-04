package kr.co.m2m.instagram.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.post.model.PostVO;


//@Repository("kr.co.m2m.instagram.mapper.CommentMapper")
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
    
    //좋아요
	public int likesCount(PostVO vo);

	public List<PostVO> likesCnt(PostVO vo);





}



