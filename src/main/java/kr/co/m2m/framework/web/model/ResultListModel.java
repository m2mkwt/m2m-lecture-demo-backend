package kr.co.m2m.framework.web.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.ToString;

@ToString
public class ResultListModel<E> implements Serializable {

	private static final long serialVersionUID = 8366448677164286774L;

	/** 요청 성공 여부 */
	private Boolean isSuccess;
	/** 결과 메시지 */
	private String message;
	/** 결과 메시지 코드 */
	private String messageCode;

	/** 현재 페이지 */
	private Integer page;
	/** 페이지 페이지당 출력할 데이터 수 */
	private Integer rows;
	/** 전제 데이터 수 */
	private Integer totalRows;
	/** 전체 페이지 수 */
	private Integer totalPages;
	/** 검색조건으로 검색된 데이터 수 */
	private Integer filterdRows;

	/** 검색된 데이터 목록 */
	private List resultList;

	/** 기타 추가 데이터 */
	private Map<String, Object> extraData;

	public ResultListModel() {
		this.isSuccess = true;
		this.message = null;
		this.messageCode = null;
		this.page = 1;
		this.rows = 0;
		this.totalPages = 1;
		this.totalRows = 0;
		this.filterdRows = 0;
		this.resultList = null;
		this.extraData = new HashMap<>();
	}

	public Boolean getSuccess() {
		return isSuccess;
	}

	public void setSuccess(Boolean success) {
		isSuccess = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Integer totalRows) {
		this.totalRows = totalRows;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getFilterdRows() {
		return filterdRows;
	}

	public void setFilterdRows(Integer filterdRows) {
		this.filterdRows = filterdRows;
	}

	public <E> List<E> getResultList() {
		return resultList;
	}

	public void setResultList(List<E> resultList) {
		this.resultList = resultList;
	}

	public Map<String, Object> getExtraData() {
		return extraData;
	}

	public void put(String key, Object value) {
		extraData.put(key, value);
	}

	public Object get(String key) {
		return extraData.get(key);
	}
}
