package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;
import javax.validation.constraints.NotEmpty;
import kr.co.m2m.framework.annotation.ValidInsert;
import kr.co.m2m.framework.annotation.ValidUpdate;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
