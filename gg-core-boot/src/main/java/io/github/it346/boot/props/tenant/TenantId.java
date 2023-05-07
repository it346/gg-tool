package io.github.it346.boot.props.tenant;

/**
 * 租户id生成器
 *
 * @author wg
 */
public interface TenantId {

	/**
	 * 生成自定义租户id
	 *
	 * @return string
	 */
	String generate();

}
