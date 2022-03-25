package kr.co.m2m.instagram.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MediaVO {

	public MediaVO() {
		super();
	}
	public MediaVO(int media_id, int post_id, String filename, char delete_yn, Date createdt) {
		super();
		this.media_id = media_id;
		this.post_id = post_id;
		this.filename = filename;
		this.delete_yn = delete_yn;
		this.createdt = createdt;
	}
	
	private int media_id;
	@NotBlank
	private int post_id;
	@NotBlank
	private String filename;
	private char delete_yn;
	private Date createdt;
	
}
