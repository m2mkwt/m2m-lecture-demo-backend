package kr.co.m2m.instagram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
public class MainController {
	
	@RequestMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/login_processing'")
	public String loginProcessing() {
		return "login_processing'";
	}
	
	@GetMapping("/login-error")
	public String loginError() {
		return "login-error";
	}
}
