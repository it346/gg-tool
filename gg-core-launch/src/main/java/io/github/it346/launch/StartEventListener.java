package io.github.it346.launch;

import io.github.it346.launch.utils.INetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StringUtils;

/**
 * 项目启动事件通知
 *
 * @author wg
 */
@Slf4j
@AutoConfiguration
public class StartEventListener {

	@Async
	@Order
	@EventListener(WebServerInitializedEvent.class)
	public void afterStart(WebServerInitializedEvent event) {
		Environment environment = event.getApplicationContext().getEnvironment();
		String appName = environment.getProperty("spring.application.name").toUpperCase();
		int localPort = event.getWebServer().getPort();
		String profile = StringUtils.arrayToCommaDelimitedString(environment.getActiveProfiles());
		log.info("---[{}]---启动完成，当前使用的端口:[{}]，环境变量:[{}]---", appName, localPort, profile);

		String ip = INetUtil.getHostIp();
		String port = environment.getProperty("server.port");
		String path = environment.getProperty("server.servlet.context-path");
		System.out.println("\n----------------------------------------------------------\n\t" +
			appName + " is running! Access URLs:\n\t" +
			"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
			"External: \thttp://" + ip + ":" + port + path + "/\n\t" +
			"Knife4j-ui: http://" + ip + ":" + port + path + "/doc.html\n" +
			"----------------------------------------------------------");
	}
}
