package kr.co.m2m.instagram.media.service;

import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.instagram.media.model.MediaVO;

public interface MediaService {
	
	// 게시글 이미지 추가 (return : media_id)
	public int insertPostMedia(MultipartFile file);
	
	// 프로필 이미지 추가 (return : media_id)
	public int insertProfileMedia(MultipartFile file);
	
	// 이미지 수정
	public int updateMedia(MultipartFile file, int media_id);
	
	// 이미지 삭제 (리스트 출력되지 않도록 delete_yn 값 'Y' 설정)
	public int deleteMedia(int media_id);
	
	// 게시글 이미지 조회
	public MediaVO selectPostMedia(int post_id);
	
	// 프로필 이미지 조회
	public MediaVO selectProfileMedia(int media_id);
	
	// 파일 업로드
	public String uploadFile(MultipartFile file);
}
