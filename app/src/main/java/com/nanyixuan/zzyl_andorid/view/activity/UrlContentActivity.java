package com.nanyixuan.zzyl_andorid.view.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

import static com.nanyixuan.zzyl_andorid.R.id.webView;

/**
 * webView 展示详情
 */
public class UrlContentActivity extends AppCompatActivity {
    public static final String TAG = "UrlContentActivity";
    @BindView(webView)
    WebView mWebView;
    @BindView(R.id.ib_qrcode)
    ImageButton btnShowQR;
    @BindView(R.id.tv_url_title)
    TextView urlTitle;
    @BindView(R.id.tv_error)
    TextView tvError;
    @BindView(R.id.loading)
    AVLoadingIndicatorView avi;
    private String className;
    private String url;

    //保存title集合
    private Stack<String> titleStack = new Stack<>();

    private boolean ispay;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_url_content);
        ButterKnife.bind(this);
        initView();

    }

    @SuppressLint("SetJavaScriptEnabled")
    protected void initView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setSupportZoom(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
        mWebView.getSettings().setSupportMultipleWindows(false);
        mWebView.getSettings().getAllowFileAccess();
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new ChromeClient());
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                avi.show();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                avi.hide();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                mWebView.setVisibility(View.GONE);
                tvError.setVisibility(View.VISIBLE);

            }

        });
        url = getIntent().getBundleExtra("data").getString("url");
        ispay = getIntent().getBundleExtra("data").getBoolean("ispay");
        if (url != null) {
            mWebView.loadUrl(url);
            // 根据url的后缀来判断哪个页面显示扫一扫按扭(园博资讯，植物百科需要显示)
            className = url.substring(url.lastIndexOf("/", url.length()) + 1);
        }
        String h5 = getIntent().getBundleExtra("data").getString("h5");
        if (h5 != null) {
            mWebView.loadDataWithBaseURL(null, h5, null, null, null);
        }

        if (!TextUtils.isEmpty(className)) {
            // 根据url的后缀来判断哪个页面显示扫一扫按扭(园博资讯，植物百科需要显示)
            if (className.equalsIgnoreCase("zhiwubaikelei.html") || className.equalsIgnoreCase("yuanbodongtai.html")) {
                btnShowQR.setVisibility(View.VISIBLE);
            } else {
                btnShowQR.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void onPause() {
        mWebView.reload();// 暂停网页中正在播放的视频
//        mWebView.clearCache(true);
//        mWebView.clearHistory();
//        mWebView.onPause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    class ChromeClient extends WebChromeClient {
        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            urlTitle.setText(title);
            titleStack.push(title);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            callback.invoke(origin, true, false);
            super.onGeolocationPermissionsShowPrompt(origin, callback);
        }
    }

    /**
     * 打开扫一扫
     */
    @OnClick(R.id.ib_qrcode)
    public void showQR() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.CAMERA).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(@NonNull Boolean aBoolean) throws Exception {
                if (aBoolean) {
                    startActivityForResult(new Intent(UrlContentActivity.this, CaptureActivity.class), Constant.REQUEST_CODE);
                } else {
                    Toast.makeText(UrlContentActivity.this, "此功能需要摄像头权限", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    mWebView.loadUrl(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(UrlContentActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && mWebView.canGoBack()) {
            mWebView.goBack(); // goBack()表示返回WebView的上一页面
            //pop 的是当前页面的标题 所以Pop之后取栈中最后一个值
            if (titleStack.size() > 1) {
                titleStack.pop();
                urlTitle.setText(titleStack.get(titleStack.size() - 1));
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 返回按钮与关闭
     *
     * @param view
     */
    @OnClick({R.id.tv_url_close, R.id.tv_url_back})
    public void closeActivity(View view) {
        if (view.getId() == R.id.tv_url_back && mWebView.canGoBack()) {
            mWebView.goBack();
            if (titleStack.size() > 1) {
                titleStack.pop();
                urlTitle.setText(titleStack.get(titleStack.size() - 1));
            }
        } else {
            if (ispay) {
                Intent intent = new Intent(this, PayActivity.class);
                intent.putExtra("alipayResult", true);
                setResult(Constant.REQUEST_ALIPAY, intent);
            }
            this.finish();

        }
    }
}
