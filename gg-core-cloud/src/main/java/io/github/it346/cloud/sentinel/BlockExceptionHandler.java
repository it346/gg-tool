package io.github.it346.cloud.sentinel;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import io.github.it346.tool.api.R;
import io.github.it346.tool.utils.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sentinel统一限流策略
 *
 * @author wg
 */
public class BlockExceptionHandler implements com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
		// Return 429 (Too Many Requests) by default.
		response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.getWriter().print(JsonUtil.toJson(R.fail(e.getMessage())));
	}
}
