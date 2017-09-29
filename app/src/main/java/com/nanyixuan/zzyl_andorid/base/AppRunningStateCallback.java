package com.nanyixuan.zzyl_andorid.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.view.activity.PayActivity;

/**
 * description: 监听支付从后台回到前台
 * Created by liNan on 2017/8/28 10:00
 */

public class AppRunningStateCallback implements Application.ActivityLifecycleCallbacks {

    private boolean mPayOnResumed = false;
    private boolean mPayOnPaused = false;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {
        mPayOnResumed=activity instanceof PayActivity;
        if (mPayOnResumed && mPayOnPaused){
            ToastUtils.showShort("回到支付界面");
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        mPayOnPaused=(activity instanceof PayActivity);
    }

    @Override
    public void onActivityStopped(Activity activity) {
//        activityCount--;
//        if (0 == activityCount) {
//            isForeground = false;
//        }

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
