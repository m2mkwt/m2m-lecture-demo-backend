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
	private int comment_id;
	private int member_id;
	private int post_id;
	private String text;
	private char delete_yn;
	private Date createdt;

}
