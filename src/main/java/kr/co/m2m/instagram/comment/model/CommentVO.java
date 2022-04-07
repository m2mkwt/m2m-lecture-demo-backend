package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 프로젝트명	: instagram
 * 패키지명		: kr.co.m2m.instagram.comment.model
 * 파일명		: CommentVO.java
 * 작성일		: 2022-04-07
 * 작성자		: "gwLee"
 * 설명		 	: 댓글 VO
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"gwLee" - 최초생성
 * </pre>
 */
@Data
@EqualsAndHashCode

public class CommentVO implements Serializable, Cloneable {

  private static final long serialVersionUID = -7175278235351293400L;

  private int commentNo;

  private int likeNo;

  private int memberNo;

  private int postNo;

  private String content;

  private String deleteYn;

  private Date createdt;

  private String loginId;

  private String filename;

}
