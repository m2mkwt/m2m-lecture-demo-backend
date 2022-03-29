package kr.co.m2m.instagram.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
//import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
 
	@Autowired
    private CommentServiceImpl commentService;
	
    @RequestMapping("commentList") //댓글 리스트
    public ResponseEntity<? extends BasicResponse> commentList(CommentVO cv,Model model) {
    	List<CommentVO> commetList = commentService.commentList(cv);
    	model.addAttribute("commentList",commetList);
    	return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commetList));
    }
    @PostMapping("select") //댓글 상세보기
	public ResponseEntity<? extends BasicResponse> selectComment(CommentSO cs,Model model) {
    	List<CommentSO> selectComment = commentService.selectComment(cs);
		log.info("Contorller Start..... SO : {}", model);
		return ResponseEntity.ok().body(new CommonResponse<List<CommentSO>>(selectComment));
	}
    
    @PostMapping("insert")
	public ResponseEntity<? extends BasicResponse> insertComment(@Valid CommentPO cp,Model model) {
		String result = commentService.insertComment(cp);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}
    @PostMapping(value = "update")
	public ResponseEntity<? extends BasicResponse> updateComment(CommentPO cp) {
		String result = commentService.updateComment(cp);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}
    @PostMapping(value = "delete")
	public ResponseEntity<? extends BasicResponse> deleteComment(CommentPO cp) {
		String result = commentService.deleteComment(cp);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}
}
   