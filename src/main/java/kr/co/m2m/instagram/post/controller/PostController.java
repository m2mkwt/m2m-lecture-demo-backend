package kr.co.m2m.instagram.post.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.m2m.instagram.post.model.PostVO;
import kr.co.m2m.instagram.post.service.impl.PostServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	
	@RequestMapping("select") // post내용물 나오는 페이지
	public String select(PostVO vo,Model model) {
		vo.setMember_id(1);
		System.out.println(vo.getText());
		List<PostVO> postList = postService.select(vo);
		model.addAttribute("postList",postList);
		return "post/post";
	}
	@RequestMapping("uploadJsp") 
	public String uploadJsp() {
		return "post/upload";
	}
	
	@RequestMapping("upload")
	public String upload(PostVO vo) {
		postService.insert(vo);
		return "redirect:/post/select";
		
	}
	
//	@RequestMapping("postDetail") //게시글 눌렀을 때 
	
//	@RequestMapping("update") // 게시글 수정 
	

//	@RequestMapping("delete") // 게시글 수정 

	
	
	
}
