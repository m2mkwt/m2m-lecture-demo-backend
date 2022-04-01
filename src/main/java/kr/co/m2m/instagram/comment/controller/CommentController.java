package kr.co.m2m.instagram.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/comment")
@CrossOrigin("*")
public class CommentController {
 
	@Autowired
    private CommentServiceImpl commentService;
	private PostService postService;
	
    @GetMapping("selectCommentlist") //댓글 리스트
    public ResponseEntity<? extends BasicResponse> selectCommentlist(CommentVO cv,Model model) {
    	List<CommentVO> commentList = commentService.listComment(cv);
    	log.info("listComment select Parameter (VO) : {}"+ cv);
    	model.addAttribute("commentList",commentList);
    	return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
    }
    @ResponseBody
    @PostMapping("addComment") //댓글 작성
	public ResponseEntity<? extends BasicResponse> addComment(@Valid @RequestBody CommentPO cp,Model model) {
		String result = commentService.insertComment(cp);
		if(result.contentEquals("insert Success")) {
			CommentVO cv = new CommentVO();
			cv.setPostNo(cp.getPostNo());
			List<CommentVO> commentList = commentService.listComment(cv);
			return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
	}
    @ResponseBody
    @PostMapping(value = "editComment") //댓글 수정
	public ResponseEntity<? extends BasicResponse> editComment(@RequestBody CommentPO cp) {
		String result = commentService.updateComment(cp);
		if(result.contentEquals("update Success")) {
			CommentVO cv = new CommentVO();
			cv.setPostNo(cp.getPostNo());
			List<CommentVO> commentList = commentService.listComment(cv);
			return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
    @ResponseBody
    @PostMapping(value = "removeComment") //댓글 삭제
	public ResponseEntity<? extends BasicResponse> removeComment(@RequestBody CommentPO cp) {
		String result = commentService.deleteComment(cp);
		if(result.contentEquals("delete Success")) { 
			CommentVO cv = new CommentVO();
			cv.setPostNo(cp.getPostNo());
			List<CommentVO> commentList = commentService.listComment(cv);
			return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commentList));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
    @ResponseBody
    @PostMapping(value = "likePost")//좋아요 증가
    public ResponseEntity<? extends BasicResponse> likesCount(@RequestBody PostPO pp){
    	String result = commentService.likesCount(pp);
		if(result.contentEquals("like Success")) {
//			PostVO vo = new PostVO();
//			vo.setPostNo(pp.getPostNo());
//			List<PostVO> resultList = postService.selectList(vo);
//			return ResponseEntity.ok().body(new CommonResponse<List<PostVO>>(resultList));
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
    }
}
   