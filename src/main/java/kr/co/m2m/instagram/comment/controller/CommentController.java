package kr.co.m2m.instagram.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentSO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.impl.CommentServiceImpl;
import kr.co.m2m.instagram.post.model.PostPO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment")
@CrossOrigin("*")
public class CommentController {
 
	@Autowired
    private CommentServiceImpl commentService;
	
    @GetMapping("listComment") //댓글 전체 리스트
    public ResponseEntity<? extends BasicResponse> listComment(CommentVO cv,Model model) {
    	List<CommentVO> commentList = commentService.listComment(cv);
    	log.info("listComment select Parameter (VO) : {}"+ cv);
    	model.addAttribute("commentList",commentList);
    	return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
    }
    @GetMapping("select") //게시판 댓글
	public ResponseEntity<? extends BasicResponse> selectComment(CommentSO cs,Model model) {
    	List<CommentSO> selectComment = commentService.selectComment(cs);
    	model.addAttribute("selectComment",selectComment);
    	log.info("Contorller Start..... SO : {}", model);
		return ResponseEntity.ok().body(new CommonResponse<List<CommentSO>>(selectComment));
	}
    @ResponseBody
    @PostMapping("insert") //댓글 작성
	public ResponseEntity<? extends BasicResponse> insertComment(@Valid CommentPO cp,Model model) {
		String result = commentService.insertComment(cp);
		if(result.contentEquals("insert Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
	}
    @ResponseBody
    @PostMapping(value = "update") //댓글 수정
	public ResponseEntity<? extends BasicResponse> updateComment(CommentPO cp) {
		String result = commentService.updateComment(cp);
		if(result.contentEquals("update Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
    @ResponseBody
    @PostMapping(value = "delete") //댓글 삭제
	public ResponseEntity<? extends BasicResponse> deleteComment(CommentPO cp) {
		String result = commentService.deleteComment(cp);
		if(result.contentEquals("delete Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
    @ResponseBody
    @PostMapping(value = "likes")//좋아요 증가
    public ResponseEntity<? extends BasicResponse> likesCount(PostPO pp){
    	String result = commentService.likesCount(pp);
		if(result.contentEquals("like Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
}
   