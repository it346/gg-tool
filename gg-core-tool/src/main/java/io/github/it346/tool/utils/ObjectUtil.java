package io.github.it346.tool.utils;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * 对象工具类
 *
 * @author wg
 */
public class ObjectUtil extends ObjectUtils {

	/**
	 * 判断元素不为空
	 * @param obj object
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectUtil.isEmpty(obj);
	}

}
