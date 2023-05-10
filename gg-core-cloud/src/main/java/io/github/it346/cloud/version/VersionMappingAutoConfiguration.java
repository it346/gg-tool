
package io.github.it346.cloud.version;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * url版本号处理
 *
 * 参考：<a href="https://gitee.com/lianqu1990/spring-boot-starter-version-mapping">...</a>
 *
 * @author wg
 */
@AutoConfiguration
@ConditionalOnWebApplication
public class VersionMappingAutoConfiguration {
	@Bean
	public org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations webMvcRegistrations() {
		return new WebMvcRegistrations();
	}
}
