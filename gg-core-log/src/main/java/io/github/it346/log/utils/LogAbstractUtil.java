
package io.github.it346.log.utils;

import io.github.it346.launch.props.Properties;
import io.github.it346.launch.server.ServerInfo;
import io.github.it346.log.model.LogAbstract;
import io.github.it346.secure.utils.SecureUtil;
import io.github.it346.tool.utils.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Log 相关工具
 *
 * @author wg
 */
public class LogAbstractUtil {

	/**
	 * 向log中添加补齐request的信息
	 *
	 * @param request     请求
	 * @param logAbstract 日志基础类
	 */
	public static void addRequestInfoToLog(HttpServletRequest request, LogAbstract logAbstract) {
		if (ObjectUtil.isNotEmpty(request)) {
			logAbstract.setRemoteIp(WebUtil.getIP(request));
			logAbstract.setUserAgent(request.getHeader(WebUtil.USER_AGENT_HEADER));
			logAbstract.setRequestUri(UrlUtil.getPath(request.getRequestURI()));
			logAbstract.setMethod(request.getMethod());
			logAbstract.setParams(WebUtil.getRequestParamString(request));
			logAbstract.setCreateBy(SecureUtil.getUserAccount(request));
		}
	}

	/**
	 * 向log中添加补齐其他的信息（eg：gg、server等）
	 *
	 * @param logAbstract     日志基础类
	 * @param properties 配置信息
	 * @param serverInfo      服务信息
	 */
	public static void addOtherInfoToLog(LogAbstract logAbstract, Properties properties, ServerInfo serverInfo) {
		logAbstract.setServiceId(properties.getName());
		logAbstract.setServerHost(serverInfo.getHostName());
		logAbstract.setServerIp(serverInfo.getIpWithPort());
		logAbstract.setEnv(properties.getEnv());
		logAbstract.setCreateTime(DateUtil.now());

		//这里判断一下params为null的情况，否则gg-log服务在解析该字段的时候，可能会报出NPE
		if (logAbstract.getParams() == null) {
			logAbstract.setParams(StringPool.EMPTY);
		}
	}
}
