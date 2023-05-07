package io.github.it346.secure.props;

import io.github.it346.launch.constant.TokenConstant;
import io.github.it346.tool.utils.StringPool;
import io.jsonwebtoken.JwtException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * secure放行额外配置
 *
 * @author wg
 */
@Slf4j
@Data
@ConfigurationProperties("gg.token")
public class TokenProperties {

	/**
	 * token签名
	 */
	private String signKey = StringPool.EMPTY;

	/**
	 * 获取签名规则
	 */
	public String getSignKey() {
		if (this.signKey.length() < TokenConstant.SIGN_KEY_LENGTH) {
			throw new JwtException("请配置 gg.token.sign-key 的值, 长度32位以上");
		}
		return this.signKey;
	}

}
