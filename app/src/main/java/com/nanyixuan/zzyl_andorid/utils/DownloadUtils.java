package com.nanyixuan.zzyl_andorid.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.widgets.UpdateDialog;

/**
 * DownloadManager.Request ：封装一个下载请求添加到系统下载器队列。
 * DownloadManager.Query ：查询下载任务，可实时获取下载进度，下载结果。
 * description: 下载app
 * Created by liNan on 2017/8/17 15:24
 */

public class DownloadUtils implements UpdateDialog.UpdateCallBack {

    private String strVersionCode = "0";  //服务器版本号
    private Activity mContext;
    private DownloadManager downloadManager;
    private DownloadManager.Request mRequest;
    private String apkUrl;
    private long downloadId;
    private boolean isForce;
    private UpdateDialog mUpdateDialog;

    public DownloadUtils(Activity context) {
        this.mContext = context;
    }

    /**
     * @param url 下载app的地址
     */
    public DownloadUtils setUrl(String url) {
        this.apkUrl = url;
        return this;
    }

    /**
     * @param version 服务器返回的版本号
     */
    public DownloadUtils setVersion(String version) {
        this.strVersionCode = version;
        return this;
    }

    public DownloadUtils setIsForce(boolean isForce) {
        this.isForce = isForce;
        return this;
    }

    /**
     * 执行downloadManager.enqueue(request)来下载apk
     */
    public void startDownLoad() {

        if (TextUtils.isEmpty(apkUrl)) {
            Toast.makeText(mContext, "更新url出现问题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (judgeUpdate()) {
            mUpdateDialog = UpdateDialog.newInstance(strVersionCode, mContext.getString(R.string.air_info), isForce);
            if (isForce) mUpdateDialog.setCancelable(false);
            mUpdateDialog.showDialog(mContext);
            mUpdateDialog.setOnBtnListener(this);
        }
    }

    private void initDownLoad() {
        /**
         *  request 相当于okHttp3 执行异步请求
         */
        mRequest = new DownloadManager.Request(Uri.parse(apkUrl)); //创建下载任务
        mRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        mRequest.setTitle("郑州园林");
        mRequest.setDescription("正在下载...");
        mRequest.setVisibleInDownloadsUi(true);
        //设置下载的路径
//        mRequest.setDestinationInExternalPublicDir(MyTools.getSDPath() + Constant.PATH_APK, "zzyl.apk");
        mRequest.setDestinationInExternalFilesDir(mContext, MyTools.getSDPath() + Constant.PATH_APK, "zzyl.apk");

        /**
         *   获取DownloadManager
         */
        downloadManager = (DownloadManager) mContext.getSystemService(Context.DOWNLOAD_SERVICE);
        downloadId = downloadManager.enqueue(mRequest); //下载Id
        //注册广播监听下载的各个状态
        mContext.registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        queryProgress();
    }

    /**
     * 判断是否需要更新
     */
    private boolean judgeUpdate() {
        PackageManager packageManager = mContext.getPackageManager();
        PackageInfo pi = null;
        try {
            pi = packageManager.getPackageInfo(mContext.getPackageName(), 0);
            String versionCode = pi.versionName;
            if (strVersionCode.compareTo(versionCode) > 0) {
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
     * 查询进度
     */
    private void queryProgress() {
        DownloadManager.Query  query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = downloadManager.query(query);
        if (cursor != null && cursor.moveToFirst()) {
            int fileUri = cursor.getColumnIndex(DownloadManager.COLUMN_URI);
            String fu = cursor.getString(fileUri);
            int totalSizeBytesIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
            int bytesDownloadSoFarIndex = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);

            // 下载的文件总大小
            int totalSizeBytes = cursor.getInt(totalSizeBytesIndex);
            // 截止目前已经下载的文件总大小
            int bytesDownloadSoFar = cursor.getInt(bytesDownloadSoFarIndex);
            LogUtils.i(this.getClass().getName(),
                    "from " + fu + " 下载到本地 "  + totalSizeBytes + " 已经下载:" + bytesDownloadSoFar);

        }
        cursor.close();
    }

    /**
     * 广播接收下载状态
     */
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
            Cursor cursor = downloadManager.query(query);
            if (cursor.moveToFirst()) {
                int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                switch (status) {
                    case DownloadManager.STATUS_SUCCESSFUL:
                        //下载完成安装APK
                        installAPK();
                        break;
                    case DownloadManager.STATUS_FAILED:
                        Toast.makeText(mContext, "下载失败", Toast.LENGTH_SHORT).show();
                        break;
                    case DownloadManager.STATUS_RUNNING:
                        LogUtils.i("d");
                        break;
                }
                cursor.close();
            }
        }
    };

    //下载到本地后执行安装
    private void installAPK() {
        //获取下载文件的Uri
        Uri downloadFileUri = downloadManager.getUriForDownloadedFile(downloadId);
        if (downloadFileUri != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public void setBtn(int btn) {
        if (btn == 0) {
            initDownLoad();
        }
    }

}
