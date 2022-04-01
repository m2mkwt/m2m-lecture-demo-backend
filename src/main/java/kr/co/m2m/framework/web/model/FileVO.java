package kr.co.m2m.framework.web.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class FileVO implements Serializable {

	private static final long serialVersionUID = -7096997790164465320L;

	/** 파일 이름 */
	private String fileName;

	/** 파일 이름 */
	private String fileOrgName;

	/** 파일 경로 */
	private String filePath;

	/** 파일 URL */
	private String fileUrl;

	/** 파일 크기 */
	private Long fileSize;

	/** 파일 타입 */
	private String fileType;

	/** 파일 확장자 */
	private String fileExtension;

	/** 파일 가로 크기 */
	private String fileWidth;

	/** 파일 세로 크기 */
	private String fileHeight;

	/** 이미지 여부 */
	private String imgYn;

	/** 임시파일 여부 */
	private String tempYn;

}