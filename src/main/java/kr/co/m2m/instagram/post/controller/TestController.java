package kr.co.m2m.instagram.post.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.m2m.framework.web.model.BasicResponse;
import kr.co.m2m.framework.web.model.CommonResponse;
import kr.co.m2m.framework.web.model.ResultListModel;
import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@RequestMapping("selectAll") // post내용물 나오는 페이지
	public ResponseEntity<? extends BasicResponse> selectAll(PostVO vo,Model model) {
		ResultListModel<PostVO> result = postService.selectAll(vo);
		return ResponseEntity.ok().body(new CommonResponse<ResultListModel<PostVO>>(result));
	}
}
