package kr.co.m2m.instagram.comment.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import kr.co.m2m.framework.annotation.ValidInsert;
import kr.co.m2m.framework.annotation.ValidUpdate;
import kr.co.m2m.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CommentPO extends BaseModel<CommentPO>{
	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class })
	private int commentNo;
	private int memberNo;
	private int postNo;
	private String contentComment;
	private char deleteYN;
	private Date createdt;
	private String loginId;

}
