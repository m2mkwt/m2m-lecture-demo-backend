package kr.co.m2m.instagram.member.model;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class MemberVO {
		
	private int memberNo;
	
	@NotBlank
	private String loginId;
	
	@NotBlank
	private String userName;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	private char gender;
	
	private char deleteYn;
	
	private int mediaNo;
	
}
