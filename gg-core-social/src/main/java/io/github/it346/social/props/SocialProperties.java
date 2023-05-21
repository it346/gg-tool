package io.github.it346.social.props;

import lombok.Getter;
import lombok.Setter;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.config.AuthDefaultSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wg
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "social")
public class SocialProperties {

	/**
	 * 启用
	 */
	private Boolean enabled = false;

	/**
	 * 域名地址
	 */
	private String domain;

	/**
	 * 类型
	 */
	private Map<AuthDefaultSource, AuthConfig> oauth = new HashMap<>();

	/**
	 * 别名
	 */
	private Map<String, String> alias = new HashMap<>();

}
