package kr.co.m2m.instagram.media.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.mapper.MediaMapper;
import kr.co.m2m.instagram.media.model.MediaVO;
import kr.co.m2m.instagram.media.service.MediaService;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.media.service.impl
 * 파일명		: MediaServiceImpl.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 게시글/프로필에서 사용되는 이미지 등록/수정을 위한 서비스 구현
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
@Service
public class MediaServiceImpl implements MediaService {

  @Autowired
  private MediaMapper mediaMapper;

  // 게시글 이미지 추가
  @Override
  public FileVO insertPostMedia(FileVO file) {
    String filename = file.getFileUrl() + '/' + file.getFileName();
    MediaVO vo = new MediaVO();
    vo.setFilename(filename);
    mediaMapper.insertMedia(vo);
    file.setMediaNo(vo.getMediaNo());
    return file;
  }

  // 프로필 이미지 추가
  @Override
  public FileVO insertProfileMedia(FileVO file) {
    String filename = file.getFileUrl() + '/' + file.getFileName();
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
    mediaMapper.insertMedia(vo);
    file.setMediaNo(vo.getMediaNo());
    mediaMapper.updatePostMedia(file);
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

  // 게시글 이미지 삭제
  @Override
  public void deletePostMedia(FileVO file) {
    mediaMapper.deleteMedia(file);
  }

  // 프로필 이미지 삭제
  @Override
  public void deleteProfileMedia(FileVO file) {
    mediaMapper.deleteMedia(file);
  }

  // 이미지 1건 정보 조회 (프로필/게시글 무관)
  @Override
  public MediaVO selectMedia(int mediaNo) {
    return mediaMapper.selectMedia(mediaNo);
  }

}
