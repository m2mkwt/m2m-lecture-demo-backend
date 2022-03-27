package kr.co.m2m.instagram.media.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MediaVO {

	@NotBlank
	private int media_id;
	@NotBlank
	private int post_id;
	@NotBlank
	private String filename;
	private char delete_yn;
	private Date createdt;
	
}
