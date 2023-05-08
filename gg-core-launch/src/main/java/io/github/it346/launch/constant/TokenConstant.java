package io.github.it346.launch.constant;

/**
 * Token配置常量.
 *
 * @author wg
 */
public interface TokenConstant {

	String AVATAR = "avatar";
	String HEADER = "gg-auth";
	String BEARER = "bearer";
	String ACCESS_TOKEN = "access_token";
	String REFRESH_TOKEN = "refresh_token";
	String TOKEN_TYPE = "token_type";
	String EXPIRES_IN = "expires_in";
	String ACCOUNT = "account";
	String USER_ID = "user_id";
	String ROLE_ID = "role_id";
	String DEPT_ID = "dept_id";
	String USER_NAME = "user_name";
	String ROLE_NAME = "role_name";
	String TENANT_ID = "tenant_id";
	String OAUTH_ID = "oauth_id";
	String CLIENT_ID = "client_id";
	String LICENSE = "license";
	String LICENSE_NAME = "powered by wg";
	String DEFAULT_AVATAR = "https://gw.alipayobjects.com/zos/rmsportal/BiazfanxmamNRoxxVxka.png";
	Integer AUTH_LENGTH = 7;

	/**
	 * token签名
	 */
	String SIGN_KEY = "ggxisapowerfulmicroservicearchitectureupgradedandoptimizedfromacommercialproject";
	/**
	 * key安全长度，具体见：<a href="https://tools.ietf.org/html/rfc7518#section-3.2">...</a>
	 */
	int SIGN_KEY_LENGTH = 32;

}
