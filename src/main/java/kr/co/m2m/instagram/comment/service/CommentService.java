package kr.co.m2m.instagram.comment.service;

import java.util.List;

import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentVO;

public interface CommentService {
	
	public List<CommentVO> commentList(CommentVO cv);
	public ResultModel<String> commentInsert(CommentPO po);
	public int commentUpdate(CommentVO cv);
	public int commentDelete(CommentVO cv);

}
