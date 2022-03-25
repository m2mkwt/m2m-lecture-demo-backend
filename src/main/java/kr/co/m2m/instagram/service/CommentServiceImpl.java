package kr.co.m2m.instagram.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.m2m.instagram.mapper.CommentMapper;
import kr.co.m2m.instagram.model.CommentVO;

@Service
public class CommentServiceImpl implements CommentService{
	@Autowired
    CommentMapper mCommentMapper;
    
	@Override
    public List<CommentVO> commentListService() throws Exception{
        return mCommentMapper.commentList();
    }
    
	@Override
    public int commentInsertService(CommentVO comment) throws Exception{
        return mCommentMapper.commentInsert(comment);
    }
    
	@Override
    public int commentUpdateService(CommentVO comment) throws Exception{
        return mCommentMapper.commentUpdate(comment);
    }
    
	@Override
    public int commentDeleteService(int cno) throws Exception{
        return mCommentMapper.commentDelete(cno);
    }
}

