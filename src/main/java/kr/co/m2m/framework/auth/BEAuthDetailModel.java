package kr.co.m2m.framework.auth;

import java.io.Serializable;

import lombok.Data;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.framework.auth
 * 파일명		: BEAuthDetailModel.java
 * 작성일		: 2022-04-08
 * 작성자		: wtkim
 * 설명		 	: Spring Security 사용자정보 저장 Detail Model
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	wtkim - 최초생성
 * </pre>
 */
@Data
public class BEAuthDetailModel implements Serializable {

	private static final long serialVersionUID = 2320264180497147702L;

	private Object authToken;
	private int memberNo;
	private String loginId;
	private String userName;
	private String email;
	private String password;
	private String gender;
	private String deleteYn;
	private int mediaNo;
	private long issueTime;
	private int extendTtl = 1000000;
}
