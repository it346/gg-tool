package io.github.it346.secure;

import lombok.Data;

/**
 * TokenInfo
 *
 * @author wg
 */
@Data
public class TokenInfo {

	/**
	 * 令牌值
	 */
	private String token;

	/**
	 * 过期秒数
	 */
	private int expire;

}
