package com.botianzu.project.utils;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 复制对象属性
 * @author 刘彦昌
 *
 * @param <K>
 * @param <M>
 */
public class PropertyCopyUtils {

	
	private static final Logger log = LoggerFactory.getLogger(PropertyCopyUtils.class);

	
	public static<K,M> void copy(K dest,M orig) {
		try {
			BeanUtils.copyProperties(dest, orig);
		} catch (IllegalAccessException | InvocationTargetException e) {
			
			log.error(e.getLocalizedMessage());
		}
	}
}
