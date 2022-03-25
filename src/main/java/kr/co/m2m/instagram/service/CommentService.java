package kr.co.m2m.instagram.service;

import java.util.List;

import kr.co.m2m.instagram.model.CommentVO;

public interface CommentService {

    public List<CommentVO> commentListService() throws Exception;
    
    public int commentInsertService(CommentVO comment) throws Exception;
    
    public int commentUpdateService(CommentVO comment) throws Exception;
    
    public int commentDeleteService(int cno) throws Exception;
}

