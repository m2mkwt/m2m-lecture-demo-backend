package kr.co.m2m.instagram.comment.service;

import java.util.List;

import kr.co.m2m.instagram.comment.model.CommentVO;

public interface CommentService {
	
	public List<CommentVO> commentList(CommentVO cv);
//	public ResultModel<String> commentInsert(@Valid List<CommentPO> parameterModel);
//	public ResultListModel<CommentVO> commentUpdate(List<CommentVO> vo);
   
	public int commentDelete(CommentVO cv);
}
