package kr.co.m2m.instagram.common.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginVO implements Serializable, Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6372438851298250149L;
	
	private int memberNo;
	private String loginId;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private int mediaNo;
}
