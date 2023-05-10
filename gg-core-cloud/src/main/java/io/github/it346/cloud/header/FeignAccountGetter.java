
package io.github.it346.cloud.header;


import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;

/**
 * gg 用户信息获取器，用于请求头传递
 *
 * @author wg
 */
public interface FeignAccountGetter {

	/**
	 * 账号信息获取器
	 *
	 * @param request HttpServletRequest
	 * @return account 信息
	 */
	@Nullable
	String get(HttpServletRequest request);
}
