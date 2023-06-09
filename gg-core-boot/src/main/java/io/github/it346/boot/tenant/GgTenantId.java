package io.github.it346.boot.tenant;

import io.github.it346.tool.utils.RandomType;
import io.github.it346.tool.utils.StringUtil;

/**
 * 租户id生成器
 *
 * @author wg
 */
public class GgTenantId implements TenantId {
	@Override
	public String generate() {
		return StringUtil.random(6, RandomType.INT);
	}
}
