package io.github.it346.swagger;

import io.github.it346.launch.constant.AppConstant;
import io.github.it346.launch.service.LauncherService;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.Ordered;

import java.util.Properties;

/**
 * 初始化Swagger配置
 *
 * @author wg
 */
public class SwaggerLauncherServiceImpl implements LauncherService {
	@Override
	public void launcher(SpringApplicationBuilder builder, String appName, String profile) {
		Properties props = System.getProperties();
		if (profile.equals(AppConstant.PROD_CODE)) {
			props.setProperty("knife4j.production", "true");
		}
		props.setProperty("knife4j.enable", "true");
		props.setProperty("spring.mvc.pathmatch.matching-strategy", "ANT_PATH_MATCHER");
	}

	@Override
	public int getOrder() {
		return Ordered.LOWEST_PRECEDENCE;
	}
}
