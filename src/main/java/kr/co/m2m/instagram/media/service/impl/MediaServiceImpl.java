package kr.co.m2m.instagram.media.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.mapper.MediaMapper;
import kr.co.m2m.instagram.media.model.MediaVO;
import kr.co.m2m.instagram.media.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	MediaMapper mediaMapper;
	
	// 이미지 추가
	@Override
	public void insertMedia(FileVO file) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertMedia(vo);
	}

	// 이미지 수정
	@Override
	public void updateMedia(FileVO file, int mediaNo) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
	    mediaMapper.deleteMedia(mediaNo);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertMedia(vo);
	}

	// 이미지 삭제
	@Override
	public void deleteMedia(int mediaNo) {
		mediaMapper.deleteMedia(mediaNo);
	}

	// 이미지 조회
	@Override
	public MediaVO selectMedia(int mediaNo) {
		return mediaMapper.selectMedia(mediaNo);
	}

}
