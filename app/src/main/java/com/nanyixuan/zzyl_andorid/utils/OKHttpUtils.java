package com.nanyixuan.zzyl_andorid.utils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * description: okhttputils
 * Created by liNan on 2016/6/17 14:43
 */

public class OKHttpUtils {
	/**
	 * 配置OkHttp连接，读取和写入超时限制
	 */
	private final static OkHttpClient M_OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
			.writeTimeout(20, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).build();

	/**
	 * 不开启异步线程
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	public static Response execute(Request request) throws IOException {
		return M_OK_HTTP_CLIENT.newCall(request).execute();
	}

	/**
	 * 开启异步线程访问，访问结果自行处理
	 * 
	 * @param request
	 * @param responseCallback
	 */
	public static void enqueue(Request request, Callback responseCallback) {
		M_OK_HTTP_CLIENT.newCall(request).enqueue(responseCallback);
	}

	/**
	 * 
	 * @param url
	 *            请求的URL
	 * @param params
	 *            请求的map参数
	 * @return 返回request对象
	 */
	public static Request request(String url, Map<String, String> params) {
		FormBody.Builder form = new FormBody.Builder();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				// 装载表单值
				form.add(entry.getKey(), entry.getValue());
			}
		}
		RequestBody body = form.build();
		Request request = new Request.Builder().url(url).post(body).build();
		return request;

	}

	/**
	 * 开启异步线程访问,不对访问结果进行处理
	 * 
	 * @param request
	 */
	public static void enqueue(Request request) {
		M_OK_HTTP_CLIENT.newCall(request).enqueue(new Callback() {

			@Override
			public void onFailure(Call arg0, IOException arg1) {

			}

			@Override
			public void onResponse(Call arg0, Response arg1) throws IOException {

			}
		});
	}

	/**
	 * 为HttpGet请求拼接一个参数
	 * 
	 * @param url
	 * @param name
	 * @param value
	 */
	public static String jointURL(String url, String name, String value) {
		return url + "?" + name + "=" + value;
	}

	/**
	 * 为HttpGet请求拼接多个参数
	 * 
	 * @param url
	 */
	public static String jointURL(String url, Map<String, String> values) {
		StringBuffer result = new StringBuffer();
		result.append(url).append("?");
		Set<String> keys = values.keySet();
		for (String key : keys) {
			result.append(key).append("=").append(values.get(key)).append("&");
		}
		return result.toString().substring(0, result.toString().length() - 1);
	}

}
