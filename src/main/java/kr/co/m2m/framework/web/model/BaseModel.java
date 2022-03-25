package kr.co.m2m.framework.web.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class BaseModel<T> implements Serializable {

	private static final long serialVersionUID = -5767508192561569865L;
	
	private Long regrNo;
    private String regrNm;
    private Date regDttm;

    private Long updrNo;
    private String updrNm;
    private Date updDttm;

    private Long delrNo;
    private String delrNm;
    private Date delDttm;
}
