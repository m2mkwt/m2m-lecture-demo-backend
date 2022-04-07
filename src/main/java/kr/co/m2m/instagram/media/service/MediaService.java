package kr.co.m2m.instagram.media.service;

import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.model.MediaVO;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.media.service
 * 파일명		: MediaService.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 게시글/프로필에서 사용되는 이미지 등록/수정을 위한 서비스 인터페이스
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
public interface MediaService {

  // 게시글 이미지 추가
  public FileVO insertProfileMedia(FileVO file);

  // 프로필 이미지 추가
  public FileVO insertPostMedia(FileVO file);

  // 게시글 이미지 수정
  public FileVO updateProfileMedia(FileVO file);

  // 프로필 이미지 수정
  public FileVO updatePostMedia(FileVO file);

  // 게시글 이미지 삭제
  public void deletePostMedia(FileVO file);

  // 프로필 이미지 삭제
  public void deleteProfileMedia(FileVO file);

  // 이미지 1건 정보 조회
  public MediaVO selectMedia(int mediaNo);

}
