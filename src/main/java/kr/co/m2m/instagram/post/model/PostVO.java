package kr.co.m2m.instagram.post.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class PostVO implements Serializable, Cloneable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3246801037149015295L;
	
	public PostVO() {
	}

	

	private int postNo;
	@NotBlank
	private int memberNo;
	@NotBlank
	private int mediaNo;
	@NotBlank
	private String text;
	private char deleyeYn;
	private Date createdt;
	private int likes;
}
