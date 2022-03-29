package kr.co.m2m.instagram.comment.model;

import java.util.Date;

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
