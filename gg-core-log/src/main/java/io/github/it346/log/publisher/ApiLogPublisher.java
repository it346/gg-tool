package io.github.it346.log.publisher;

import io.github.it346.log.annotation.ApiLog;
import io.github.it346.log.constant.EventConstant;
import io.github.it346.log.event.ApiLogEvent;
import io.github.it346.log.model.LogApi;
import io.github.it346.log.utils.LogAbstractUtil;
import io.github.it346.tool.constant.Constant;
import io.github.it346.tool.utils.SpringUtil;
import io.github.it346.tool.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * API日志信息事件发送
 *
 * @author wg
 */
public class ApiLogPublisher {

	public static void publishEvent(String methodName, String methodClass, ApiLog apiLog, long time) {
		HttpServletRequest request = WebUtil.getRequest();
		LogApi logApi = new LogApi();
		logApi.setType(Constant.LOG_NORMAL_TYPE);
		logApi.setTitle(apiLog.value());
		logApi.setTime(String.valueOf(time));
		logApi.setMethodClass(methodClass);
		logApi.setMethodName(methodName);

		LogAbstractUtil.addRequestInfoToLog(request, logApi);
		Map<String, Object> event = new HashMap<>(16);
		event.put(EventConstant.EVENT_LOG, logApi);
		SpringUtil.publishEvent(new ApiLogEvent(event));
	}

}
