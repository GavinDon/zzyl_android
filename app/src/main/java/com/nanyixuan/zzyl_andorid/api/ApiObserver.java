package com.nanyixuan.zzyl_andorid.api;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by YixuanNan on 2017/3/15.
 *
 * @author YixuanNan
 */

public abstract class ApiObserver<T> implements Observer<T> {
    private ProgressDialog progressDialog;
    private Context context;
    private Observable<T> observable;
    private boolean isShow = true;

    private void showDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("数据加载中...");
            progressDialog.setCancelable(true);
        }
        progressDialog.show();
    }

    private void dismissDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("网络连接中...");
            progressDialog.setCancelable(true);
        }

        progressDialog.dismiss();
    }


    @Override
    public void onSubscribe(Disposable d) {
        showDialog();
    }

    @Override
    public void onNext(T t) {
//        if (t instanceof RespCommon) {
//            if (((RespCommon) t).getRetCode().equals("0")) {
//                onSuccess(t);
//            } else {
//                ToastUtils.showShort("retCode==1");
//            }
//        }
//        if (t instanceof BaseData) {
//            if (((BaseData) t).isSuccess()) {
//                onSuccess(t);
//            } else {
//                ToastUtils.showShort(((BaseData) t).getMsg());
//            }
//        }
        onSuccess(t);
        Log.i("nanyixuan", "ApiObserver onNext = " + t.toString());
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onFail(e);
        dismissDialog();
        String showError = e.getMessage() + "\n" + "需要重新连接吗？";
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setNegativeButton("是", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                observable.subscribe(ApiObserver.this);
                dialogInterface.dismiss();
            }
        }).setMessage(showError);

        if (e instanceof SocketTimeoutException) {
            if (NetworkUtils.isConnected()) {
//                alertDialog.show();
            } else {
                ToastUtils.showShort("网络不可用");
            }
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("ConnectException");

        } else if (e instanceof UnknownHostException) {
            ToastUtils.showShort("连接异常，请检查网络是否通畅");
        } else {
//            observable.subscribe(ApiObserver.this);
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onComplete() {
        dismissDialog();

    }

    public abstract void onSuccess(T tBaseData);

    public void setContext(Context context) {
        this.context = context;
    }

    public void setShowDialog(boolean isShow) {
        this.isShow = isShow;
    }

    public void setObservable(Observable<T> observable) {
        this.observable = observable;
    }

    public abstract void onFail(Throwable t);
}
