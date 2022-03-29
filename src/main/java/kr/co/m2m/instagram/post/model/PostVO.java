package kr.co.m2m.instagram.post.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostVO {
	
	public PostVO() {
		super();
	}

	public PostVO(int postId, @NotBlank int memberId, @NotBlank int mediaId, @NotBlank String text, char deleyeYn,
			Date createdt) {
		super();
		this.postId = postId;
		this.memberId = memberId;
		this.mediaId = mediaId;
		this.text = text;
		this.deleyeYn = deleyeYn;
		this.createdt = createdt;
	}

	private int postId;
	@NotBlank
	private int memberId;
	@NotBlank
	private int mediaId;
	@NotBlank
	private String text;
	private char deleyeYn;
	private Date createdt;
	
}
