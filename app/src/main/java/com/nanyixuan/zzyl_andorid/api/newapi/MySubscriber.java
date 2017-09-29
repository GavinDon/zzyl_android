package com.nanyixuan.zzyl_andorid.api.newapi;

import android.content.Context;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.R;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import butterknife.BindColor;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * description:  重新封装Subsriber
 * Created by liNan on 2017/8/31 15:39
 */

public class MySubscriber<T> implements Observer<T>, ProgressCancelListener {
    private Context mContext;
    private SubCallback mSubCallback;
    private boolean isShowDialog;

    @BindColor(R.color.lightGreen)
    int lightGreen;
    private Disposable mDisposable;
    ProgressDialogHandler mProgressDialogHandler;


    public MySubscriber(Context context, boolean showDialog, SubCallback<T> subCallback) {
        this.mContext = context;
        this.mSubCallback = subCallback;
        this.isShowDialog = showDialog;
        if (showDialog) {
            mProgressDialogHandler = new ProgressDialogHandler(context, true,this);
        }
    }


    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }


    @Override
    public void onSubscribe(@NonNull Disposable d) {
        this.mDisposable = d;
        if (isShowDialog)
            showProgressDialog();

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onNext(T t) {
        try {
            mSubCallback.onNext(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
        if (isShowDialog)
            dismissProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            if (NetworkUtils.isConnected()) {
                ToastUtils.showShort("连接超时");
            } else {
                ToastUtils.showShort("网络不可用");
            }
        } else if (e instanceof ConnectException) {
            ToastUtils.showShort("ConnectException");

        } else if (e instanceof UnknownHostException) {
            ToastUtils.showShort("连接异常，请检查网络是否通畅");
        } else {
            mSubCallback.onError(e.getMessage());
        }
        dismissProgressDialog();
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onCancelProgress() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }
}
