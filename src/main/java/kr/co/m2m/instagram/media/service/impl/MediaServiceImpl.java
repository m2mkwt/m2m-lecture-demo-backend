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
	
	@Override
	public int insertPostMedia(MultipartFile file) {
		String filename = uploadFile(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertPostMedia(vo);
		return vo.getMedia_id();
	}

	@Override
	public int insertProfileMedia(MultipartFile file) {
		String filename = uploadFile(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertProfileMedia(vo);
		return vo.getMedia_id();
	}

	@Override
	public int updateMedia(MultipartFile file, int media_id) {
		String filename = uploadFile(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		vo.setMedia_id(media_id);
		return mediaMapper.insertPostMedia(vo);
	}

	@Override
	public int deleteMedia(int media_id) {
		return mediaMapper.deleteMedia(media_id);
	}

	@Override
	public MediaVO selectPostMedia(int post_id) {
		return mediaMapper.selectPostMedia(post_id);
	}

	@Override
	public MediaVO selectProfileMedia(int media_id) {
		return mediaMapper.selectProfileMedia(media_id);
	}

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
