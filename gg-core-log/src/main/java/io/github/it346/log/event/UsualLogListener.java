package io.github.it346.log.event;


import io.github.it346.launch.props.Properties;
import io.github.it346.launch.server.ServerInfo;
import io.github.it346.log.constant.EventConstant;
import io.github.it346.log.feign.ILogClient;
import io.github.it346.log.model.LogUsual;
import io.github.it346.log.utils.LogAbstractUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.Map;

/**
 * 异步监听日志事件
 *
 * @author wg
 */
@Slf4j
@AllArgsConstructor
public class UsualLogListener {

	private final ILogClient logService;
	private final ServerInfo serverInfo;
	private final Properties properties;

	@Async
	@Order
	@EventListener(UsualLogEvent.class)
	public void saveUsualLog(UsualLogEvent event) {
		Map<String, Object> source = (Map<String, Object>) event.getSource();
		LogUsual logUsual = (LogUsual) source.get(EventConstant.EVENT_LOG);
		LogAbstractUtil.addOtherInfoToLog(logUsual, properties, serverInfo);
		logService.saveUsualLog(logUsual);
	}

}
