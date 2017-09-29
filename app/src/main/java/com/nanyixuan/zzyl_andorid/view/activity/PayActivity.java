package com.nanyixuan.zzyl_andorid.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.RGBLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiService;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.AliPayBean;
import com.nanyixuan.zzyl_andorid.bean.AllOrderBean;
import com.nanyixuan.zzyl_andorid.bean.OrderBean;
import com.nanyixuan.zzyl_andorid.bean.OrderDetailBean;
import com.nanyixuan.zzyl_andorid.bean.PayBean;
import com.nanyixuan.zzyl_andorid.bean.PrepayBean;
import com.nanyixuan.zzyl_andorid.bean.PrepayDetail;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

import static com.nanyixuan.zzyl_andorid.R.id.tv_pay_money;
import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

/**
 * 支付界面
 */
public class PayActivity extends BaseActivity {
    @BindView(R.id.btn_wechat)
    Button btnWeChat;
    @BindView(R.id.btn_alipay)
    Button btnAlipay;
    @BindView(R.id.btn_other)
    Button btnOtherPay;
    private PayBean payBean; //支付参数
    @BindView(tv_pay_money)
    TextView tvPayTotal;

    @BindView(R.id.tv_pay_location)
    TextView tvPayLocation;
    private String attach; //数据包 后台分辨是哪个平台进行支付的

    @BindColor(R.color.lightGreen)
    int lightGreen;
    @BindColor(R.color.pressGreen)
    int pressGreen;
    @BindColor(R.color.blue_500)
    int lightBlue;
    @BindColor(R.color.blue_800)
    int pressBlue;
    @BindColor(R.color.orange_500)
    int anotherColor;
    @BindColor(R.color.orange_500)
    int anotherPressColor;
    @BindColor(R.color.gray)
    int grayColor;

    private OrderBean mOrderBean;
    private String realMoney;

    private String strPayPage;
    private String attachService = "1"; //1 购票 2停车 3退款


    @Override
    protected void initView(Bundle savedInstanceState) {
        initButton();
        setTitle("园博支付");
        Bundle bundle = getIntent().getExtras();
        strPayPage = bundle.getString("page"); //获取是由哪个页面跳转来的支付
        if (strPayPage.equals("0")) {
            //gardenTicketActivity跳转过来
            mOrderBean = bundle.getParcelable("orderBean");
            if (mOrderBean != null) {
                realMoney = String.valueOf(Float.parseFloat(mOrderBean.getReal_sum()) / 100);
                tvPayTotal.setText(realMoney);
                payBean = new PayBean(realMoney, mOrderBean.getId());
            }

        } else if (strPayPage.equals("1")) {
            //订单列表item跳转过来
            AllOrderBean allOrderBean = bundle.getParcelable("orderBean");
            if (allOrderBean != null) {
                realMoney = String.valueOf(Float.parseFloat(allOrderBean.getReal_sum()) / 100);
                tvPayTotal.setText(realMoney);
                payBean = new PayBean(allOrderBean.getReal_sum(), allOrderBean.getId());
            }

        } else if (strPayPage.equals("2")) {
            //订单详情跳转过来
            OrderDetailBean bean = bundle.getParcelable("orderDetailInfo");
            if (bean != null) {
                realMoney = String.valueOf(Float.parseFloat(bean.getReal_sum()) / 100);
                tvPayTotal.setText(realMoney);
                payBean = new PayBean(bean.getReal_sum(), bean.getId());
            }
        }

    }

