package kr.co.m2m.instagram.comment.model;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import kr.co.m2m.framework.annotation.ValidInsert;
import kr.co.m2m.framework.annotation.ValidUpdate;
import kr.co.m2m.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CommentSO extends BaseModel<CommentSO>{
	private int commentId;
	private int memberId;
	private int postId;
	private String text;
	private char deleteYN;
	private Date createdt;

}
