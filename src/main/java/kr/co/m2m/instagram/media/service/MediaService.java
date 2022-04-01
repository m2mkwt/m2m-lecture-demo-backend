package kr.co.m2m.instagram.media.service;

import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.model.MediaVO;

public interface MediaService {
	
	// 이미지 추가 (return : mediaNo)
	public void insertMedia(FileVO file);
	
	// 이미지 수정 (기존 mediaNo에 해당하는 이미지 deleteYn=Y 처리 후, 신규 file insert)
	public void updateMedia(FileVO file, int mediaNo);
	
	// 이미지 삭제 (리스트 출력되지 않도록 delete_yn 값 'Y' 설정)
	public void deleteMedia(int mediaNo);
	
	// 이미지 조회
	public MediaVO selectMedia(int mediaNo);
	
}
