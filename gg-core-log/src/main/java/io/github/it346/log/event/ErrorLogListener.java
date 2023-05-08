package io.github.it346.log.event;


import io.github.it346.launch.props.Properties;
import io.github.it346.launch.server.ServerInfo;
import io.github.it346.log.constant.EventConstant;
import io.github.it346.log.feign.ILogClient;
import io.github.it346.log.model.LogError;
import io.github.it346.log.utils.LogAbstractUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * 异步监听错误日志事件
 *
 * @author wg
 */
@Slf4j
@AllArgsConstructor
public class ErrorLogListener {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final Properties properties;

	@Async
	@Order
	@EventListener(ErrorLogEvent.class)
	public void saveErrorLog(ErrorLogEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		LogError logError = (LogError) source.get(EventConstant.EVENT_LOG);
		LogAbstractUtil.addOtherInfoToLog(logError, properties, serverInfo);
		logService.saveErrorLog(logError);
	}

}
