package kr.co.m2m.instagram.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ResultModel;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	
	@GetMapping("list") // 게시글 전체 리스트 조회
	public ResponseEntity<? extends BasicResponse> list(PostVO vo,Model model) {
		List<PostVO> resultList = postService.selectList(vo);
		log.info("list select Parameter (VO) : {}"+ vo);
		model.addAttribute("postList",resultList);
		return ResponseEntity.ok().body(new CommonResponse<List<PostVO>>(resultList));
	}
	
	@GetMapping("detail") // 게시글 상세 내용 조회
	public ResponseEntity<? extends BasicResponse> detail(PostVO vo,Model model) {
		List<PostVO> resultList = postService.selectDetail(vo);
		log.info("detail select Parameter (VO) : {}"+ vo);
		model.addAttribute("postDetailList",resultList);
		return ResponseEntity.ok().body(new CommonResponse<List<PostVO>>(resultList));
	}
	
	@ResponseBody
	@PostMapping("addPost")  //게시글 업로드
	public ResponseEntity<? extends BasicResponse> addPost(PostPO po,MultipartFile file) {
		log.info("Input Parameter (PO) : {}"+ po);
		String result = postService.insertPost(po);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}
	

	@ResponseBody
	@PostMapping("editPost") // 게시글 수정 
	public ResponseEntity<? extends BasicResponse> editPost(PostPO po) {
		log.info("update Parameter (PO) : {}"+ po);
		String result = postService.updatePost(po);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}

	@ResponseBody
	@PostMapping("removePost") 
	public ResponseEntity<? extends BasicResponse> removePost(PostPO po) {
		log.info("delete Parameter (PO) : {}"+ po);
		String result = postService.deletePost(po);
		return ResponseEntity.ok().body(new CommonResponse<String>(result));
	}

	
	
	
}
