package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class CommentVO implements Serializable, Cloneable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7175278235351293400L;
	public CommentVO() {
		super();
	}
	public CommentVO(int commentId, int memberId, int postId, String text, char deleteYN, Date createdt) {
		super();
		this.commentId = commentId;
		this.memberId = memberId;
		this.postId = postId;
		this.text = text;
		this.deleteYN = deleteYN;
		this.createdt = createdt;
	}
	
	private int commentId;
	@NotBlank
	private int memberId;
	@NotBlank
	private int postId;
	@NotBlank
	private String text;
	private char deleteYN;
	private Date createdt;

}
