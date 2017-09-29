package com.nanyixuan.zzyl_andorid.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import com.blankj.utilcode.util.FileUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.utils.OKHttpUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * description: 更新app service
 * Created by liNan on 2017/9/13 14:06
 */

public class UpdateService extends Service {
    // 通知栏
    private NotificationManager mNotificationManager;
    private Notification.Builder builder;
    private Notification mNotification;
    private PendingIntent mPendingIntent;
    // 创建通知栏
    private RemoteViews contentView;
    private String apkUrl;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null!=intent){
            apkUrl=intent.getStringExtra(Constant.UPDATE_URL);
//            createNotify();
            okDownLoadApk();
        }
        return START_NOT_STICKY;

    }

    private void createNotify() {
        // 获取通知栏管理对象;
        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(this)
                .setSmallIcon(R.mipmap.logo)
                .setWhen(System.currentTimeMillis())
                .setContentTitle("郑州园林更新")
                .setContentText("下载中...")
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(Intent.ACTION_DELETE), 0));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mNotification = builder.build();
        } else {
            mNotification = builder.getNotification();
        }
        mNotificationManager.notify(1, mNotification);
    }

    private File mSavefile;// 保存APK的文件路径
    private File mSaveFileApk;// APK 的绝对路径

    /**
     * 使用Okhttp 下载 不使用retroftit
     */
    public void okDownLoadApk() {
        Request request = new Request.Builder().url(apkUrl)
                .build();
        OKHttpUtils.enqueue(request, new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                ToastUtils.showShort(e.getMessage());
            }

            @Override
            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                if (response.isSuccessful()) {
                    InputStream is = null;
                    FileOutputStream fos = null;
                    byte[] buf = new byte[4098];
                    long total = response.body().contentLength();
                    try {
                        is = response.body().byteStream();
                        //创建保存APK 的文件夹
                        String mSavePath = MyTools.getSDPath() + Constant.PATH_APK;
                        mSavefile = new File(mSavePath);
                        //创建APK文件
                        mSaveFileApk = new File(mSavefile.getPath(), "zzyl.apk");
                        if (!mSavefile.exists()) {
                            mSavefile.mkdir();
                        }
//                        if (!mSaveFileApk.exists()) {
//                            mSaveFileApk.createNewFile();
//                        }
                        FileUtils.createFileByDeleteOldFile(mSaveFileApk);
                        //从内存中把流写入创建好的apk 文件
                        fos = new FileOutputStream(mSaveFileApk);
                        int len; //读取的长度
                        long sum = 0; //读取的总长度
                        Intent intent = new Intent();
                        intent.setAction(Constant.UPDATE_PROGRESS);
                        while ((len = is.read(buf)) != -1) {
                            fos.write(buf, 0, len);//将获取到的流写入文件中
                            sum += len;
                            int progress = (int) (sum * 100 / total); //计算百分比
//                            builder.setProgress(100, progress, false);
//                            mNotificationManager.notify(1, mNotification);
                            intent.putExtra("progress", progress);
                            sendBroadcast(intent);
                            if (progress == 100) {
                                sendInstallApkBroad(); //安装apk
//                                builder.setContentTitle("开始安装");
//                                builder.setContentText("安装中...");
                                //设置进度为不确定，用于模拟安装
//                                builder.setProgress(0, 0, true);
//                                mNotificationManager.notify(1, mNotification);
                            }
                        }

                        fos.flush();

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (is != null)
                                is.close();
                        } catch (IOException e) {
                        }
                        try {
                            if (fos != null)
                                fos.close();
                        } catch (IOException e) {
                        }
                    }
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }

    /**
     * 安装apk文件
     */
    public void sendInstallApkBroad() {
        if (Build.VERSION.SDK_INT >= 24) {

        }
        Uri uri = Uri.fromFile(mSaveFileApk);
        Intent intentPath = new Intent(Intent.ACTION_VIEW);
        intentPath.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intentPath.setDataAndType(uri, "application/vnd.android.package-archive");
        startActivity(intentPath);
        android.os.Process.killProcess(android.os.Process.myPid());// 如果不加上这句的话在apk安装完成之后点击单开会崩溃
    }


    /**
     * retrofit (貌似)是把文件下载完成 之后才走OnNext 导致更新进度不走。
     */
    private void getDownLoad() {
        RetrofitHelper.getInstance().creat().downloadApp("http://116.196.90.178/BusSafe3.1.apk")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, false, new SubCallback<ResponseBody>() {
                    @Override
                    public void onNext(ResponseBody response) throws IOException {
                        InputStream is = null;
                        FileOutputStream fos = null;
                        byte[] buf = new byte[4098];
                        long total = response.contentLength();
                        try {
                            is = response.byteStream();
                            //创建保存APK 的文件夹
                            String mSavePath = MyTools.getSDPath() + Constant.PATH_APK;
                            mSavefile = new File(mSavePath);
                            //创建APK文件
                            mSaveFileApk = new File(mSavefile.getPath(), "zzyla.apk");
                            if (!mSavefile.exists()) {
                                mSavefile.mkdir();
                            }
                            if (!mSaveFileApk.exists()) {
                                mSaveFileApk.createNewFile();
                            } else {
                                mSaveFileApk.delete();
                            }
                            //从内存中把流写入创建好的apk 文件
                            fos = new FileOutputStream(mSaveFileApk);
                            int len; //读取的长度
                            long sum = 0; //读取的总长度
                            Intent intent = new Intent();
                            intent.setAction(Constant.UPDATE_PROGRESS);
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);//将获取到的流写入文件中
                                sum += len;
                                int progress = (int) (sum * 100 / total); //计算百分比
                                intent.putExtra("progress", progress);
                                sendBroadcast(intent);
                                if (progress == 100) {
                                    sendInstallApkBroad(); //安装apk
                                }
                            }

                            fos.flush();

                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                if (is != null)
                                    is.close();
                            } catch (IOException e) {
                            }
                            try {
                                if (fos != null)
                                    fos.close();
                            } catch (IOException e) {
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

}
