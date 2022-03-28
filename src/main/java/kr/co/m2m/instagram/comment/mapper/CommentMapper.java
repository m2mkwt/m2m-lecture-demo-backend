package kr.co.m2m.instagram.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentVO;


//@Repository("kr.co.m2m.instagram.mapper.CommentMapper")
@Mapper
public interface CommentMapper {
 
    // 댓글 목록
	public List<CommentVO> commentList(CommentVO cv);
 
    // 댓글 작성
	// public int commentInsert(CommentVO cv);
	public ResultModel<String> commentInsert(CommentPO po);    
    // 댓글 수정
    public int commentUpdate(CommentVO cv);
 
    // 댓글 삭제
    public int commentDelete(CommentVO cv);

}



