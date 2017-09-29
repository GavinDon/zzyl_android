package com.nanyixuan.zzyl_andorid;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.IBle;
import com.brtbeacon.sdk.utils.L;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nanyixuan.zzyl_andorid.api.UserManager;
import com.nanyixuan.zzyl_andorid.base.AppRunningStateCallback;
import com.nanyixuan.zzyl_andorid.utils.CrashHandler;
import com.umeng.analytics.MobclickAgent;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YixuanNan on 2017/3/22.
 *
 * @author YixuanNan
 */

public class MyApplication extends Application {
    public static Context appContext;
    public static Map<String, Long> map = new HashMap<>(); //存放短信倒计时时间
    private BRTBeaconManager beaconManager;
    public  AppRunningStateCallback mAppRunningStateCallback;
    public static String carToken; //停车接口token
    public static long tokenTime; //token

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = getApplicationContext();
//        cn.smssdk.SMSSDK.initSDK(this, "1c894c521f09c", "c6f2695d9c5f8f0a6587fd1282a84a5e");
        UserManager.init(this);
        Fresco.initialize(this);
//        SDKInitializer.initialize(this); //百度初始化
        CrashHandler.getInstance().init(this); //异常捕捉
        ZXingLibrary.initDisplayOpinion(this); //二维码
        Utils.init(this);
        MobclickAgent.startWithConfigure(new MobclickAgent.UMAnalyticsConfig(this, getResources().getString(R.string.um_appkey), "channelId", MobclickAgent.EScenarioType.E_UM_NORMAL));
        //禁止默认的页面统计方式，这样将不会再自动统计Activity。
        MobclickAgent.openActivityDurationTrack(false);
        createBrightBeacon();
        mAppRunningStateCallback = new AppRunningStateCallback();
//        registerActivityLifecycleCallbacks(mAppRunningStateCallback);
    }


    @Override
    public void onTerminate() {
        //使用模拟器调试调用的方法
//        unregisterActivityLifecycleCallbacks(mAppRunningStateCallback);
        super.onTerminate();
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (level==TRIM_MEMORY_RUNNING_LOW){

        }
    }

    private void createBrightBeacon() {
        L.enableDebugLogging(true);
        //获取单例
        beaconManager = BRTBeaconManager.getInstance(this);
        // 注册应用 APPKEY申请:http://brtbeacon.com/main/index.shtml
        beaconManager.registerApp("d773f54b43d547a6bd849956e377ae99");
        // 开启Beacon扫描服务
        beaconManager.startService();

    }

    /**
     * 创建Beacon连接需要传递此参数
     *
     * @return IBle
     */
    public IBle getIBle() {
        return beaconManager.getIBle();
    }

    /**
     * 获取Beacon管理对象
     *
     * @return BRTBeaconManager
     */
    public BRTBeaconManager getBRTBeaconManager() {
        return beaconManager;
    }

}