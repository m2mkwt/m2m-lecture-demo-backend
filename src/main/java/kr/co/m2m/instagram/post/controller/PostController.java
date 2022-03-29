package kr.co.m2m.instagram.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	
	@RequestMapping("list") // post내용물 나오는 페이지
	public ResponseEntity<? extends BasicResponse> list(PostVO vo,Model model) {
		List<PostVO> postList = postService.selectPost(vo);
		log.info("select Parameter (VO) : {}"+ vo);
		model.addAttribute("postList",postList);
		return ResponseEntity.ok().body(new CommonResponse<List<PostVO>>(postList));
	}
	
//	@ResponseBody
//	@RequestMapping("addPost")  //글업로드
//	public ResponseEntity<? extends BasicResponse> addPost(PostPO po,MultipartFile file) {
//		log.info("Input Parameter (PO) : {}"+ po);
//		ResultModel<String> result = postService.insertPost(po);
//		
//		return ResponseEntity.ok().body(new CommonResponse<ResultModel<String>>(result));
//	}
	
//	@RequestMapping("detail") //게시글 눌렀을 때 
	

//	@ResponseBody
//	@RequestMapping("editPost") // 게시글 수정 
//	public ResponseEntity<? extends BasicResponse> editPost(PostPO po) {
//		log.info("update Parameter (PO) : {}"+ po);
//		ResultModel<PostVO> result = postService.updatePost(po);
//		return ResponseEntity.ok().body(new CommonResponse<ResultModel<PostVO>>(result));
//	}

//	@ResponseBody
//	@RequestMapping("removePost") 
//	public ResponseEntity<? extends BasicResponse> removePost(PostPO po) {
//		log.info("delete Parameter (PO) : {}"+ po);
//		ResultModel<String> result = postService.deletePost(po);
//		return ResponseEntity.ok().body(new CommonResponse<ResultModel<String>>(result));
//	}

	
	
	
}
