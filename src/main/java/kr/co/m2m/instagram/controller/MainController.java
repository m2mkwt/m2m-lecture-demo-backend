package kr.co.m2m.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main() {
		return "index";
	}
	
	@RequestMapping("/favicon.ico")
	@ResponseBody
	void returnNoFavicon() {
	}

	@RequestMapping("/accessDenied_page")
	public String accessDenied() {
		return "accessDenied_page";
	}

	@RequestMapping("/logout")
	public String logout() {
		return "logout";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String dispLogin() {
		return "index";
	}

	@RequestMapping("/")
	public String startPoint() {
		return "index";
	}
}
