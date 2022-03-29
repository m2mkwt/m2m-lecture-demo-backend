package kr.co.m2m.instagram.media.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MediaVO {

	@NotBlank
	private int mediaNo;
	@NotBlank
	private String filename;
	private char deleteYn;
	private Date createdt;
	
}
