/**
 * Copyright (c) 2018-2028, DreamLu 卢春梦 (qq596392912@gmail.com).
 * <p>
 * Licensed under the GNU LESSER GENERAL PUBLIC LICENSE 3.0;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.gnu.org/licenses/lgpl.html
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.it346.cloud.header;

import io.github.it346.cloud.props.FeignHeadersProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * HttpHeaders hystrix Callable
 *
 * @param <V> 泛型标记
 * @author wg
 */
public class HttpHeadersCallable<V> implements Callable<V> {
	private final Callable<V> delegate;
	@Nullable
	private HttpHeaders httpHeaders;

	public HttpHeadersCallable(Callable<V> delegate,
									@Nullable FeignAccountGetter accountGetter,
									FeignHeadersProperties properties) {
		this.delegate = delegate;
		this.httpHeaders = HttpHeadersContextHolder.toHeaders(accountGetter, properties);
	}

	@Override
	public V call() throws Exception {
		if (httpHeaders == null) {
			return delegate.call();
		}
		try {
			HttpHeadersContextHolder.set(httpHeaders);
			return delegate.call();
		} finally {
			HttpHeadersContextHolder.remove();
			httpHeaders.clear();
			httpHeaders = null;
		}
	}
}
