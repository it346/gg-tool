
package io.github.it346.tool.jackson;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jackson 配置
 *
 * @author wg
 */
@Getter
@Setter
@ConfigurationProperties("gg.jackson")
public class JacksonProperties {

	/**
	 * 支持 MediaType text/plain，用于和 gg-api-crypto 一起使用
	 */
	private Boolean supportTextPlain = Boolean.FALSE;

}
