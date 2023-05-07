package io.github.it346.log.feign;

import io.github.it346.launch.constant.AppConstant;
import io.github.it346.log.model.LogApi;
import io.github.it346.log.model.LogError;
import io.github.it346.log.model.LogUsual;
import io.github.it346.tool.api.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Feign接口类
 *
 * @author wg
 */
@FeignClient(
	value = AppConstant.APPLICATION_LOG_NAME,
	fallback = LogClientFallback.class
)
public interface ILogClient {

	String API_PREFIX = "/log";

	/**
	 * 保存错误日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveUsualLog")
	R<Boolean> saveUsualLog(@RequestBody LogUsual log);

	/**
	 * 保存操作日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveApiLog")
	R<Boolean> saveApiLog(@RequestBody LogApi log);

	/**
	 * 保存错误日志
	 *
	 * @param log 日志实体
	 * @return boolean
	 */
	@PostMapping(API_PREFIX + "/saveErrorLog")
	R<Boolean> saveErrorLog(@RequestBody LogError log);

}