    /**
     * 按钮样式
     */
    private void initButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            btnWeChat.setBackground(MyTools.setSelector(lightGreen, pressGreen, 10));
            btnAlipay.setBackground(MyTools.setSelector(lightBlue, pressBlue, 10));
            btnOtherPay.setBackground(MyTools.setSelector(anotherColor, anotherPressColor, 10));
        } else {
            btnWeChat.setBackgroundDrawable(MyTools.setSelector(lightGreen, pressGreen, 10));
            btnAlipay.setBackgroundDrawable(MyTools.setSelector(lightBlue, pressBlue, 10));
            btnOtherPay.setBackgroundDrawable(MyTools.setSelector(anotherColor, anotherPressColor, 10));
        }

    }


    @Override
    protected int setLayout() {
        return R.layout.activity_pay;
    }

    @OnClick({R.id.btn_wechat, R.id.btn_alipay, R.id.btn_other})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_wechat: //微信
                attach = "3";
                getWxPrepayId(payBean);
                btnWeChat.setClickable(false);
                btnWeChat.setBackgroundDrawable(MyTools.setSelector(grayColor, grayColor, 10));
                break;
            case R.id.btn_alipay:
                attach = "1";
                getAliPrepayId2();
                btnAlipay.setClickable(false);
                btnAlipay.setBackgroundDrawable(MyTools.setSelector(grayColor, grayColor, 10));
                break;
            case R.id.btn_other:
                break;
        }
    }

    /**
     * 支付宝支付
     */
    private void getAliPrepayId2() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", payBean.getOrderId()); //订单号
        params.put("body", "园博购票"); //商品描述
        params.put("mch_create_ip", NetworkUtils.getIPAddress(true)); //ip
        params.put("attach", String.format("{\"service\":\"%s\",\"payType\":\"1\"}", attachService)); //1支付宝 2微信
        params.put("service", "pay.alipay.native");//pay.alipay.native
        LogUtils.i(MyTools.jointURL(Constant.URL.BASEURL_HTML + "zhcomplaint/wft/pay", params));

        RetrofitHelper.getInstance().creat().getPrepay(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon tBaseData) throws IOException {
                        if (tBaseData.getRetCode().equals("0")) {
                            AliPayBean prepayBean = fromJson(tBaseData.getRetData(), AliPayBean.class);
                            if (prepayBean != null) {
                                setIntentData(prepayBean.getCode_img_url());
                                finish();
                            } else {
                                ToastUtils.showShort("不能进行支付!");
                            }
                        } else if (tBaseData.getRetCode().equals("-2")) {
                            ToastUtils.showShort(tBaseData.getRetData());
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }


    /**
     * 获取微信预支付
     */
    private void getWxPrepayId(PayBean orderBean) {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", orderBean.getOrderId()); //订单号
        params.put("body", "园博购票"); //商品描述
        params.put("mch_create_ip", NetworkUtils.getIPAddress(true)); //ip
        params.put("appid", "wxbb032cafa457f0a6");
        params.put("attach", String.format("{\"service\":\"%s\",\"payType\":\"3\"}", attachService)); //1支付宝 3微信
        params.put("service", "pay.weixin.raw.app");//unified.trade.pay  pay.weixin.raw.app
        LogUtils.i("", params);
        RetrofitHelper.getInstance().creat().getPrepay(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon tBaseData) throws IOException {
                        if (tBaseData.getRetCode().equals("0")) {
                            PrepayBean prepayBean = JsonUtil.fromJson(tBaseData.getRetData(), PrepayBean.class);
                            if (null != prepayBean) {
                                LogUtils.i(tBaseData.getRetData());
                                PrepayDetail prepayDetail = JsonUtil.fromJson(prepayBean.getPay_info(), PrepayDetail.class);
                                if (null != prepayDetail) {
                                    PayReq request = new PayReq();
                                    request.partnerId = prepayDetail.getPartnerid();
                                    request.prepayId = prepayDetail.getPrepayid();
                                    request.appId = prepayDetail.getAppid();
                                    request.packageValue = prepayDetail.getPackageX();
                                    request.nonceStr = prepayDetail.getNoncestr();
                                    request.timeStamp = prepayDetail.getTimestamp();
                                    request.sign = prepayDetail.getSign();
                                    IWXAPI iwxapi = WXAPIFactory.createWXAPI(PayActivity.this, null);
                                    iwxapi.registerApp("wxbb032cafa457f0a6"); //注册微信
                                    iwxapi.sendReq(request);
                                    finish();
                                }
                            } else {
                                ToastUtils.showShort("空");
                            }
                        } else if (tBaseData.getRetCode().equals("-2")) {
                            ToastUtils.showShort(tBaseData.getRetData());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }


    /**
     * startActivityForResult回调
     *
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_ALIPAY) {
            if (null != data) {
                if (data.getBooleanExtra("alipayResult", false)) {
                } else {
                    ToastUtils.showShort(data.getStringExtra("pay_result"));
                }
            }
        }

    }

    private boolean isQR;//判断是否为二维码

    /**
     * 显示支付宝支付二维码
     */
    @SuppressLint("SetJavaScriptEnabled")
    private void showAliPayQR(String url) {
        BottomSheetDialog bd = new BottomSheetDialog(this);
        WebView mWebView = new WebView(this);
        bd.setContentView(mWebView);
        mWebView.getSettings().setLoadsImagesAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().getAllowFileAccess();
        mWebView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        mWebView.getSettings().setUseWideViewPort(true);// 调整到适合webview大小
        mWebView.getSettings().setLoadWithOverviewMode(true);// 调整到适合webview大小
        mWebView.loadUrl(url);
        if (bd.isShowing()) {
            bd.dismiss();
        } else {
            bd.show();
        }
        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // TODO: 2017/8/30 长按识别二维码
                WebView.HitTestResult result = ((WebView) v).getHitTestResult();
                if (null == result)
                    return false;
                int type = result.getType();
                if (type == WebView.HitTestResult.IMAGE_TYPE) {
                    final String saveImgUrl = result.getExtra(); //图片链接
                    getBitmap(saveImgUrl);
                }
                return false;
            }
        });
    }

    private void getBitmap(String saveImgUrl) {
        ApiManager.mRetrofit.create(ApiService.class)
                .getDownLoadImage(saveImgUrl)
                .subscribeOn(Schedulers.newThread())
                .map(new Function<ResponseBody, Bitmap>() {
                    @Override
                    public Bitmap apply(@NonNull ResponseBody responseBody) throws Exception {
                        int a = responseBody.byteStream().available();
                        System.out.println(a + "==");
                        return BitmapFactory.decodeStream(responseBody.byteStream());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Bitmap>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Bitmap bitmap) {
                        int width = bitmap.getWidth();
                        int height = bitmap.getHeight();
                        int[] data = new int[width * height];
                        bitmap.getPixels(data, 0, width, 0, 0, width, height);
                        RGBLuminanceSource source = new RGBLuminanceSource(width, height, data);
                        BinaryBitmap bb = new BinaryBitmap(new HybridBinarizer(source));
                        QRCodeReader reader = new QRCodeReader();
                        try {
                            Result result = reader.decode(bb);
                            if (result != null) {
                                String sd = result.getText();
//                                Uri uri = Uri.parse("alipayqr://platformapi/startapp?saId=10000007&qrcode="+sd);
//                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//                                startActivity(intent);
//                                reqPayResult();
                                System.out.println(sd);
                            }
                        } catch (NotFoundException e) {
                            e.printStackTrace();
                        } catch (ChecksumException e) {
                            e.printStackTrace();
                        } catch (FormatException e) {
                            e.printStackTrace();
                        }


//                        saveMyBitmap(bitmap, "alipay");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 保存支付二维码
     *
     * @param bitmap 二维码的二进制图片
     * @param alipay 二维码的名称
     */
    private void saveMyBitmap(Bitmap bitmap, String alipay) {
        String savePath = MyTools.getSDPath() + Constant.PATH_PAY;
        File files = new File(savePath);//保存图片的文件夹
        File file = new File(files, alipay + ".jpg");//图片的具体路径
        FileOutputStream fos; //文件输出流
        try {
            if (!files.exists()) {
                files.mkdir();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        gotoActivity(HtmlActivity.class, true, bundle);
    }


}
