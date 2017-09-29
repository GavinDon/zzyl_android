package com.nanyixuan.zzyl_andorid.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Author linan Date：2016年6月22日 下午4:21:36
 */
public class JsonUtil {
	/**
	 * 对象转换成json字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * json字符串转成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Type type) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(str, type);

		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * json字符串转成对象
	 * 
	 * @param str
	 * @param type
	 * @return
	 */
	public static <T> T fromJson(String str, Class<T> type) {
		Gson gson = new Gson();
		try {
			return gson.fromJson(str, type);

		} catch (Exception e) {
		}
		return null;
	}
}
