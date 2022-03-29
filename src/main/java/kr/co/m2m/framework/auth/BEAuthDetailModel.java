package kr.co.m2m.framework.auth;

import java.io.Serializable;

import lombok.Data;

@Data
public class BEAuthDetailModel implements Serializable {

	private static final long serialVersionUID = 2320264180497147702L;

	private Object authToken;
	private int memberId;
	private String loginName;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private String deleteYn;
	private int mediaId;
	private long issueTime;
	private int extendTtl = 1000000;
}
