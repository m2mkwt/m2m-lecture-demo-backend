package kr.co.m2m.instagram.media.model;

import java.util.Date;
import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명	: kr.co.m2m.instagram.media.model
 * 파일명		: MediaVO.java
 * 작성일		: 2022-04-07
 * 작성자		: "cshwang"
 * 설명		: 이미지 정보 저장을 위한 객체
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"cshwang" - 최초생성
 * </pre>
 */
@Data
public class MediaVO {

  // 이미지 번호
  @NotBlank
  private int mediaNo;

  // 파일명 (파일 존재하는 경로 포함)
  @NotBlank
  private String filename;

  // 삭제 여부
  private char deleteYn;

  // 생성 일자 (추가 일자)
  private Date createdt;

}
