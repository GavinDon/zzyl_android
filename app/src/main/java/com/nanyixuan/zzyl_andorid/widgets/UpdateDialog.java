package com.nanyixuan.zzyl_andorid.widgets;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.numberprogressbar.NumberProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description: app更新对话框
 * Created by liNan on 2017/8/17 14:43
 */

public class UpdateDialog extends DialogFragment implements View.OnKeyListener {

    private String strVersionCode; //服务器返回的版本号
    private String strUpdateContent;
    @BindView(R.id.dialog_confirm)
    SubmitButton btnConfirm;
    @BindView(R.id.dialog_title)
    TextView tvTitle;
    @BindView(R.id.dialog_cancel)
    SubmitButton btnCancel;
    @BindView(R.id.dialog_content)
    TextView tvContent;
    @BindView(R.id.update_progress)
    NumberProgressBar mProgressBar;
    private boolean isForce;

    private MyApkBroadcast mApkBroadcast;
    private Activity mActivity;


    public static UpdateDialog newInstance(String code, String description, boolean isForce) {
        UpdateDialog updateDialog = new UpdateDialog();
        Bundle bundle = new Bundle();
        bundle.putString("version", code);
        bundle.putString("description", description);
        bundle.putBoolean("isForce", isForce);
        updateDialog.setArguments(bundle);
        return updateDialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        strVersionCode = bundle.getString("version");
        strUpdateContent = bundle.getString("description");
        isForce = bundle.getBoolean("isForce");
        //设置无标题 且保证宽度为屏幕的65%
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog_MinWidth);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.update_dialog, null, false);
        ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitle.setText(String.format("%s 版本更新", strVersionCode));
        tvContent.setText(strUpdateContent);
        Window window = getDialog().getWindow();
        window.setGravity(Gravity.CENTER);
    }

    /**
     * 设置button文字
     *
     * @param text
     */
    public void setConfirmTxt(String text) {
        btnConfirm.setText(text);
    }

    @OnClick({R.id.dialog_cancel, R.id.dialog_confirm})
    public void onclick(View v) {
        if (v.getId() == R.id.dialog_confirm) {
            mCallBack.setBtn(0);
            mProgressBar.setVisibility(View.VISIBLE);
            if (isForce) {
                btnConfirm.setClickable(false);
                btnCancel.setClickable(false);
            }

        } else {
            this.dismissAllowingStateLoss();
        }
    }

    public void setBtnConfirmClickable(boolean clickable) {
        btnConfirm.setClickable(clickable);
    }


    /**
     * 显示对话框
     *
     * @param activity
     */
    public void showDialog(Activity activity) {
        this.mActivity = activity;
        this.show(activity.getFragmentManager(), "updateDialog");
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && isForce) {
            return true;
        } else {
            return false;
        }
    }


    class MyApkBroadcast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, final Intent intent) {
            final int progress = intent.getIntExtra("progress", 0);
            LogUtils.i(progress + "d");
            if (progress != 0) {
                mProgressBar.setProgress(progress);
            }
            if (progress == 100) {
                dismissAllowingStateLoss();
                isForce = false;
            }

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mActivity.unregisterReceiver(mApkBroadcast);
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.UPDATE_PROGRESS);
        mApkBroadcast = new MyApkBroadcast();
        mActivity.registerReceiver(mApkBroadcast, intentFilter);
    }

    public interface UpdateCallBack {
        void setBtn(int btn);
    }

    private UpdateCallBack mCallBack;

    public void setOnBtnListener(UpdateCallBack c) {
        mCallBack = c;
    }

}
