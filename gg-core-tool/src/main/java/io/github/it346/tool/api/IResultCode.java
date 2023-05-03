package io.github.it346.tool.api;

import java.io.Serializable;

/**
 * 业务代码接口
 *
 * @author wg
 */
public interface IResultCode extends Serializable {

	/**
	 * 消息
	 *
	 * @return String
	 */
	String getMessage();

	/**
	 * 状态码
	 *
	 * @return int
	 */
	int getCode();

}
