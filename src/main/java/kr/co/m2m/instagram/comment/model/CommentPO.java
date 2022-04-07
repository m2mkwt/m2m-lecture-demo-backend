package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import kr.co.m2m.framework.annotation.ValidInsert;
import kr.co.m2m.framework.annotation.ValidUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <pre>
 * 프로젝트명	: instagram
 * 패키지명		: kr.co.m2m.instagram.comment.model
 * 파일명		: CommentPO.java
 * 작성일		: 2022-04-07
 * 작성자		: "gwLee"
 * 설명		 	: 댓글 PO
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-07	"gwLee" - 최초생성
 * </pre>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CommentPO implements Serializable, Cloneable {

  private static final long serialVersionUID = 8106620070909528580L;

  @NotEmpty(groups = {ValidInsert.class, ValidUpdate.class})
  private int commentNo;

  @NotEmpty(groups = {ValidInsert.class, ValidUpdate.class})
  private int memberNo;

  @NotEmpty(groups = {ValidInsert.class, ValidUpdate.class})
  private int postNo;

  private String content;

  private String deleteYn;

  private Date createdt;

  private String loginId;

  private int likeNo;
}
