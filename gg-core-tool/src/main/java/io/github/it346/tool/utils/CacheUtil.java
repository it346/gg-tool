package io.github.it346.tool.utils;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.lang.Nullable;

import java.util.concurrent.Callable;

/**
 * 缓存工具类
 *
 * @author wg
 */
public class CacheUtil {

	public static final String SYS_CACHE = "gg:sys";

	private static CacheManager cacheManager;

	/**
	 * 获取缓存工具
	 *
	 * @return CacheManager
	 */
	private static CacheManager getCacheManager() {
		if (cacheManager == null) {
			cacheManager = SpringUtil.getBean(CacheManager.class);
		}
		return cacheManager;
	}

	/**
	 * 获取缓存对象
	 *
	 * @param cacheName 缓存名
	 * @return Cache
	 */
	public static Cache getCache(String cacheName) {
		return getCacheManager().getCache(cacheName);
	}

	/**
	 * 获取缓存
	 *
	 * @param cacheName 缓存名
	 * @param keyPrefix 缓存键前缀
	 * @param key       缓存键值
	 * @return Cache
	 */
	@Nullable
	public static Object get(String cacheName, String keyPrefix, Object key) {
		if (Func.hasEmpty(cacheName, keyPrefix, key)) {
			return null;
		}
		return getCache(cacheName).get(keyPrefix.concat(String.valueOf(key))).get();
	}

	/**
	 * 获取缓存
	 *
	 * @param cacheName 缓存名
	 * @param keyPrefix 缓存键前缀
	 * @param key       缓存键值
	 * @param type      转换类型
	 * @param <T>       类型
	 * @return Cache
	 */
	@Nullable
	public static <T> T get(String cacheName, String keyPrefix, Object key, @Nullable Class<T> type) {
		if (Func.hasEmpty(cacheName, keyPrefix, key)) {
			return null;
		}
		return getCache(cacheName).get(keyPrefix.concat(String.valueOf(key)), type);
	}

	/**
	 * 获取缓存
	 *
	 * @param cacheName   缓存名
	 * @param keyPrefix   缓存键前缀
	 * @param key         缓存键值
	 * @param valueLoader 重载对象
	 * @param <T>         类型
	 * @return Cache
	 */
	@Nullable
	public static <T> T get(String cacheName, String keyPrefix, Object key, Callable<T> valueLoader) {
		if (Func.hasEmpty(cacheName, keyPrefix, key)) {
			return null;
		}
		try {
			Cache.ValueWrapper valueWrapper = getCache(cacheName).get(keyPrefix.concat(String.valueOf(key)));
			Object value = null;
			if (valueWrapper == null) {
				T call = valueLoader.call();
				if (Func.isNotEmpty(call)) {
					getCache(cacheName).put(keyPrefix.concat(String.valueOf(key)), call);
					value = call;
				}
			} else {
				value = valueWrapper.get();
			}
			return (T) value;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * 设置缓存
	 *
	 * @param cacheName 缓存名
	 * @param keyPrefix 缓存键前缀
	 * @param key       缓存键值
	 * @param value     缓存值
	 */
	public static void put(String cacheName, String keyPrefix, Object key, @Nullable Object value) {
		getCache(cacheName).put(keyPrefix.concat(String.valueOf(key)), value);
	}

	/**
	 * 清除缓存
	 *
	 * @param cacheName 缓存名
	 * @param keyPrefix 缓存键前缀
	 * @param key       缓存键值
	 */
	public static void evict(String cacheName, String keyPrefix, Object key) {
		if (Func.hasEmpty(cacheName, keyPrefix, key)) {
			return;
		}
		getCache(cacheName).evict(keyPrefix.concat(String.valueOf(key)));
	}

	/**
	 * 清空缓存
	 *
	 * @param cacheName 缓存名
	 */
	public static void clear(String cacheName) {
		if (Func.isEmpty(cacheName)) {
			return;
		}
		getCache(cacheName).clear();
	}

}

