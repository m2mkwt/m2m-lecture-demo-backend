package kr.co.m2m.instagram.comment.service;

import java.util.List;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;

public interface CommentService {
	//댓글 전체
	public List<CommentVO> listComment(CommentVO cv);
	
	//댓글 작성
	public String insertComment(CommentPO cp);
	
	//댓글 수정
	public String updateComment(CommentPO cp);
	
	//댓글 삭제
	public String deleteComment(CommentPO cp);
	
	//좋아요 수 
	public String updateLikes(CommentPO po);
	
	//
	public int getLikesCnt(CommentSO so);

}
