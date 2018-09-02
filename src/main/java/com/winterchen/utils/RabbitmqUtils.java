package com.winterchen.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class RabbitmqUtils {
	private static final Logger logger = LoggerFactory.getLogger(RabbitmqUtils.class);

	public static Map<String, Object> parseRabbitmqMsgToMap(String content) {
		if (StringUtils.isNotBlank(content)) {
			String[] split = content.split(",");
			byte[] listbyte = new byte[split.length];
			for (int i = 0; i < listbyte.length; i++) {
				listbyte[i] = new Byte(split[i]);
			}
			try {
				content = new String(listbyte, "UTF-8");
				return FastJsonUtils.stringToCollect(content);
			} catch (Throwable e) {
				logger.error("【parms】:{},【error msg】：{}",content,e.getMessage());
			}
		}
		return null;
	}
}
