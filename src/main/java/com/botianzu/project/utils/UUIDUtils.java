package com.botianzu.project.utils;

import java.util.UUID;

/**
 * 生成UUID编号
 * @author 刘彦昌
 *
 */
public class UUIDUtils {

	public static String createUUIDCode() {
		return UUID.randomUUID().toString().replace("-","");
	}
}
