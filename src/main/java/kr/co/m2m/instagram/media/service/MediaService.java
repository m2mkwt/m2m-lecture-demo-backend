package kr.co.m2m.instagram.media.service;

import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.model.MediaVO;

public interface MediaService {
	
	// 이미지 추가 (프로필, 게시글)
	public FileVO insertProfileMedia(FileVO file);
	public FileVO insertPostMedia(FileVO file);
	
	// 이미지 수정 (프로필, 게시글) (기존 mediaNo에 해당하는 이미지 deleteYn=Y 처리 후, 신규 file insert)
	public FileVO updateProfileMedia(FileVO file);
	public FileVO updatePostMedia(FileVO file);
	
	// 이미지 삭제 (프로필, 게시글) (리스트 출력되지 않도록 delete_yn 값 'Y' 설정)
	public void deleteProfileMedia(FileVO file);
	public void deletePostMedia(FileVO file);
	
	// 이미지 조회
	public MediaVO selectMedia(int mediaNo);
	
}
