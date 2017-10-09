package com.nanyixuan.zzyl_andorid.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;

import butterknife.BindView;
import butterknife.OnClick;

public class HtmlActivity extends BaseActivity {

    @BindView(R.id.webView)
    WebView mWebView;
    private String strUrl;

    @BindView(R.id.btn_paysuccess)
    SubmitButton  btnSuccess;
    @BindView(R.id.btn_payquestion)
    SubmitButton btnQuestion;

    private   boolean  isCarPay;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("支付宝二维码");
        mWebView.getSettings().setJavaScriptEnabled(true);
        strUrl = getIntent().getStringExtra("url");
        isCarPay=getIntent().getBooleanExtra("isCarPay",false);
        if (strUrl != null) {
            mWebView.loadUrl(strUrl);
        }
        if (isCarPay){
            btnSuccess.setVisibility(View.GONE);
            btnQuestion.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.btn_paysuccess, R.id.btn_payquestion})
    public void onclick(View v) {
        switch (v.getId()) {
            case R.id.btn_paysuccess:
                gotoActivity(OrderListActivity.class,true);
                break;
            case R.id.btn_payquestion:
                gotoActivity(OrderListActivity.class,true);
                break;
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_html;
    }
}
