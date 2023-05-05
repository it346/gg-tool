package io.github.it346.swagger;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import io.github.it346.launch.constant.TokenConstant;
import springfox.documentation.RequestHandler;
import springfox.documentation.service.ApiKey;

import java.util.List;
import java.util.function.Predicate;

/**
 * Swagger工具类
 *
 * @author wg
 */
public class SwaggerUtil {

	/**
	 * 获取包集合
	 *
	 * @param basePackages 多个包名集合
	 */
	public static Predicate<RequestHandler> basePackages(final List<String> basePackages) {
		return input -> declaringClass(input).transform(handlerPackage(basePackages)).or(true);
	}

	private static Function<Class<?>, Boolean> handlerPackage(final List<String> basePackages) {
		return input -> {
			// 循环判断匹配
			for (String strPackage : basePackages) {
				boolean isMatch = input.getPackage().getName().startsWith(strPackage);
				if (isMatch) {
					return true;
				}
			}
			return false;
		};
	}

	private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
		return Optional.fromNullable(input.declaringClass());
	}


	public static ApiKey clientInfo() {
		return new ApiKey("ClientInfo", "Authorization", "header");
	}

	public static ApiKey auth() {
		return new ApiKey("Auth", TokenConstant.HEADER, "header");
	}

	public static ApiKey bladeTenant() {
		return new ApiKey("TenantId", "Tenant-Id", "header");
	}

}
