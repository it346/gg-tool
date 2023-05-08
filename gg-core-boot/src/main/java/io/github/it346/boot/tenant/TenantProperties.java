package io.github.it346.boot.tenant;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 多租户配置
 *
 * @author wg
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "gg.tenant")
public class TenantProperties {

	/**
	 * 多租户字段名称
	 */
	private String column = "tenant_id";

	/**
	 * 多租户数据表
	 */
	private List<String> tables = new ArrayList<>();

	/**
	 * 多租户系统数据表
	 */
	private List<String> GgTables = Arrays.asList("gg_notice", "gg_post", "gg_log_api", "gg_log_error", "gg_log_usual");
}
