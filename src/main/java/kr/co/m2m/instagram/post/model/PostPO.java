package kr.co.m2m.instagram.post.model;


import java.util.Date;

import javax.validation.constraints.NotEmpty;

import kr.co.m2m.framework.annotation.ValidDelete;
import kr.co.m2m.framework.annotation.ValidInsert;
import kr.co.m2m.framework.annotation.ValidUpdate;
import kr.co.m2m.framework.web.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class PostPO  {
	
	@NotEmpty(groups = {ValidDelete.class },message = "{member.postNo.empty}")
	private int postNo;
	@NotEmpty(groups = {ValidDelete.class },message = "{member.memberNo.empty}" )
	private int memberNo;
	private int mediaNo;
	@NotEmpty(groups = { ValidInsert.class, ValidUpdate.class },message = "{member.text.empty}")
	private String text;
	private char deleteYn;
	private Date createdt;
}
