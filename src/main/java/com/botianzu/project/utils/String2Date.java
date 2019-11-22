package com.botianzu.project.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期转换
 * @author 刘彦昌
 *
 */
public class String2Date {

	
	private static final Logger log = LoggerFactory.getLogger(String2Date.class);

	public static Date transition(String str) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return simpleDateFormat.parse(str);
		} catch (ParseException e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}
}
