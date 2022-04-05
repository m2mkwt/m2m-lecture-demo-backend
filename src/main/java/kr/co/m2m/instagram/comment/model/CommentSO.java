package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;

import kr.co.m2m.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CommentSO implements Serializable, Cloneable {

  private static final long serialVersionUID = 5750683834821320536L;

  private int commentNo;
  
  private int likeNo;
  
  private int memberNo;
  
  private int postNo;
  
  private String content;
  
  private String deleteYn;
  
  private Date createdt;
  
  private String loginId;

}
