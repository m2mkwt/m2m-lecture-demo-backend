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
import kr.co.m2m.instagram.member.mapper.MemberMapper;
import kr.co.m2m.instagram.post.mapper.PostMapper;

@Service
public class MediaServiceImpl implements MediaService {

	@Autowired
	private MediaMapper mediaMapper;
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Autowired
	private PostMapper postMapper;
	
	// 프로필 이미지 추가
	@Override
	public FileVO insertProfileMedia(FileVO file) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		int mediaNo = mediaMapper.insertMedia(vo);
		file.setMediaNo(mediaNo);
		mediaMapper.updateProfileMedia(file);
		return file;
	}
	// 게시글 이미지 추가
	@Override
	public FileVO insertPostMedia(FileVO file) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		int mediaNo = mediaMapper.insertMedia(vo);
		file.setMediaNo(mediaNo);
		return file;
	}

	// 프로필 이미지 수정
	@Override
	public FileVO updateProfileMedia(FileVO file) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
	    mediaMapper.deleteMedia(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		mediaMapper.insertMedia(vo);
		file.setMediaNo(vo.getMediaNo());
		mediaMapper.updateProfileMedia(file);
		return file;
	}
	// 게시글 이미지 수정
	@Override
	public FileVO updatePostMedia(FileVO file) {
	    String filename = file.getFileUrl() + '/' + file.getFileName();
	    mediaMapper.deleteMedia(file);
		MediaVO vo = new MediaVO();
		vo.setFilename(filename);
		int mediaNo = mediaMapper.insertMedia(vo);
		file.setMediaNo(mediaNo);
		mediaMapper.updatePostMedia(file);
		return file;
	}

	// 프로필 이미지 삭제
	@Override
	public void deleteProfileMedia(FileVO file) {
		mediaMapper.deleteMedia(file);
	}
	// 게시글 이미지 삭제
	@Override
	public void deletePostMedia(FileVO file) {
		mediaMapper.deleteMedia(file);
	}
	

	// 이미지 조회
	@Override
	public MediaVO selectMedia(int mediaNo) {
		return mediaMapper.selectMedia(mediaNo);
	}

}
