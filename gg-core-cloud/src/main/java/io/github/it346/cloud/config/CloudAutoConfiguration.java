package io.github.it346.cloud.config;

import com.alibaba.cloud.sentinel.feign.SentinelFeignAutoConfiguration;
import feign.Feign;
import feign.RequestInterceptor;
import io.github.it346.cloud.feign.FeignSentinel;
import io.github.it346.cloud.header.FeignRequestHeaderInterceptor;
import io.github.it346.cloud.sentinel.BlockExceptionHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;


/**
 * cloud 增强配置
 *
 * @author wg
 */
@AutoConfiguration
@Import(SentinelFilterConfiguration.class)
@AutoConfigureBefore(SentinelFeignAutoConfiguration.class)
public class CloudAutoConfiguration {

	@Bean
	@Scope("prototype")
	@ConditionalOnMissingBean
	@ConditionalOnProperty(name = "feign.sentinel.enabled")
	public Feign.Builder feignSentinelBuilder(RequestInterceptor requestInterceptor) {
		return FeignSentinel.builder().requestInterceptor(requestInterceptor);
	}

	@Bean
	@ConditionalOnMissingBean
	public BlockExceptionHandler blockExceptionHandler() {
		return new BlockExceptionHandler();
	}

	@Bean
	@ConditionalOnMissingBean
	public RequestInterceptor requestInterceptor() {
		return new FeignRequestHeaderInterceptor();
	}

}
