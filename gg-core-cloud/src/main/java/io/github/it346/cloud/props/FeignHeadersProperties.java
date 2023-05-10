
package io.github.it346.cloud.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;

/**
 * Hystrix Headers 配置
 *
 * @author wg
 */
@Getter
@Setter
@RefreshScope
@ConfigurationProperties("gg.feign.headers")
public class FeignHeadersProperties {

	/**
	 * 用于 聚合层 向调用层传递用户信息 的请求头，默认：x-gg-account
	 */
	private String account = "X-Gg-Account";

	/**
	 * RestTemplate 和 Fegin 透传到下层的 Headers 名称表达式
	 */
	@Nullable
	private String pattern = "Gg*";

	/**
	 * RestTemplate 和 Fegin 透传到下层的 Headers 名称列表
	 */
	private List<String> allowed = Arrays.asList("X-Real-IP", "x-forwarded-for", "authorization", "gg-auth", "Authorization", "Gg-Auth");

}
