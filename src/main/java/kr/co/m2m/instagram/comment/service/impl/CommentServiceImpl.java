package kr.co.m2m.instagram.comment.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.co.m2m.instagram.comment.mapper.CommentMapper;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.CommentService;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
//@Transactional(rollbackFor = Throwable.class)
public class CommentServiceImpl implements CommentService {
	
	//private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommentMapper commentMapper;
	
	//@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	@Override
	//댓글전체목록
	public List<CommentVO> listComment(CommentVO cv){
		List<CommentVO> commentList = commentMapper.listComment(cv);
        return commentList;
    }
	//댓글 작성
	public String insertComment(CommentPO cp) {
		int result = commentMapper.insertComment(cp);
		if(result==1) {
			return "insert Success";
		}else {
			return "insert Fail";
		}
	}
	//댓글 수정
	public String updateComment(CommentPO cp) {
		int result = commentMapper.updateComment(cp);
		if(result==1) {
			return "update Success";
		}else {
			return "update Fail";
		}
	}
	//댓글 삭제 (Update)
	public String deleteComment(CommentPO cp){
		int result = commentMapper.deleteComment(cp);
		if(result==1) {
			return "delete Success";
		}else {
			return "delete Fail";
		}
	}
	//좋아요
	public String likesCount(PostVO vo) {
		int result = commentMapper.likesCount(vo);
		if(result==1) {
			return "like Success";
		}else {
			return "like Fail";
	}
}
	public List<PostVO> likesCnt(PostVO vo) {
		List<PostVO> likesCnt = commentMapper.likesCnt(vo);
        return likesCnt;
	}
}



