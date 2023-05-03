package io.github.it346.tool.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.it346.tool.jackson.JacksonProperties;
import io.github.it346.tool.jackson.MappingApiJackson2HttpMessageConverter;
import io.github.it346.tool.utils.Charsets;
import lombok.AllArgsConstructor;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.AbstractJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 消息配置类
 *
 * @author wg
 */
@AutoConfiguration
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MessageConfiguration implements WebMvcConfigurer {

	private final ObjectMapper objectMapper;
	private final JacksonProperties properties;

	/**
	 * 使用 JACKSON 作为JSON MessageConverter
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.removeIf(x -> x instanceof StringHttpMessageConverter || x instanceof AbstractJackson2HttpMessageConverter);
		converters.add(new StringHttpMessageConverter(Charsets.UTF_8));
		converters.add(new ByteArrayHttpMessageConverter());
		converters.add(new ResourceHttpMessageConverter());
		converters.add(new ResourceRegionHttpMessageConverter());
		converters.add(new MappingApiJackson2HttpMessageConverter(objectMapper, properties));
	}

}
