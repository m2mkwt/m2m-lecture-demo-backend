package kr.co.m2m.instagram.comment.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode

public class CommentVO implements Serializable, Cloneable{
	private static final long serialVersionUID = -7175278235351293400L;

	private int commentNo;
	private int memberNo;
	private int postNo;
	private String text;
	private char deleteYN;
	private Date createdt;

}
