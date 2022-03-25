package kr.co.m2m.framework.auth;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class BEAuthDetailModel implements Serializable {

	private static final long serialVersionUID = 2320264180497147702L;

	private Object authToken;
	private String id; /* 아이디 */
	private String name; /* 이름 */
	private String dept; /* 부서코드 */
	private String jikgub; /* 직급코드 */
	private String sDate; /* 입사일 */
	private String chiefYn; /* 부서장여부 */
	private String retire; /* 퇴직여부 */
	private String retireDt; /* 퇴직일자 */
	private String email; /* 이메일 */
	private String deptNm; /* 부서명 */
	private String jikgubNm; /* 직급명 */
	private long issueTime;
	private int extendTtl = 1000000;

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getJikgub() {
		return jikgub;
	}

	public void setJikgub(String jikgub) {
		this.jikgub = jikgub;
	}

	public String getsDate() {
		return sDate;
	}

	public void setsDate(String sDate) {
		this.sDate = sDate;
	}

	public String getChiefYn() {
		return chiefYn;
	}

	public void setChiefYn(String chiefYn) {
		this.chiefYn = chiefYn;
	}

	public String getRetire() {
		return retire;
	}

	public void setRetire(String retire) {
		this.retire = retire;
	}

	public String getRetireDt() {
		return retireDt;
	}

	public void setRetireDt(String retireDt) {
		this.retireDt = retireDt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getJikgubNm() {
		return jikgubNm;
	}

	public void setJikgubNm(String jikgubNm) {
		this.jikgubNm = jikgubNm;
	}

	public int getExtendTtl() {
		return extendTtl;
	}

	public void setExtendTtl(int extendTerm) {
		if (this.extendTtl > 0) {
//			throw new IllegalStateException("extend ttl asigned already. can't change it!");
		}
		this.extendTtl = extendTerm;
	}

	/**
	 * @return the authToken
	 */
	public Object getAuthToken() {
		return authToken;
	}

	/**
	 * @param authToken the authToken to set
	 */
	public void setAuthToken(Object authToken) {
		this.authToken = authToken;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the issueTime
	 */
	public long getIssueTime() {
		return issueTime;
	}

	/**
	 * @param issueTime the issueTime to set
	 */
	public void setIssueTime(long issueTime) {
		this.issueTime = issueTime;
	}
}
