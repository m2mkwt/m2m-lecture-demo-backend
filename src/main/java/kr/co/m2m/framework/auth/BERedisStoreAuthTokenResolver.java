package kr.co.m2m.framework.auth;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Primary
@Component
public class BERedisStoreAuthTokenResolver implements BEAuthTokenResolver {

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> authDetailOper;

	@Autowired
	private ObjectMapper omap;

	@Override
	public BEAuthDetailModel getAuthDetail(String authToken, boolean extendTTL) {
		log.info("authToken : {}", authToken);
		BEAuthDetailModel rv;

		String keyByToken;
		String authDetailStr;

		keyByToken = AUTH_TOKEN_PREFIX + authToken.toLowerCase();
		authDetailStr = this.authDetailOper.get(keyByToken);
		log.info("authDetailStr : {}", authDetailStr);

		try {
			rv = authDetailStr == null ? null : this.omap.readValue(authDetailStr, BEAuthDetailModel.class);
		} catch (IOException e) {
			log.error("IOException Occured... : {}", e);
			throw new RuntimeException("bo auth detail deserialize fail", e);
		}
		log.info("rv : {}", rv);

		if (rv != null && extendTTL) {
			this.authDetailOper.getOperations().expire(keyByToken, rv.getExtendTtl(), TimeUnit.SECONDS);
		}
		return rv;
	}
}