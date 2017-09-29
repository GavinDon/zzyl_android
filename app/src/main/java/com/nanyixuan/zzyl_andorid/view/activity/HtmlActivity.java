package com.nanyixuan.zzyl_andorid.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class HtmlActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    private String strUrl;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("支付宝二维码");
        mWebView.getSettings().setJavaScriptEnabled(true);
        strUrl = getIntent().getStringExtra("url");
        if (strUrl != null) {
            mWebView.loadUrl(strUrl);
        }
    }

    @OnClick({R.id.btn_paysuccess, R.id.btn_payquestion})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btn_paysuccess:
                gotoActivity(MainActivity.class,true);
                break;
            case R.id.btn_payquestion:
                gotoActivity(MainActivity.class,true);
                break;
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_html;
    }
}
