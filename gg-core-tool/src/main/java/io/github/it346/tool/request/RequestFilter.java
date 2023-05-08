package io.github.it346.tool.request;

import lombok.AllArgsConstructor;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Request全局过滤
 *
 * @author wg
 */
@AllArgsConstructor
public class RequestFilter implements Filter {

	private final RequestProperties requestProperties;
	private final XssProperties xssProperties;
	private final AntPathMatcher antPathMatcher = new AntPathMatcher();

	@Override
	public void init(FilterConfig config) {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();
		// 跳过 Request 包装
		if (!requestProperties.getEnabled() || isRequestSkip(path)) {
			chain.doFilter(request, response);
		}
		// 默认 Request 包装
		else if (!xssProperties.getEnabled() || isXssSkip(path)) {
			HttpServletRequestWrapper httpServletRequestWrapper = new HttpServletRequestWrapper((HttpServletRequest) request);
			chain.doFilter(httpServletRequestWrapper, response);
		}
		// Xss Request 包装
		else {
			XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper((HttpServletRequest) request);
			chain.doFilter(xssRequest, response);
		}
	}

	private boolean isRequestSkip(String path) {
		return requestProperties.getSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
	}

	private boolean isXssSkip(String path) {
		return xssProperties.getSkipUrl().stream().anyMatch(pattern -> antPathMatcher.match(pattern, path));
	}

	@Override
	public void destroy() {

	}

}
