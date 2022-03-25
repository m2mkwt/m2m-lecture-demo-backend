package kr.co.m2m.framework.web.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import kr.co.m2m.framework.web.common.CommonConstants;


public class BaseSearchVO<T> implements Serializable, Cloneable {
    /** 페이징 정렬 컬럼 */
    @Pattern(regexp = "(^[a-zA-Z]+[_a-zA-Z0-9]*)?")
    private String sidx = "";

    /** 페이징 정렬 방향 */
    @Pattern(regexp = "(ASC|DESC)?")
    private String sord = "";

    /** 현재 페이지 */
    private int page = 1;

    /** 한페이지당 보여줄 리스트 수 */
    private Integer rows;

    /** 총 카운트 */
    private int totalCount = 0;

    /** 총 페이지 카운트 */
    private int totalPageCount = 0;

    private int limit = 0;

    private int offset = 0;

    private int startIndex = 0;

    private int endIndex = 0;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Integer getRows() {
        if (null != rows) {
            return rows;
        } else {
            return CommonConstants.RECORD_COUNT_PAGE;
        }
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageCount() {
        this.totalPageCount = ((getTotalCount() - 1) / getRows() + 1);
        return this.totalPageCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public void setSort(String sort) {
        String[] temp = sort.split(" ");

        if(temp.length == 2) {
            this.sidx = temp[0];
            this.sord = temp[1];
        } else {
            this.sidx = temp[0];
            this.sord = "ASC";
        }
    }

    public String getSort() {
        if(sord.trim().length() > 1) {
            return sidx + " " + sord;
        } else {
            return sidx;
        }
    }


    @Override
    protected BaseSearchVO<T> clone() throws CloneNotSupportedException {
        return (BaseSearchVO<T>) super.clone();
    }
}
