package kr.co.m2m.instagram.post.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PostVO implements Serializable, Cloneable {

  private static final long serialVersionUID = -3246801037149015295L;

  public PostVO() {}

  private int postNo;
  private int memberNo;
  private int mediaNo;
  private String content;
  private String deleyeYn;
  private Date createdt;

  private int likeCnt;
  private String loginId;
  private String pFilename;
  private String mFilename;
  private int commentCnt;
  private String likeFlag;
  private boolean likeStatus;  

  /** 페이징 정렬 컬럼 */
  @Pattern(regexp = "(^[a-zA-Z]+.[_a-zA-Z0-9]*)?")
  private String sidx = "";

  /** 페이징 정렬 방향 */
  @Pattern(regexp = "(ASC|DESC)?")
  private String sord = "";

  /** 현재 페이지 */
  private int page = 1;

  /** 한페이지당 보여줄 리스트 수 */
  private Integer rows;
  
  private int startIndex = 0;
  
  private int endIndex = 0;
}
