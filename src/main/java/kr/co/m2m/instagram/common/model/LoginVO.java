package kr.co.m2m.instagram.common.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class LoginVO implements Serializable, Cloneable {
	private String id; /* 아이디 */
	private String pw; /* 비밀번호 */
	private String name; /* 이름 */
	private String email; /* 이메일 */
}
