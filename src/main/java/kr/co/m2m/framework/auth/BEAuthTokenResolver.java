package kr.co.m2m.framework.auth;

public interface BEAuthTokenResolver {
	/**
	 * 인증 토큰 저장소 키 prefix
	 */
	public static final String AUTH_TOKEN_PREFIX = "m2m.auth.token:";
	/**
	 * 사용자 인증토큰 인덱스 저장소 키 prefix
	 */
	public static final String CLIENT_INDEX_PREFIX = "m2m.auth.client:";

	public default BEAuthDetailModel getAuthDetail(String authToken) {
		return this.getAuthDetail(authToken, false);
	}

	public BEAuthDetailModel getAuthDetail(String authToken, boolean extendTTL);
}
