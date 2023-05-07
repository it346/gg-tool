package io.github.it346.log.props;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 异步配置
 *
 * @author wg
 */
@Getter
@Setter
@ConfigurationProperties(LogProperties.PREFIX)
public class LogProperties {
	/**
	 * 前缀
	 */
	public static final String PREFIX = "gg.log";

	/**
	 * 是否开启 api 日志
	 */
	private Boolean api = Boolean.TRUE;
	/**
	 * 是否开启 error 日志
	 */
	private Boolean error = Boolean.TRUE;
	/**
	 * 是否开启 usual 日志
	 */
	private Boolean usual = Boolean.TRUE;
}
