package io.github.it346.secure.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * secure放行额外配置
 *
 * @author wg
 */
@Data
@ConfigurationProperties("gg.secure")
public class SecureProperties {

	private final List<ClientSecure> client = new ArrayList<>();

	private final List<String> skipUrl = new ArrayList<>();

}
