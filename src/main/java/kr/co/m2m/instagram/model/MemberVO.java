package kr.co.m2m.instagram.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberVO {
	
	public MemberVO() {
		super();
	}
	public MemberVO(int member_id, String login_name, String user_name, String email,
			String password, char gender, char delete_yn, int media_id) {
		super();
		this.member_id = member_id;
		this.login_name = login_name;
		this.user_name = user_name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.delete_yn = delete_yn;
		this.media_id = media_id;
	}
	
	private int member_id;
	@NotBlank
	private String login_name;
	@NotBlank
	private String user_name;
	@NotBlank
	private String email;
	@NotBlank
	private String password;
	private char gender;
	private char delete_yn;
	private int media_id;
	
}
