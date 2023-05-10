
package io.github.it346.cloud.version;

import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * url版本号处理
 *
 * @author wg
 */
public class WebMvcRegistrations implements org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations {
	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return new RequestMappingHandlerMapping();
	}

	@Override
	public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
		return null;
	}

	@Override
	public ExceptionHandlerExceptionResolver getExceptionHandlerExceptionResolver() {
		return null;
	}
}
