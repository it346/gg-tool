package io.github.it346.secure.provider;

/**
 * 多终端注册接口
 *
 * @author wg
 */
public interface IClientDetailsService {

	/**
	 * 根据clientId获取Client详情
	 *
	 * @param clientId 客户端id
	 * @return IClientDetails
	 */
	IClientDetails loadClientByClientId(String clientId);

}
