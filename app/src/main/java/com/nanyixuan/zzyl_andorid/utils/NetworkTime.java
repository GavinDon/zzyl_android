package com.nanyixuan.zzyl_andorid.utils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 读取网络时间
 *
 * @author SHANHY(365384722@QQ.COM)
 * @date 2015年11月27日
 */
public class NetworkTime {

    public static String webUrl1 = "http://www.bjtime.cn";//bjTime
    public static String webUrl2 = "http://www.baidu.com";//百度
    public static String webUrl3 = "http://www.taobao.com";//淘宝
    public static String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心
    public static String webUrl5 = "http://www.360.cn";//360
    public static String webUrl6 = "http://www.beijing-time.org";//beijing-time

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author SHANHY
     * @date 2015年11月27日
     */
    public static String getWebsiteDatetime(String webUrl, String format) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
