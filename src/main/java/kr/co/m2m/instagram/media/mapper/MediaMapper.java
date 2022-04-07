package kr.co.m2m.instagram.media.mapper;

import org.apache.ibatis.annotations.Mapper;
import kr.co.m2m.framework.web.model.FileVO;
import kr.co.m2m.instagram.media.model.MediaVO;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.media.mapper
 * 파일명		: MediaMapper.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 이미지 관련 DB 쿼리 수행을 위한 mapper
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
@Mapper
public interface MediaMapper {

  // 이미지 정보 DB에 추가
  public void insertMedia(MediaVO vo);

  // 프로필 이미지 정보 수정
  public void updateProfileMedia(FileVO vo);

  // 게시글 이미지 정보 수정
  public void updatePostMedia(FileVO vo);

  // 이미지 삭제 처리 (SET delete_yn = 'Y')
  public void deleteMedia(FileVO vo);

  // 이미지 1건 정보 조회
  public MediaVO selectMedia(int mediaNo);

}
