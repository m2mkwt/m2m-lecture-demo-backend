package kr.co.m2m.instagram.post.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ErrorResponse;
import kr.co.m2m.instagram.post.model.PostPO;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.PostService;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/api/v1/post")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	@GetMapping("selectPostList") // 게시글 전체 리스트 조회
	public ResponseEntity<? extends BasicResponse> selectPostList(PostVO vo,Model model) {
		List<PostVO> resultList = postService.selectList(vo);
		log.info("list select Parameter (VO) : {}"+ vo);
		model.addAttribute("postList",resultList);
		return ResponseEntity.ok().body(new CommonResponse<List<PostVO>>(resultList));
	}
	
	@GetMapping("getPost") // 게시글 상세 내용 조회
	public ResponseEntity<? extends BasicResponse> getPostDetail(PostVO vo,Model model) {
		PostVO resultList = postService.selectDetail(vo);
		log.info("detail select Parameter (VO) : {}"+ vo);
		model.addAttribute("postDetailList",resultList);
		return ResponseEntity.ok().body(new CommonResponse<PostVO>(resultList));
	}
	
	@GetMapping("getProfile") // 게시글 상세 내용 조회
	public ResponseEntity<? extends BasicResponse> getProfile(PostVO vo,Model model) {
		PostVO resultList = postService.selectDetail(vo);
		log.info("detail select Parameter (VO) : {}"+ vo);
		model.addAttribute("postDetailList",resultList);
		return ResponseEntity.ok().body(new CommonResponse<PostVO>(resultList));
	}

	@ResponseBody
	@PostMapping("addPost")  //게시글 업로드
	public ResponseEntity<? extends BasicResponse> addPost(@RequestBody PostPO po,MultipartFile file) {
		log.info("Input Parameter (PO) : {}"+ po);
		String result = postService.insertPost(po);
		if(result.contentEquals("insert Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
	}

	@ResponseBody
	@PostMapping("editPost") // 게시글 수정 
	public ResponseEntity<? extends BasicResponse> editPost(@RequestBody PostPO po) {
		log.info("update Parameter (PO) : {}"+ po);
		String result = postService.updatePost(po);
		if(result.contentEquals("update Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
	}

	@ResponseBody
	@PostMapping("removePost") // 게시글 삭제
	public ResponseEntity<? extends BasicResponse> removePost(@RequestBody PostPO po) {
		log.info("delete Parameter (PO) : {}"+ po);
		String result = postService.deletePost(po);
		if(result.contentEquals("delete Success")) {
			return ResponseEntity.ok().body(new CommonResponse<String>(result));
		}else {
			return ResponseEntity.internalServerError().body(new ErrorResponse(result));
		}
	}
	
	// 내 게시글 갯수 조회
	@GetMapping("getPostCnt")
	public ResponseEntity<? extends BasicResponse> getPostCnt(int memberNo) {
		int result = postService.countPost(memberNo);
		log.info("count My Post : total {}"+ result);
		return ResponseEntity.ok().body(new CommonResponse<Integer>(result));
	}
	
	// 내 게시글 리스트 조회
	@GetMapping("searchPostList")
	public ResponseEntity<? extends BasicResponse> searchPostList(int memberNo, Model model) {
		List<Map<String, String>> myPost = postService.selectMyPost(memberNo);
		log.info("select Count MembeNo : {}" + memberNo);
		model.addAttribute("myPost", myPost);
		return ResponseEntity.ok().body(new CommonResponse<List<Map<String, String>>>(myPost));
	}
	
	
}
