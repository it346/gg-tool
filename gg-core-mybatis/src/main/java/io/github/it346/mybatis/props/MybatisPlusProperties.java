package io.github.it346.mybatis.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * MybatisPlus配置类
 *
 * @author wg
 */
@Data
@ConfigurationProperties(prefix = "gg.mybatis-plus")
public class MybatisPlusProperties {

	/**
	 * 分页最大数
	 */
	private Long pageLimit = 500L;

	/**
	 * 溢出总页数后是否进行处理
	 */
	protected Boolean overflow = false;

}
