
package io.github.it346.log.event;

import org.springframework.context.ApplicationEvent;

import java.util.Map;

/**
 * 系统日志事件
 *
 * @author wg
 */
public class UsualLogEvent extends ApplicationEvent {

	public UsualLogEvent(Map<String, Object> source) {
		super(source);
	}

}
