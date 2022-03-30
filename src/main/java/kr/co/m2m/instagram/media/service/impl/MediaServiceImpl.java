package kr.co.m2m.instagram.media.service.impl;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.m2m.instagram.media.mapper.MediaMapper;
import kr.co.m2m.instagram.media.model.MediaVO;
import kr.co.m2m.instagram.media.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	MediaMapper mediaMapper;
	
	// 이미지 추가
	@Override
	public int insertMedia(MultipartFile file) {
		String filename = uploadFile(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertMedia(vo);
		return vo.getMediaNo();
	}

	// 이미지 수정
	@Override
	public int updateMedia(MultipartFile file, int mediaNo) {
		String filename = uploadFile(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		vo.setMediaNo(mediaNo);
		return mediaMapper.updateMedia(vo);
	}

	// 이미지 삭제
	@Override
	public int deleteMedia(int mediaNo) {
		return mediaMapper.deleteMedia(mediaNo);
	}

	// 이미지 조회
	@Override
	public MediaVO selectMedia(int mediaNo) {
		return mediaMapper.selectMedia(mediaNo);
	}

	// 파일 업로드
	@Override
	public String uploadFile(MultipartFile file) {
		String dirPath = "C:\\savedir";
		String filename = file.getOriginalFilename();
		if (!file.isEmpty()) {
			File f = new File(dirPath, filename);
			try {
				file.transferTo(f);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		return filename;
	}


}
