package io.github.it346.tool.request;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Request配置类
 *
 * @author wg
 */
@Data
@ConfigurationProperties("blade.request")
public class RequestProperties {

	/**
	 * 开启自定义request
	 */
	private Boolean enabled = true;

	/**
	 * 放行url
	 */
	private List<String> skipUrl = new ArrayList<>();

}
