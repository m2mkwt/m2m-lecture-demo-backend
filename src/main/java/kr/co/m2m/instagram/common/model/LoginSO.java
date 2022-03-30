package kr.co.m2m.instagram.common.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginSO implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3045798968113195173L;
	
	private String id; /* 아이디 */
	private String password; /* 비밀번호 */
}
