package kr.co.m2m.instagram.comment.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentPO {
	
	private int comment_id;
	@NotBlank
	private int member_id;
	@NotBlank
	private int post_id;
	@NotBlank
	private String text;
	private char delete_yn;
	private Date createdt;

}
