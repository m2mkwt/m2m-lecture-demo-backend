package kr.co.m2m.instagram.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j // 로그출력에 사용함 (ex. log.debug(String), debug 외에 info, warn 등 사용 가능함)
@Controller
@RequestMapping("/")
public class MediaController {

//	@RequestMapping(value = "upload", method = RequestMethod.GET)
//	public String upload() {
//		return "/fileupload";
//	}
	
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file) {
		String dirPath = "C:\\savedir";
		if (!file.isEmpty()) {
			File f = new File(dirPath, file.getOriginalFilename());
			try {
				file.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return 	"login";
	}
}
