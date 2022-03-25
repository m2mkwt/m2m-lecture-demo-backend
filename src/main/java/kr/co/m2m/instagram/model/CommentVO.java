package kr.co.m2m.instagram.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentVO {

	public CommentVO() {
		super();
	}
	public CommentVO(int comment_id, int member_id, int post_id, String text, char delete_yn, Date createdt) {
		super();
		this.comment_id = comment_id;
		this.member_id = member_id;
		this.post_id = post_id;
		this.text = text;
		this.delete_yn = delete_yn;
		this.createdt = createdt;
	}
	
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
