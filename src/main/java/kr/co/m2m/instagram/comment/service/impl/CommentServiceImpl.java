package kr.co.m2m.instagram.comment.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.comment.mapper.CommentMapper;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentMapper mCommentMapper;
	@Override
	public List<CommentVO> commentList(CommentVO cv){
        return mCommentMapper.commentList(cv);
    }
//	public int commentInsert(CommentVO cv){
//        return mCommentMapper.commentInsert(cv);
//    }
    
	public int commentUpdate(CommentVO cv){
        return mCommentMapper.commentUpdate(cv);
    }
    
	public int commentDelete(CommentVO cv){
        return mCommentMapper.commentDelete(cv);
    }
	@Override
	public ResultModel<String> commentInsert(CommentPO po) {
		// TODO Auto-generated method stub
		return null;
	}
}

