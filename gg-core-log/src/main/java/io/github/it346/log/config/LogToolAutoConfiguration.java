
package io.github.it346.log.config;

import io.github.it346.launch.props.Properties;
import io.github.it346.launch.server.ServerInfo;
import io.github.it346.log.aspect.ApiLogAspect;
import io.github.it346.log.event.ApiLogListener;
import io.github.it346.log.event.ErrorLogListener;
import io.github.it346.log.event.UsualLogListener;
import io.github.it346.log.feign.ILogClient;
import io.github.it346.log.logger.Logger;
import io.github.it346.log.props.LogProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;

/**
 * 日志工具自动配置
 *
 * @author wg
 */
@AutoConfiguration
@AllArgsConstructor
@ConditionalOnWebApplication
public class LogToolAutoConfiguration {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final Properties bladeProperties;

	@Bean
	@ConditionalOnProperty(value = LogProperties.PREFIX + "api.enabled", havingValue = "true", matchIfMissing = true)
	public ApiLogAspect apiLogAspect() {
		return new ApiLogAspect();
	}

	@Bean
	@ConditionalOnProperty(value = LogProperties.PREFIX + "api.enabled", havingValue = "true", matchIfMissing = true)
	public ApiLogListener apiLogListener() {
		return new ApiLogListener(logService, serverInfo, bladeProperties);
	}

	@Bean
	@ConditionalOnProperty(value = LogProperties.PREFIX + "error.enabled", havingValue = "true", matchIfMissing = true)
	public ErrorLogListener errorEventListener() {
		return new ErrorLogListener(logService, serverInfo, bladeProperties);
	}

	@Bean
	@ConditionalOnProperty(value = LogProperties.PREFIX + "usual.enabled", havingValue = "true", matchIfMissing = true)
	public UsualLogListener bladeEventListener() {
		return new UsualLogListener(logService, serverInfo, bladeProperties);
	}

	@Bean
	@ConditionalOnProperty(value = LogProperties.PREFIX + "usual.enabled", havingValue = "true", matchIfMissing = true)
	public Logger logger() {
		return new Logger();
	}

}
