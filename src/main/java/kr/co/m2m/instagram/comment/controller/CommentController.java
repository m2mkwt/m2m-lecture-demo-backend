package kr.co.m2m.instagram.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.framework.web.model.ResultListModel;
import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.comment.model.CommentPO;
import kr.co.m2m.instagram.comment.model.CommentVO;
import kr.co.m2m.instagram.comment.service.impl.CommentServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment")

public class CommentController {
 
	@Autowired
    private CommentServiceImpl mCommentService;
        
    @RequestMapping("commentList") //댓글 리스트
    public ResponseEntity<? extends BasicResponse> commentList(CommentVO cv,Model model) {
    	List<CommentVO> commetList = mCommentService.commentList(cv);
    	model.addAttribute("commentList",commetList);
    	return ResponseEntity.ok().body(new CommonResponse<List<CommentVO>>(commetList));
    }
 
//	@RequestMapping("commentInsert")
//    public ResponseEntity<? extends BasicResponse> commentInsert (CommentPO po) {
//		log.debug("Input Parameter (PO) : {}", po);
//		ResultModel<String> result = mCommentService.commentInsert(po);
//        return ResponseEntity.ok().body(new CommonResponse<ResultModel<String>>(result));
//    }
	@PostMapping(value = "insert")
	public ResultModel<String> commentInsert(@RequestBody List<CommentPO> po) {
		log.info(" commentInsert Contorller Start..... SO : {}", po);
		return mCommentService.commentInsert(po);
	}
// 	@RequestMapping("commentInsert") //댓글 작성
// 	public String commentInsert(CommentVO cv,Model model) {
// 		int result = mCommentService.commentInsert(cv);
//    	return "post/commentList";
// 	}
	@PostMapping(value = "update")
	public ResultListModel<CommentVO> commentUpdate(@RequestBody List<CommentVO> vo) {
		log.info("Contorller Start..... SO : {}", vo);
		return mCommentService.commentUpdate(vo);
	}
	
	/* 
	 * @RequestMapping("update") //댓글 수정
	 * 
	 * @ResponseBody private int mCommentServiceUpdateProc(@RequestParam int
	 * cno, @RequestParam String content) throws Exception{
	 * 
	 * CommentVO comment = new CommentVO(); comment.setCno(cno);
	 * comment.setContent(content);
	 * 
	 * return mCommentService.commentUpdateService(comment); }
	 * 
	 * @RequestMapping("delete/{cno}") //댓글 삭제
	 * 
	 * @ResponseBody private int mCommentServiceDelete(@PathVariable int cno) throws
	 * Exception{
	 * 
	 * return mCommentService.commentDeleteService(cno); }
	 */
}

   