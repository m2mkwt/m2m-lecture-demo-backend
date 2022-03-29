package kr.co.m2m.instagram.media.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.instagram.media.model.MediaVO;

public interface MediaService {
	
	// 이미지 추가 (return : mediaNo)
	public int insertMedia(MultipartFile file);
	
	// 이미지 수정
	public int updateMedia(MultipartFile file, int mediaNo);
	
	// 이미지 삭제 (리스트 출력되지 않도록 delete_yn 값 'Y' 설정)
	public int deleteMedia(int mediaNo);
	
	// 이미지 조회
	public MediaVO selectMedia(int mediaNo);
	
	// 파일 업로드
	public String uploadFile(MultipartFile file);
}
