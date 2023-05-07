package io.github.it346.cloud.header;

import io.github.it346.secure.User;
import io.github.it346.secure.utils.SecureUtil;
import io.github.it346.tool.utils.Charsets;
import io.github.it346.tool.utils.UrlUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户信息获取器
 *
 * @author wg
 */
public class AccountGetter implements FeignAccountGetter {

	@Override
	public String get(HttpServletRequest request) {
		User account = SecureUtil.getUser();
		if (account == null) {
			return null;
		}
		// 增加用户头, 123[admin]
		String xAccount = String.format("%s[%s]", account.getUserId(), account.getUserName());
		return UrlUtil.encodeURL(xAccount, Charsets.UTF_8);
	}

}
