package io.github.it346.secure.interceptor;

import io.github.it346.secure.User;
import io.github.it346.secure.utils.SecureUtil;
import io.github.it346.tool.api.R;
import io.github.it346.tool.api.ResultCode;
import io.github.it346.tool.constant.Constant;
import io.github.it346.tool.utils.JsonUtil;
import io.github.it346.tool.utils.StringUtil;
import io.github.it346.tool.utils.WebUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.AsyncHandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 客户端校验
 *
 * @author wg
 */
@Slf4j
@AllArgsConstructor
public class ClientInterceptor implements AsyncHandlerInterceptor {

	private final String clientId;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		User user = SecureUtil.getUser();
		if (user != null && StringUtil.equals(clientId, SecureUtil.getClientIdFromHeader()) && StringUtil.equals(clientId, user.getClientId())) {
			return true;
		} else {
			log.warn("客户端认证失败，请求接口：{}，请求IP：{}，请求参数：{}", request.getRequestURI(), WebUtil.getIP(request), JsonUtil.toJson(request.getParameterMap()));
			R result = R.fail(ResultCode.UN_AUTHORIZED);
			response.setHeader(Constant.CONTENT_TYPE_NAME, MediaType.APPLICATION_JSON_VALUE);
			response.setCharacterEncoding(Constant.UTF_8);
			response.setStatus(HttpServletResponse.SC_OK);
			try {
				response.getWriter().write(Objects.requireNonNull(JsonUtil.toJson(result)));
			} catch (IOException ex) {
				log.error(ex.getMessage());
			}
			return false;
		}
	}

}
