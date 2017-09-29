package com.nanyixuan.zzyl_andorid.utils;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Environment;
import android.text.TextUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * description:
 * Created by liNan on 2017/8/1 14:25
 */

public class MyTools {

    /**
     * 是否存在SD卡
     */
    public static boolean isSDCardExists() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    /**
     * SD卡根目录
     */
    public static String getSDPath() {
        if (!isSDCardExists()) {
            return null;
        }
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    /**
     * 背景颜色
     */
    public static StateListDrawable setSelector(int nRgb, int pRgb, float radius) {
        Drawable normalDrawable = createDrawable(nRgb, radius);
        Drawable pressDrawable = createDrawable(pRgb, radius);
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed}, pressDrawable);
        stateListDrawable.addState(new int[]{}, normalDrawable);
        return stateListDrawable;
    }

    /**
     * 设置textiview 按下颜色
     *
     * @return
     */
    public static ColorStateList setTextColor(int colors[]) {
        int state[][] = {{android.R.attr.state_pressed}, {}};
        ColorStateList colorStateList = new ColorStateList(state, colors);
        return colorStateList;
    }

    /**
     * 创建shape
     *
     * @param rgb
     * @param radius
     * @return
     */
    public static GradientDrawable createDrawable(int rgb, float radius) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(rgb);//设置颜色
//        gradientDrawable.setColors(rgb);
        gradientDrawable.setCornerRadius(radius);//设置圆角的半径
        gradientDrawable.setShape(GradientDrawable.RECTANGLE);
        return gradientDrawable;
    }

    /**
     * 拼接url和参数
     *
     * @param url
     * @param values
     * @return
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

    /**
     * 把系统时间分割为年，月，日
     *
     * @return返回年月日数组
     */
    public static int[] splitSystemDate() {
        Calendar c = Calendar.getInstance();
        int[] date = new int[]{c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE),
                c.get(Calendar.HOUR_OF_DAY)};
        return date;
    }

    /**
     * 获取系统当前时间
     *
     * @param format
     * @return
     */
    public static String getNowDate(String format) {
        if (TextUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
        String currDate = sdf.format(date);
        return currDate;
    }

    /**
     * 生成5位随机数
     *
     * @return
     */
    public static String noneStr() {
        int n = (int) (Math.random() * 10000 + 10000);
        return String.valueOf(n);
    }

    /**
     * 保留两位小数
     *
     * @param str
     * @return
     */
    public static String getString2Float(String str) {
        if (TextUtils.isEmpty(str)) {
            return "0.00";
        }
        Double d = Double.valueOf(str);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        return decimalFormat.format(d);
    }

    /**
     * 改变image 方向
     *
     * @param img
     * @param from
     * @param to
     */
    public static void rotationExpandIcon(final ImageView img, float from, float to) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);//属性动画
        valueAnimator.setDuration(300);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                img.setRotation((Float) valueAnimator.getAnimatedValue());
            }
        });
        valueAnimator.start();
    }

    /**
     * 判断是否需要更新
     */
    public static boolean judgeUpdate(Context mContext, String versionName) {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            float fv1 = Float.parseFloat(versionName);
            float fv2 = Float.parseFloat(pi.versionName);
            if (fv1 - fv2 > 0) {
                return true;
            } else {
                return false;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 个人信息
     *
     * @return
     */
    public static LoginBean getLoginInfo() {
        boolean isLogin = com.blankj.utilcode.util.SPUtils.getInstance().getBoolean(Constant.SP_LOGIN);
        if (isLogin) {
            String strLoginInfo = com.blankj.utilcode.util.SPUtils.getInstance().getString(Constant.SP_USER_INFO);
            LoginBean loginBean = JsonUtil.fromJson(strLoginInfo, LoginBean.class);
            return loginBean;
        }
        return null;
    }

    /**
     * 查询订单状态
     *
     * @param state
     * @return
     */
    public static String getOrderState(String state) {
        String status = "";
        switch (Integer.parseInt(state)) {
            case 0:
                status = "未支付";
                break;
            case 1:
                status = "已支付";
                break;
            case 2:
                status = "退款中";
                break;
            case 3:
                status = "已退款";
                break;
            case -1:
                status = "订单失效";
                break;
            default:
                status = "未识别状态";
                break;
        }
        return status;
    }

}
