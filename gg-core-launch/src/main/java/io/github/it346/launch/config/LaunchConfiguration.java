package io.github.it346.launch.config;

import io.github.it346.launch.props.Properties;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * 配置类
 *
 * @author wg
 */
@AutoConfiguration
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableConfigurationProperties({
	Properties.class
})
public class LaunchConfiguration {

}
