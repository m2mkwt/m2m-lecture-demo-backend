package kr.co.m2m.instagram.comment.model;

import java.util.Date;

import kr.co.m2m.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CommentSO extends BaseModel<CommentSO>{
	private int commentNo;
	private int memberNo;
	private int postNo;
	private String contentComment;
	private char deleteYN;
	private Date createdt;
	private String loginId;

}
