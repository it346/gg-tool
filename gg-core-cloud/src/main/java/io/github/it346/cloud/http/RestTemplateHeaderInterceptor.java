
package io.github.it346.cloud.http;

import io.github.it346.cloud.header.FeignAccountGetter;
import io.github.it346.cloud.header.HttpHeadersContextHolder;
import io.github.it346.cloud.props.FeignHeadersProperties;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.Nullable;

import java.io.IOException;

/**
 * RestTemplateHeaderInterceptor 传递Request header
 *
 * @author wg
 */
@AllArgsConstructor
public class RestTemplateHeaderInterceptor implements ClientHttpRequestInterceptor {
	@Nullable
	private final FeignAccountGetter accountGetter;
	private final FeignHeadersProperties properties;

	@Override
	public ClientHttpResponse intercept(
		HttpRequest request, byte[] bytes,
		ClientHttpRequestExecution execution) throws IOException {
		HttpHeaders headers = HttpHeadersContextHolder.get();
		// 考虑2中情况 1. RestTemplate 不是用 hystrix 2. 使用 hystrix
		if (headers == null) {
			headers = HttpHeadersContextHolder.toHeaders(accountGetter, properties);
		}
		if (headers != null && !headers.isEmpty()) {
			HttpHeaders httpHeaders = request.getHeaders();
			headers.forEach((key, values) -> {
				values.forEach(value -> httpHeaders.add(key, value));
			});
		}
		return execution.execute(request, bytes);
	}
}
