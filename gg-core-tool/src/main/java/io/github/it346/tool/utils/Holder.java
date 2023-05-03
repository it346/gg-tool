package io.github.it346.tool.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 一些常用的单利对象
 *
 * @author wg
 */
public class Holder {

	/**
	 * RANDOM
	 */
	public final static Random RANDOM = new Random();

	/**
	 * SECURE_RANDOM
	 */
	public final static SecureRandom SECURE_RANDOM = new SecureRandom();
}
