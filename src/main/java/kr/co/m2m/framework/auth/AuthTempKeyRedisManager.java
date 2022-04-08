package kr.co.m2m.framework.auth;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.m2m.framework.util.KeyGenUtility;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.framework.auth
 * 파일명		: AuthTempKeyRedisManager.java
 * 작성일		: 2022-04-08
 * 작성자		: wtkim
 * 설명		 	: Redis를 사용한 Client 검증용 Token키 관리 
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	wtkim - 최초생성
 * </pre>
 */
@Slf4j
@Component
public class AuthTempKeyRedisManager {

  /** 임시 인증키 redis prefix */
  static final String AUTH_TEMPKEY_PREFIX = "m2m.common:auth:temp.key:";

  /** 임시 인증키 TTL */
  static final int AUTH_TEMPKEY_TTL = 60 * 30;

  @Resource(name = "redisTemplate")
  private ValueOperations<String, String> tempKeyOper;

  @Autowired
  private ObjectMapper omap;

  @Autowired
  private KeyGenUtility keyGen;

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 임시키를 생성하여 해당키를 기준으로 redis 세션을 저장한다. 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param id
   * @return
   */
  public String createTempkey(String id) {

    if (StringUtils.isBlank(id)) {
      throw new RuntimeException("parameter is required.");
    }

    TempKeyAuthModel tempKeyAuthInfo = new TempKeyAuthModel();
    tempKeyAuthInfo.setId(id);
    log.info("tempKeyAuthInfo : {}", tempKeyAuthInfo);

    String tempKey = this.makeTempkey(0).toLowerCase();
    log.info("tempKey : {}", tempKey);

    this.saveTempKeyAuthInfoToRedis(tempKeyAuthInfo, tempKey);

    return tempKey;
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 토큰 생성
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param cnt
   * @return
   */
  private String makeTempkey(int cnt) {

    if (cnt > 10) {
      throw new RuntimeException("failed to create temp key!!");
    }

    String tempKey = keyGen.getNewKey("auth/tempKey");
    String existedTempkey = null;
    try {
      existedTempkey = tempKeyOper.get((AUTH_TEMPKEY_PREFIX + tempKey).toLowerCase());
    } catch (Exception ignore) {
    }

    if (existedTempkey == null) {
      return tempKey;
    } else {
      // 중복키의 경우 재 생성
      return this.makeTempkey(cnt + 1);
    }
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKey
   * @param athnNo
   */
  public void setAthnNoToRedis(String tempKey, String athnNo) {

    if (StringUtils.isBlank(tempKey) || StringUtils.isBlank(athnNo)) {
      throw new RuntimeException("parameter is required.");
    }

    TempKeyAuthModel tempKeyAuthInfo = this.getTempKeyAuthModelFromRedis(tempKey);
    tempKeyAuthInfo.setAhtnNo(athnNo);

    this.saveTempKeyAuthInfoToRedis(tempKeyAuthInfo, tempKey);
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKey
   * @param athnNo
   * @param id
   * @return
   */
  public boolean validationAthnNo(String tempKey, String athnNo, String id) {

    if (StringUtils.isBlank(tempKey) || StringUtils.isBlank(athnNo) || StringUtils.isBlank(id)) {
      return false;
    }

    TempKeyAuthModel tempKeyAuthInfo = this.getTempKeyAuthModelFromRedis(tempKey);

    if (athnNo.equals(tempKeyAuthInfo.getAhtnNo()) == false
        || id.equals(tempKeyAuthInfo.getId()) == false) {
      return false;
    }

    return true;
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKey
   * @return
   */
  public String getUserIdByTempKey(String tempKey) {

    if (StringUtils.isBlank(tempKey)) {
      throw new RuntimeException("parameter is required.");
    }

    TempKeyAuthModel tempKeyAuthInfo = this.getTempKeyAuthModelFromRedis(tempKey);

    return tempKeyAuthInfo.getId();
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKeyAuthInfo
   * @param tempKey
   */
  private void saveTempKeyAuthInfoToRedis(TempKeyAuthModel tempKeyAuthInfo, String tempKey) {
    try {
      log.info("saveTempKeyAuthInfoToRedis - tempKeyAuthInfo {}, tempKey : {}", tempKeyAuthInfo,
          tempKey);
      tempKeyOper.set((AUTH_TEMPKEY_PREFIX + tempKey).toLowerCase(),
          this.omap.writeValueAsString(tempKeyAuthInfo), AUTH_TEMPKEY_TTL, TimeUnit.SECONDS);

      log.info("saveTempKeyAuthInfoToRedis complete!!! : {}", tempKey);
    } catch (JsonProcessingException e) {
      throw new RuntimeException("tempKeyAuthInfo serialize fail", e);
    }
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKey
   * @return
   */
  private TempKeyAuthModel getTempKeyAuthModelFromRedis(String tempKey) {

    String tempKeyAuthStr = null;

    try {
      tempKeyAuthStr = tempKeyOper.get((AUTH_TEMPKEY_PREFIX + tempKey).toLowerCase());
    } catch (Exception ignore) {
    }

    if (tempKeyAuthStr == null) {
      throw new RuntimeException("non-existent tempKey");
    }

    TempKeyAuthModel tempKeyAuthInfo;

    try {
      tempKeyAuthInfo = this.omap.readValue(tempKeyAuthStr, TempKeyAuthModel.class);
    } catch (IOException e) {
      throw new RuntimeException("tempKeyAuthInfo deserialize fail.", e);
    }

    return tempKeyAuthInfo;
  }

  /**
   * <pre>
   * 작성일 : 2022-04-08
   * 작성자 : wtkim
   * 설명   : 
   *
   * 수정내역(수정일 수정자 - 수정내용)
   * -------------------------------------------------------------------------
   * 2022-04-08. wtkim - 최초생성
   * </pre>
   *
   * @param tempKey
   */
  public void expireTempKey(String tempKey) {

    if (StringUtils.isBlank(tempKey)) {
      throw new RuntimeException("parameter is required.");
    }

    String tempKeyAuthStr = null;

    try {
      tempKeyAuthStr = tempKeyOper.get((AUTH_TEMPKEY_PREFIX + tempKey).toLowerCase());

      if (tempKeyAuthStr == null) {
        return;
      }

    } catch (Exception ignore) {
    }

    tempKeyOper.getOperations().delete((AUTH_TEMPKEY_PREFIX + tempKey).toLowerCase());
  }
}


/**
 * <pre>
 * 프로젝트명	: m2m-lecture-demo-backend
 * 패키지명		: kr.co.m2m.framework.auth
 * 파일명		: AuthTempKeyRedisManager.java
 * 작성일		: 2022-04-08
 * 작성자		: wtkim
 * 설명		 	: 임시토큰 Model
 *
 * 수정내역(수정일 수정자 - 수정내용)
 * -------------------------------------------------------------------------
 * 2022-04-08	wtkim - 최초생성
 * </pre>
 */
class TempKeyAuthModel implements Serializable {

  private static final long serialVersionUID = -3381216184917969767L;

  private String id;
  private String ahtnNo;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAhtnNo() {
    return ahtnNo;
  }

  public void setAhtnNo(String ahtnNo) {
    this.ahtnNo = ahtnNo;
  }

  @Override
  public String toString() {
    return "TempKeyAuthModel [id=" + id + ", ahtnNo=" + ahtnNo + "]";
  }
}
