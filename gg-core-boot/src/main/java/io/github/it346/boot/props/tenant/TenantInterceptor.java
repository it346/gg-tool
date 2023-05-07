package io.github.it346.boot.props.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 租户拦截器
 *
 * @author wg
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TenantInterceptor extends TenantLineInnerInterceptor {

	/**
	 * 租户处理器
	 */
	private TenantLineHandler tenantLineHandler;

	@Override
	public void setTenantLineHandler(TenantLineHandler tenantLineHandler) {
		super.setTenantLineHandler(tenantLineHandler);
		this.tenantLineHandler = tenantLineHandler;
	}

}
