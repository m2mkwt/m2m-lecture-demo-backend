package kr.co.m2m.framework.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.framework.annotation
 * 파일명		: ValidUpdate.java
 * 작성일		: 2022-04-08
 * 작성자		: wtkim
 * 설명		 	: Group Validation 사용시 수정검증시 사용되는 어노테이션
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	wtkim - 최초생성
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidUpdate {
}
