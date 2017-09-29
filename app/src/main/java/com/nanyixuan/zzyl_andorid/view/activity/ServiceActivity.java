package com.nanyixuan.zzyl_andorid.view.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 服务信息
 */
public class ServiceActivity extends BaseActivity {
    public static final String TAG = "ServiceActivity";

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("服务信息");
    }

    @BindView(R.id.tvPhone2)
    AppCompatTextView tvPhoneNum;

    @Override
    public int setLayout() {
        return R.layout.fragment_service;
    }

    @OnClick({R.id.layout_suggest, R.id.tvPhone2})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.tvPhone2:
                //拨打服务电话
                call(tvPhoneNum.getText().toString());
                break;
            case R.id.layout_suggest:
                //投诉建议
                setIntentData("http://zhihui.expo2017.net.cn/zz/yijiantousu.html");
                break;
        }
    }
    private void setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent(this, UrlContentActivity.class);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    /**
     * 调用拨号功能
     *
     * @param phone 电话号码
     */
    private void call(final String phone) {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CALL_PHONE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(@NonNull Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}