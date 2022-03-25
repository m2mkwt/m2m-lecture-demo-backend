package kr.co.m2m.instagram.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostVO {
	
	public PostVO() {
		super();
	}
	
	public PostVO(int post_id, int member_id, int media_id, String text, char deleye_yn, Date createdt) {
		super();
		this.post_id = post_id;
		this.member_id = member_id;
		this.media_id = media_id;
		this.text = text;
		this.deleye_yn = deleye_yn;
		this.createdt = createdt;
	}

	private int post_id;
	@NotBlank
	private int member_id;
	@NotBlank
	private int media_id;
	@NotBlank
	private String text;
	private char deleye_yn;
	private Date createdt;
	
}
