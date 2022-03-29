package kr.co.m2m.instagram.comment.service;

import java.util.List;

import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;

public interface CommentService {
	//댓글 전체
	public List<CommentVO> commentList(CommentVO cv);
	//댓글 상세보기
	public List<CommentSO> selectComment(CommentSO cs);
	//댓글 작성
	public String insertComment(CommentPO cp);
	//댓글 수정
	public String updateComment(CommentPO cp);
	//댓글 삭제
	public String deleteComment(CommentPO cp);
	
}
