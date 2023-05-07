package io.github.it346.boot.props.tenant;

import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import io.github.it346.mybatis.config.MybatisPlusConfiguration;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * 多租户配置类
 *
 * @author wg
 */
@AutoConfiguration
@AllArgsConstructor
@AutoConfigureBefore(MybatisPlusConfiguration.class)
@EnableConfigurationProperties(TenantProperties.class)
public class TenantConfiguration {

	/**
	 * 自定义多租户处理器
	 *
	 * @param tenantProperties 多租户配置类
	 * @return TenantHandler
	 */
	@Bean
	@Primary
	public TenantLineHandler bladeTenantHandler(TenantProperties tenantProperties) {
		return new TenantHandler(tenantProperties);
	}

	/**
	 * 自定义租户拦截器
	 *
	 * @param tenantHandler 多租户处理器
	 * @return TenantInterceptor
	 */
	@Bean
	@Primary
	public TenantLineInnerInterceptor tenantLineInnerInterceptor(TenantLineHandler tenantHandler) {
		TenantInterceptor tenantInterceptor = new TenantInterceptor();
		tenantInterceptor.setTenantLineHandler(tenantHandler);
		return tenantInterceptor;
	}

	/**
	 * 自定义租户id生成器
	 *
	 * @return TenantId
	 */
	@Bean
	@ConditionalOnMissingBean(TenantId.class)
	public TenantId tenantId() {
		return new GgTenantId();
	}

}
