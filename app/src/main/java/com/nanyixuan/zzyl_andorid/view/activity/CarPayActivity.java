package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.AliPayBean;
import com.nanyixuan.zzyl_andorid.bean.PayBean;
import com.nanyixuan.zzyl_andorid.bean.PayValidateBean;
import com.nanyixuan.zzyl_andorid.bean.PrepayBean;
import com.nanyixuan.zzyl_andorid.bean.PrepayDetail;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

/**
 * 停车缴费界面
 */
public class CarPayActivity extends BaseActivity {
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

    @BindView(R.id.car_tv_pay_money)
    TextView tvMoney;
    @BindView(R.id.car_btn_wechat)
    Button carBtnWechat;
    @BindView(R.id.car_btn_alipay)
    Button carBtnAlipay;
    @BindView(R.id.car_btn_other)
    Button carBtnOther;

    private String attachService = "2"; //1 购票 2停车 3退款

    private PayBean payBean;
    private PayValidateBean payValidateBean; //缴费验证信息

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("停车缴费");
        initButton();
        Bundle bundle = getIntent().getExtras();
        payValidateBean = bundle.getParcelable("payInfo");
        if (payValidateBean != null) {
            payBean = new PayBean();
            payBean.setOrderId(payValidateBean.getId());
            payBean.setPayTotal(payValidateBean.getMoney());
            tvMoney.setText(payValidateBean.getMoney());
        }
    }


    /**
     * 按钮样式
     */
    private void initButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            carBtnWechat.setBackground(MyTools.setSelector(lightGreen, pressGreen, 10));
            carBtnAlipay.setBackground(MyTools.setSelector(lightBlue, pressBlue, 10));
            carBtnOther.setBackground(MyTools.setSelector(anotherColor, anotherPressColor, 10));
        } else {
            carBtnWechat.setBackgroundDrawable(MyTools.setSelector(lightGreen, pressGreen, 10));
            carBtnAlipay.setBackgroundDrawable(MyTools.setSelector(lightBlue, pressBlue, 10));
            carBtnOther.setBackgroundDrawable(MyTools.setSelector(anotherColor, anotherPressColor, 10));
        }

    }

    @OnClick({R.id.car_btn_wechat, R.id.car_btn_alipay, R.id.car_btn_other})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.car_btn_wechat: //微信
                if (payBean != null)
                    getWxPrepayId();
                carBtnWechat.setClickable(false);

                break;
            case R.id.car_btn_alipay:
                if (payBean != null)
                    getAliPrepayId();
                carBtnAlipay.setClickable(false);

                break;
            case R.id.car_btn_other:
                break;
        }
    }


    @Override
    protected int setLayout() {
        return R.layout.activity_car_pay;
    }


    /**
     * 支付宝支付
     */
    private void getAliPrepayId() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", payBean.getOrderId()); //订单号
        params.put("body", "停车缴费"); //商品描述
        params.put("mch_create_ip", NetworkUtils.getIPAddress(true)); //ip
        params.put("attach", String.format("{\"service\":\"%s\",\"payType\":\"1\"}", attachService)); //1支付宝 2微信
        params.put("service", "pay.alipay.native");//pay.alipay.native
        LogUtils.i(MyTools.jointURL("", params));
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
    private void getWxPrepayId() {
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", payBean.getOrderId()); //订单号
        params.put("body", "停车缴费"); //商品描述
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
                            PrepayBean prepayBean = fromJson(tBaseData.getRetData(), PrepayBean.class);
                            if (null != prepayBean) {
                                LogUtils.i(tBaseData.getRetData());
                                PrepayDetail prepayDetail = fromJson(prepayBean.getPay_info(), PrepayDetail.class);
                                if (null != prepayDetail) {
                                    PayReq request = new PayReq();
                                    request.partnerId = prepayDetail.getPartnerid();
                                    request.prepayId = prepayDetail.getPrepayid();
                                    request.appId = prepayDetail.getAppid();
                                    request.packageValue = prepayDetail.getPackageX();
                                    request.nonceStr = prepayDetail.getNoncestr();
                                    request.timeStamp = prepayDetail.getTimestamp();
                                    request.sign = prepayDetail.getSign();
                                    IWXAPI iwxapi = WXAPIFactory.createWXAPI(CarPayActivity.this, null);
                                    iwxapi.registerApp("wxbb032cafa457f0a6"); //注册微信
                                    iwxapi.sendReq(request);
                                    finish();
                                }
                            } else {
                                ToastUtils.showShort("空");
                            }
                        } else {
                            ToastUtils.showShort("不能进行支付!");
                        }

                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }

    private void setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("isCarPay",true);
        bundle.putString("url", url);
        gotoActivity(HtmlActivity.class, true, bundle);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.getInstance().remove("parkId");
    }
}
