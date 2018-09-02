package com.winterchen.dao.redis;

import org.springframework.stereotype.Component;

@Component
public class RedisConstant {

	/**
	 * 外呼
	 */
	public static final String OUTCALL_KEY = "OUTCALL_KEY";
	// 开启外呼开关
	public static final String OUTCALL_FEATURETOGGLE_FIELD_KEY = "OUTCALL_FEATURETOGGLE_FIELD_KEY";
	public static final String OUTCALL_FEATURETOGGLE_FIELD = "FEATURETOGGLE_FIELD";

	//
	public static final String OUTCALL_RESOURCENUMBER_FIELD = "ResourceNumber";
	// 渠道设置量
	public static final String OUTCALL_CLUECHANNELCONFIG_FIELD = "OutcallClueChannelConfig";

	// 每天提取的线索总人数
	public static final String OUTCALL_CLUENUMCONFIG_FIELD = "OUTCALL_CLUENUMCONFIG_FIELD";
	//当天提取的线索总数
	public static final String OUTCALL_CURRENTDAY_COUNT_KEY = "outCallCountCurrentDaykey";	
	public static final String OUTCALL_CURRENTDAY_COUNT = "outCallCountCurrentDay";	
	
	public static final String OUTCALL_CHANNEL_KEY = "OUTCALL_CHANNEL_KEY";
	
	//5分钟
	public static final Integer EXPRIE_FIVEMINU=300000;
	//过期时间2--1小时
	public static final Integer EXPRIE_ONEHOUR=3600000;
	//1分钟
	public static final Integer EXPRIE_ONEMINU=60000;
	
	
	 public static final String OUTCALL_DEALER_INFO="OUTCALL_DEALER_INFO";
	 
	 public static final String OUTCALL_CHANNEL_NUM_KEY = "OUTCALL_CHANNEL_NUM_KEY";
	 
	 public static final String OUTCALL_CUSTOMER_KEY = "OUTCALL_CUSTOMER_KEY";
	 
	 public static final String OUTCALL_USERS_KEY = "OUTCALL_USERS_KEYS";

}
