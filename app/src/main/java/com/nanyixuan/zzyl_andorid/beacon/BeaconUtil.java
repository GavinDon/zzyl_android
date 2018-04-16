package com.nanyixuan.zzyl_andorid.beacon;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;

import com.blankj.utilcode.util.ToastUtils;
import com.brtbeacon.sdk.BRTBeacon;
import com.brtbeacon.sdk.BRTBeaconManager;
import com.brtbeacon.sdk.BRTThrowable;
import com.brtbeacon.sdk.callback.BRTBeaconManagerListener;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.bean.BeaconBean;
import com.nanyixuan.zzyl_andorid.view.activity.UrlContentActivity;

import java.io.IOException;
import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * description:
 * Created by liNan on 2017/8/10 14:08
 */

public class BeaconUtil {
    private Context mContext;
    private BRTBeaconManager beaconManager = null;
    private String fName = "";
    private String currName;

    public BeaconUtil(Context mContext) {
        this.mContext = mContext;
        beaconManager = BRTBeaconManager.getInstance(mContext);
        onResume();

    }

    public void onResume() {
        beaconManager.setBRTBeaconManagerListener(scanListener);
        beaconManager.startRanging();
    }

    /**
     * 暂停蓝牙扫描
     */
    public void onPause() {
        beaconManager.setBRTBeaconManagerListener(null);
        beaconManager.stopRanging();
    }

    /**
     * 获取匹配ID对应的链接
     */
    private void getBeacon(final String title, final String code) {
        RetrofitHelper.getInstance().creat().getBeaconInfo(code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(mContext, false, new SubCallback<BeaconBean>() {
                    @Override
                    public void onNext(BeaconBean beaconBean) throws IOException {
                        if (beaconBean != null) {
                            if (TextUtils.isEmpty(beaconBean.getUrl())) {
                                ToastUtils.showShort(beaconBean.getLocaion2()+"==");
                                //如果url 为空的话
                                nofifyFun(beaconBean.getLocaion2(), "");
                            } else {
                                nofifyFun(beaconBean.getLocaion2(), beaconBean.getUrl());
                            }
                        }

                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));

    }


    /**
     * beacon管理类
     */
    private BRTBeaconManagerListener scanListener = new BRTBeaconManagerListener() {

        @Override
        public void onUpdateBeacon(final ArrayList<BRTBeacon> beacons) {
            System.out.println("onupdate");
        }

        @Override
        public void onNewBeacon(BRTBeacon beacon) {

            if (beacon.getMinor() != 0 ) {
                String name = beacon.getName();
                int code = beacon.getMinor();
                if (!TextUtils.isEmpty(name))
                if (name.equals(fName)) {
                    return;
                } else {
                    getBeacon(name, String.valueOf(code));
                }
                fName = name;

            }

        }

        @Override
        public void onGoneBeacon(BRTBeacon beacon) {
            System.out.println("GoneBeacon");
        }

        @Override
        public void onError(BRTThrowable arg0) {
            System.out.println(arg0.getError());
        }
    };

    /**
     * 启动webView并传值
     *
     * @param url
     */
    private Intent setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent(mContext, UrlContentActivity.class);
        intent.putExtra("data", bundle);
        return intent;
    }

    /**
     * 进行通知
     */
    private void nofifyFun(String title, String url) {
        NotificationManager nm = (NotificationManager) mContext.getSystemService(NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(mContext);
        Notification notification;
        Intent mIntent = setIntentData(url);
        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.logo);
        builder.setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.logo));
        builder.setAutoCancel(true); //用户点击后自动取消
        builder.setContentTitle(title);
        builder.setVibrate(new long[]{500L, 200L, 200L, 500L}); //带振动
//        builder.setContentText(url);
        //API16之后获取Notification对象的方法
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        } else {
            notification = builder.getNotification();
        }
        nm.notify(1, notification);
    }

}
