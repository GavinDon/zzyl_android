package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.google.gson.reflect.TypeToken;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiService;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.AllOrderBean;
import com.nanyixuan.zzyl_andorid.bean.CreatRefundBean;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.RefundBean;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.utils.DisplayUtil;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.utils.ToolAES;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

public class PersonalActivity extends BaseActivity {
    @BindView(R.id.item_root)
    LinearLayout itemRoot;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("个人中心");
        addItem();
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean bLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN); //是否登陆
        if (bLogin) {
            String strUserInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
            if (!TextUtils.isEmpty(strUserInfo)) {
                LoginBean loginBean = JsonUtil.fromJson(strUserInfo, LoginBean.class);
                tvLogin.setText(loginBean.getUser().getUsername());
                tvLogin.setClickable(false);
            }
        } else {
            tvLogin.setText("未登陆");
            tvLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gotoActivity(LoginActivity.class);
                }
            });
        }
    }

    /**
     * 添加item
     */
    private void addItem() {
        String[] content = {"我的足迹", "我的消息", "园林服务", "我的门票",  "设置","退出登陆"};
        int[] icon = {R.mipmap.foot, R.mipmap.message, R.mipmap.server, R.mipmap.ticket, R.mipmap.setting, R.mipmap.loginout};

        for (int i = 0; i < content.length; i++) {
            //获取子控件
            View itemView = LayoutInflater.from(this).inflate(R.layout.personal_item, itemRoot, false);
            LinearLayout item = (LinearLayout) itemView.findViewById(R.id.ll_item);
            TextView tvContent = (TextView) itemView.findViewById(R.id.tv_content); // item文字
            View space = itemView.findViewById(R.id.space); //item上面16dp高度的分割线
            //当子view显示到第三个的时候 显示头部分割线
            if (i == 3 || i == 5) {
                space.setVisibility(View.VISIBLE);
            } else {
                space.setVisibility(View.GONE);
            }
            item.setId(i); //为item设置标记
            //设置textView 左边的小图标
            Drawable d = ContextCompat.getDrawable(this, icon[i]);
            d.setBounds(new Rect(0, 0, d.getMinimumWidth(), d.getMinimumHeight()));
            tvContent.setCompoundDrawables(d, null, null, null);
            tvContent.setCompoundDrawablePadding(DisplayUtil.dip2px(this, 16));
            tvContent.setText(content[i]); // 设置文字
            itemRoot.addView(itemView); //添加到LinearLayout中
            //给每个item 添加点击事件
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case 0:
                            setIntentData(Constant.URL.MY_STEP);//我的足迹
                            break;
                        case 1:
                            setIntentData(Constant.URL.MY_MSG);//我的消息
                            break;
                        case 2:
                            gotoActivity(ServiceActivity.class);//园林服务
                            break;
                        case 3:
                            gotoActivity(OrderListActivity.class);
                            break;
                        case 4:
//                            setIntentData(Constant.URL.MY_TICKET);
//                            ToastUtils.showShort("微信支付正在提交认证，暂不能查看");//车票订单
                            break;
                        case 5: //退出登陆
                            boolean isLogin= SPUtils.getInstance().getBoolean(Constant.SP_LOGIN);
                            if (isLogin){
                                   SPUtils.getInstance().clear();
                                ToastUtils.showShort("您已退出登陆");
                            }else {
                                ToastUtils.showShort("您还未登陆");
                            }
                            break;
                        case 6:
                            returnTicket();//退票
                            break;
                    }
                }
            });

        }

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_personal;
    }

    private void setIntentData(String url) {
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        Intent intent = new Intent(this, UrlContentActivity.class);
        intent.putExtra("data", bundle);
        startActivity(intent);
    }

    /**
     * 退票
     */
    private void returnTicket() {
        findAllOrder();
    }

    private List<AllOrderBean> retLst; //订单集合

    /**
     * 查找所有订单
     */
    private void findAllOrder() {
        //查找订单
        ApiManager.mRetrofit.create(ApiService.class).getFindOrder("10001")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RespCommon>() {
                    @Override
                    public void accept(RespCommon respCommon) throws Exception {
                        if (null != respCommon)
                            if (respCommon.getRetCode().equals("0")) {
                                retLst = fromJson(respCommon.getRetData(), new TypeToken<List<AllOrderBean>>() {
                                }.getType());
                                for (int i = 0; i < retLst.size(); i++) {
                                    String ticketStatus = retLst.get(i).getTicket_status();
                                    String status = retLst.get(i).getStatus();
                                    if (status.equals("1") && ticketStatus.equals("1")) {
                                        createRefund(retLst.get(i));
                                    }
                                }
                            }
                    }
                });

        RetrofitHelper.getInstance().creat().getFindOrder("")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (null != respCommon)
                            if (respCommon.getRetCode().equals("0")) {
                                retLst = fromJson(respCommon.getRetData(), new TypeToken<List<AllOrderBean>>() {
                                }.getType());
                                for (int i = 0; i < retLst.size(); i++) {
                                    String ticketStatus = retLst.get(i).getTicket_status();
                                    String status = retLst.get(i).getStatus();
                                    if (status.equals("1") && ticketStatus.equals("1")) {
                                        createRefund(retLst.get(i));
                                    }
                                }
                            }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }
    /**
     * 创建退单
     *
     * @param orderBean
     */
    private void createRefund(AllOrderBean orderBean) {

        Map<String, String> params = new HashMap();
        params.put("userId", orderBean.getUser_id());
        params.put("orderId", orderBean.getId());
        params.put("identityCode", orderBean.getIdentity_code());
        params.put("ticketCount", String.valueOf(orderBean.getTicket_count()));
        params.put("refundAmt", ToolAES.encode(orderBean.getReal_sum()));
        params.put("transactionId", orderBean.getTransaction_id().toString());
        LogUtils.i(MyTools.jointURL(Constant.URL.BASEURL_HTML, params));
        RetrofitHelper.getInstance().creat().getCreateRefund(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon != null)
                            if (respCommon.getRetCode().equals("0")) {
                                if (!TextUtils.isEmpty(respCommon.getRetData())) {
                                    CreatRefundBean b = JsonUtil.fromJson(respCommon.getRetData(), CreatRefundBean.class);
//                                    refund(b); //退款
                                    refundDone(b);
                                }
                                System.out.println(respCommon.getRetData());
                            }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));

    }

    /**
     * 退款
     *
     * @param mCreateRefundBean
     */
    private void refund(CreatRefundBean mCreateRefundBean) {
        Map<String, String> params = new HashMap();
        params.put("service", "unified.trade.refund");
        params.put("out_trade_no", mCreateRefundBean.getOrder_id()); //商户订单号 order_id
        params.put("transaction_id", mCreateRefundBean.getTransaction_id()); //平台订单号
        params.put("out_refund_no", mCreateRefundBean.getId()); //商户退款单号
        params.put("total_fee", ToolAES.decode(mCreateRefundBean.getRefund_AMT_sum())); //总金额  需要加密
        params.put("refund_fee", ToolAES.decode(mCreateRefundBean.getRefund_AMT())); //退款金额  需要加密
        params.put("isEncode", "1"); // 0 未加密 1已加密
        ApiManager.mRetrofit.create(ApiService.class)
                .getRefund(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<RespCommon>() {
                    @Override
                    public void accept(RespCommon respCommon) throws Exception {
                        System.out.println(respCommon);
                        if (respCommon != null) {
                            if (respCommon.getRetCode().equals("0")) {
                                String resp = respCommon.getRetData();
                                if (!TextUtils.isEmpty(resp)) {
                                    RefundBean refundBean = JsonUtil.fromJson(resp, RefundBean.class);
//                                refundDone( refundBean);
                                    System.out.println(resp);
                                }
                            }
                        }
                    }
                });
    }

    /**
     * 调用退款成功接口
     *
     * @param creatRefundBean
     */
    private void refundDone(CreatRefundBean creatRefundBean) {

        //返回0退款退票成功 返回-1退票失败 返回-2已退票
        Map<String, String> params = new HashMap();
        params.put("transaction_id", creatRefundBean.getTransaction_id()); //平台订单号
        params.put("out_trade_no", creatRefundBean.getOrder_id()); //商户订单号
        params.put("out_refund_no", creatRefundBean.getId()); //商户退款单号
        params.put("refund_id", "12345"); //威富通平台退款单号
        RetrofitHelper.getInstance().creat()
                .getRefundDone(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon != null) {
                            if (respCommon.getRetCode().equals("0")) {
                                String resp = respCommon.getRetData();
                                if (!TextUtils.isEmpty(resp)) {
                                    ToastUtils.showShort(resp);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    private void createRefunds(AllOrderBean orderBean) {

        final Map<String, String> params = new HashMap();
        params.put("userId", orderBean.getUser_id());
        params.put("orderId", orderBean.getId());
        params.put("identityCode", orderBean.getIdentity_code());
        params.put("ticketCount", String.valueOf(orderBean.getTicket_count()));
        params.put("refundAmt", ToolAES.encode(orderBean.getReal_sum()));
        params.put("transactionId", orderBean.getTransaction_id().toString());
        LogUtils.i(MyTools.jointURL(Constant.URL.BASEURL_HTML, params));
        RetrofitHelper.getInstance().creat().getCreateRefund(params)
                .flatMap(new Function<RespCommon, ObservableSource<RespCommon>>() {
                    @Override
                    public ObservableSource<RespCommon> apply(@NonNull RespCommon respCommon) throws Exception {
                        if (respCommon != null)
                            if (respCommon.getRetCode().equals("0")) {
                                if (!TextUtils.isEmpty(respCommon.getRetData())) {
                                    CreatRefundBean b = JsonUtil.fromJson(respCommon.getRetData(), CreatRefundBean.class);
                                    Map<String, String> params = new HashMap();
                                    params.put("transaction_id", b.getTransaction_id()); //平台订单号
                                    params.put("out_trade_no", b.getOrder_id()); //商户订单号
                                    params.put("out_refund_no", b.getId()); //商户退款单号
                                    params.put("refund_id", "12345"); //威富通平台退款单号
                                    return RetrofitHelper.getInstance().creat().getRefundDone(params);
                                }
                            }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, false, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon != null) {
                            if (respCommon.getRetCode().equals("0")) {
                                String resp = respCommon.getRetData();
                                if (!TextUtils.isEmpty(resp)) {
                                    ToastUtils.showShort(resp);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));

    }

}

