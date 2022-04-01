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
	private int memberNo;
	private int mediaNo;
	private String content;
	private String deleyeYn;
	private Date createdt;

	private int likeCnt;
	private String loginId;
	private String filename;
	private int commentCnt;
	

}
