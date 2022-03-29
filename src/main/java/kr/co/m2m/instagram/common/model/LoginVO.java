package kr.co.m2m.instagram.common.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginVO implements Serializable, Cloneable {
	private int memberId;
	private String loginName;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private String deleteYn;
	private int mediaId;
}
