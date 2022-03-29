package kr.co.m2m.framework.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class RedisBEAuthManager {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> authDetailOper;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> tokenIndexOper;

	private int accessPer = 10;

	private long accessRate = 999;

	private String mainApiSecretKey = "secretKey";

	@Autowired
	private ObjectMapper omap;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private String createAuthToken(String authToken, String secritKey) {
		Map<Object, Object> sessionObject;
		Map<Object, Object> accessRights;

		HttpHeaders headers;
		HttpEntity<?> request;
		RestTemplate restClient;
		ResponseEntity<List> apiListRes;
		ResponseEntity<Map> keyRes;

		headers = new HttpHeaders();
		headers.set("x-tyk-authorization", secritKey);

		restClient = new RestTemplate();

		sessionObject = new HashMap<>();
		sessionObject.put("rate", this.accessRate);
		sessionObject.put("per", this.accessPer);
		sessionObject.put("expires", System.currentTimeMillis() + (1000 * 60 * 60 * 24));
		sessionObject.put("is_inactive", false);
		accessRights = new HashMap<>();
		request = new HttpEntity<>(headers);
//		apiListRes = restClient.exchange("/apis", HttpMethod.GET, request, List.class);
//		apiListRes.getBody().stream().forEach(i -> {
//			Map appInfo;
//			Map<String, Object> accessRight;
//
//			appInfo = (Map) i;
//			accessRight = new HashMap<>();
//			accessRight.put("api_name", appInfo.get("name"));
//			accessRight.put("api_id", appInfo.get("api_id"));
//			accessRight.put("versions", new String[] { "Default" });
//			accessRight.put("allowed_urls", new String[0]);
//			accessRights.put(appInfo.get("api_id"), accessRight);
//		});
//		if (!accessRights.isEmpty()) {
//			sessionObject.put("access_rights", accessRights);
//		}
		headers.setContentType(MediaType.APPLICATION_JSON);
		request = new HttpEntity<>(sessionObject, headers);
		if (authToken != null) {
			keyRes = restClient.exchange("/keys/" + authToken, HttpMethod.POST, request, Map.class);
		} else {
			keyRes = restClient.exchange("/keys/create", HttpMethod.POST, request, Map.class);
		}
		return (String) keyRes.getBody().get("key");
	}

	private void deleteAuthToken(String authToken, String secritKey) {
		HttpHeaders headers;
		HttpEntity<?> request;
		RestTemplate restClient;

		headers = new HttpHeaders();
		headers.set("x-tyk-authorization", this.mainApiSecretKey);

		restClient = new RestTemplate();
		request = new HttpEntity<>(headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		restClient.exchange("/keys/" + authToken, HttpMethod.DELETE, request, Map.class);
	}

	public void expireAuth(String token) {
		int id;
		BEAuthDetailModel auth;
		String keyByIndex = null;
		String keyByToken;

		this.expireAuthToken(token);
		keyByToken = (BEAuthTokenResolver.AUTH_TOKEN_PREFIX + token).toLowerCase();
		try {
			String authDetailStr;

			authDetailStr = this.authDetailOper.get(keyByToken);
			auth = authDetailStr == null ? null : this.omap.readValue(authDetailStr, BEAuthDetailModel.class);
		} catch (IOException e) {
			throw new RuntimeException("bo auth detail deserialize fail.", e);
		}
		if (auth == null) {
			return;
		}
		this.authDetailOper.getOperations().delete(keyByToken);
		id = auth.getMemberId();

		String hexId = this.toHexStr(String.valueOf(id));
		keyByIndex = (BEAuthTokenResolver.CLIENT_INDEX_PREFIX + ":" + hexId).toLowerCase();

		this.authDetailOper.getOperations().delete(keyByIndex);
	}

	protected void expireAuthToken(String token) {
		log.info("delete auth token {} from {}", token);
		this.deleteAuthToken(token, this.mainApiSecretKey);
	}

	public BEAuthDetailModel getAuthById(String id) {

		String keyByIndex = null;

		String hexId = this.toHexStr(id);

		keyByIndex = (BEAuthTokenResolver.CLIENT_INDEX_PREFIX + ":" + hexId).toLowerCase();

		if (StringUtils.isEmpty(keyByIndex)) {
			return null;
		}

		String token = this.tokenIndexOper.get(keyByIndex);
		if (StringUtils.isEmpty(token)) {
			return null;
		}

		String keyByToken = (BEAuthTokenResolver.AUTH_TOKEN_PREFIX + token).toLowerCase();
		try {
			String authDetailStr;

			authDetailStr = this.authDetailOper.get(keyByToken);
			return authDetailStr == null ? null : this.omap.readValue(authDetailStr, BEAuthDetailModel.class);
		} catch (IOException e) {
			throw new RuntimeException("bo auth detail deserialize fail.", e);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected String issueAuthToken(String id, int expendTtl) {
		String rv;
		rv = this.createAuthToken(null, this.mainApiSecretKey);
		return rv;
	}

	public void registAuth(BEAuthDetailModel auth) {
		int id;
		int expendTtl;
		String token;
		String keyByIndex = null;
		String keyByToken;
		String existedToken;

		id = auth.getMemberId();
		expendTtl = auth.getExtendTtl();
		token = (String) auth.getAuthToken();
		auth.setIssueTime(System.currentTimeMillis());

		String hexId = this.toHexStr(String.valueOf(id));
		keyByIndex = (BEAuthTokenResolver.CLIENT_INDEX_PREFIX + ":" + hexId).toLowerCase();
		keyByToken = (BEAuthTokenResolver.AUTH_TOKEN_PREFIX + token).toLowerCase();
		existedToken = null;
		try {
			existedToken = this.tokenIndexOper.get(keyByIndex);
		} catch (Exception ignore) {
		} // 기존 토큰정보 조회 실패 무시
		try {

			log.info("this.authDetailOper : {}", this.authDetailOper);

			this.authDetailOper.set(keyByToken, this.omap.writeValueAsString(auth), expendTtl, TimeUnit.SECONDS);
		} catch (IOException e) {
			throw new RuntimeException("bo auth detail serialize fail", e);
		}
		this.tokenIndexOper.set(keyByIndex, token);
		if (existedToken != null) {
			this.authDetailOper.getOperations().delete((BEAuthTokenResolver.AUTH_TOKEN_PREFIX + existedToken).toLowerCase());
		}
	}

	private String toHexStr(String str) {
		byte[] byteArr = str.getBytes();

		StringBuffer result = new StringBuffer();

		for (int i = 0; i < byteArr.length; i++) {
			String strString = Integer.toString((byteArr[i] & 0xff) + 0x100, 16);
			String subStr = strString.substring(1);
			result.append(subStr);
		}

		return result.toString();
	}
}
