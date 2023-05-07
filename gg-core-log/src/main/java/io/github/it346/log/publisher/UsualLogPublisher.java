
package io.github.it346.log.publisher;

import io.github.it346.log.constant.EventConstant;
import io.github.it346.log.event.UsualLogEvent;
import io.github.it346.log.model.LogUsual;
import io.github.it346.log.utils.LogAbstractUtil;
import io.github.it346.tool.utils.SpringUtil;
import io.github.it346.tool.utils.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * BLADE日志信息事件发送
 *
 * @author wg
 */
public class UsualLogPublisher {

	public static void publishEvent(String level, String id, String data) {
		HttpServletRequest request = WebUtil.getRequest();
		LogUsual logUsual = new LogUsual();
		logUsual.setLogLevel(level);
		logUsual.setLogId(id);
		logUsual.setLogData(data);

		LogAbstractUtil.addRequestInfoToLog(request, logUsual);
		Map<String, Object> event = new HashMap<>(16);
		event.put(EventConstant.EVENT_LOG, logUsual);
		event.put(EventConstant.EVENT_REQUEST, request);
		SpringUtil.publishEvent(new UsualLogEvent(event));
	}

}
