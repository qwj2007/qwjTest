package com.winterchen.utils;

import org.apache.log4j.Logger;

import java.text.MessageFormat;

/**
 *
 * @author zhangweidong
 *
 */
public  class StringUtil {
	private static Logger log = Logger.getLogger(StringUtil.class);

	private StringBuffer sb = new StringBuffer();

	public StringUtil() {}

	public StringUtil(String str) {
		sb.append(str);
	}

	public StringUtil(Object str) {
		sb.append(str);
	}

	public StringUtil cp() {
		return this.copy();
	}

	public StringUtil copy() {
		return new StringUtil(sb);
	}

	public StringUtil ap(String str) {
		return this.append(str);
	}

	public StringUtil ap(Object str) {
		return this.append(str);
	}

	public StringUtil append(String str) {
		sb.append(str);
		return this;
	}

	public StringUtil append(Object str) {
		sb.append(str);
		return this;
	}

	public StringUtil apfm(String pattern, Object... arguments) {
		return this.appendFormat(pattern, arguments);
	}

	public StringUtil appendFormat(String pattern, Object... arguments) {
		sb.append(MessageFormat.format(pattern, arguments));
		return this;
	}

	public String ts() {
		return this.toString();
	}

	@Override
	public String toString() {
		return this.sb.toString();
	}

}
