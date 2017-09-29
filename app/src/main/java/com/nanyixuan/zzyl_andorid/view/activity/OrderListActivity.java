package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.reflect.TypeToken;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.AllOrderBean;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.OrderDetailBean;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.view.adapter.OrderListAdapter;
import com.nanyixuan.zzyl_andorid.widgets.SimpleDialog;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

public class OrderListActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_order_list)
    RecyclerView mRecyclerView;
    private List<AllOrderBean> retLst; //订单集合
    private LoginBean loginBean;
    private OrderListAdapter mAdapter;
    private SimpleDialog mSimpleDialog; //退款对话框
    private BottomSheetDialog dialog; //底部对话框
    private SubmitButton btnReturn;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("我的门票");
        initRv();
    }

    private void initRv() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new OrderListAdapter(R.layout.adapter_order_list, null);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setHasFixedSize(true);
        mAdapter.setOnItemChildClickListener(this);
//        mAdapter.setOnItemClickListener(this);
        reqOrderList();
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_order_list;
    }

    /**
     * 请求退款
     */

    private void createRefunds(AllOrderBean orderBean, final int position) {
        final Map<String, String> params = new HashMap();
        params.put("userId", orderBean.getUser_id());
        params.put("orderId", orderBean.getId());
        params.put("identityCode", orderBean.getIdentity_code());
        params.put("ticketCount", String.valueOf(orderBean.getTicket_count()));
        params.put("transactionId", orderBean.getTransaction_id().toString());
        LogUtils.i(MyTools.jointURL(Constant.URL.BASEURL_HTML, params));
        RetrofitHelper.getInstance().creat().getCreateRefund(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon != null) {
                            if (respCommon.getRetCode().equals("0")) {
                                String resp = respCommon.getRetData();
                                if (!TextUtils.isEmpty(resp)) {
                                    ToastUtils.showLong(resp);
                                    //更新界面为退款状态
                                    mAdapter.getItem(position).setStatus("2");
                                    mAdapter.notifyItemChanged(position, mAdapter.getItemCount() - position);
                                } else {
                                    ToastUtils.showShort("退款失败。");
                                }
                            } else {
                                ToastUtils.showShort(respCommon.getRetData());
                            }
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        reqOrderList();
    }

    /**
     * 查询所有订单
     */
    private void reqOrderList() {
        boolean isLogin = SPUtils.getInstance().getBoolean(Constant.SP_LOGIN);
        if (isLogin) {
            String strLoginInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
            loginBean = JsonUtil.fromJson(strLoginInfo, LoginBean.class);
        }
        if (loginBean != null) {
            RetrofitHelper.getInstance().creat().getFindOrder(String.valueOf(loginBean.getUser().getUsername()))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                        @Override
                        public void onNext(RespCommon respCommon) {
                            if (null != respCommon)
                                if (respCommon.getRetCode().equals("0")) {
                                    retLst = fromJson(respCommon.getRetData(), new TypeToken<List<AllOrderBean>>() {
                                    }.getType());
                                    if (retLst != null) {
                                        mAdapter.setNewData(retLst);
                                    }else {
                                        ToastUtils.showShort("您还未创建订单");
                                    }
                                }
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    }));

        } else {
            gotoActivity(LoginActivity.class);
        }
    }

    @Override
    public void onItemChildClick(final BaseQuickAdapter adapter, View view, final int position) {
        final AllOrderBean allOrderBean = (AllOrderBean) adapter.getData().get(position);
        switch (view.getId()) {
            case R.id.btn_return_ticket:
                //退票
                mSimpleDialog = new SimpleDialog(this);
                btnReturn = (SubmitButton) adapter.getViewByPosition(mRecyclerView, position, R.id.btn_return_ticket);
                mSimpleDialog.setTitle("")
                        .setContentText("您正在进行退款操作？")
                        .showCancelButton(true)
                        .setConfirmClickListener(new SimpleDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SimpleDialog simpleDialog) {
                                createRefunds(allOrderBean, position);
                                mSimpleDialog.dismiss();
                            }
                        }).setCancelClickListener(new SimpleDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SimpleDialog simpleDialog) {
                        mSimpleDialog.dismiss();
                    }
                });
                break;
            case R.id.btn_query:
                //查询订单信息
                String orderId = allOrderBean.getId();
                reqQueryOrderDetail(orderId);
                break;
            case R.id.btn_deleter:
                //删除订单
                deleterOrder(allOrderBean.getId(), position);
                break;
        }

    }

    /**
     * 删除订单
     *
     * @param orderId 要删除的订单号
     */
    private void deleterOrder(String orderId, final int pos) {
        RetrofitHelper.getInstance().creat().getCancleOrder(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon response) throws IOException {
                        if (response.getRetCode().equals("0")) {
                            mAdapter.remove(pos);
                            mAdapter.notifyItemRemoved(pos);
                        }

                    }
                    @Override
                    public void onError(String msg) {

                    }
                }));
    }


    /**
     * 查询订单详情
     */
    private void reqQueryOrderDetail(String orderId) {
        RetrofitHelper.getInstance().creat().getOrderDetail(orderId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) throws IOException {
                        if (respCommon.getRetCode().equals("0")) {
                            OrderDetailBean orderDetailBean = JsonUtil.fromJson(respCommon.getRetData(), OrderDetailBean.class);
                            LogUtils.i(respCommon.getRetData());
                            createOrderDialog(orderDetailBean);
                        } else {
                            ToastUtils.showShort("订单异常");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }

    /**
     * 弹出订单详情信息
     *
     * @param orderDetailBean
     */
    private void createOrderDialog(final OrderDetailBean orderDetailBean) {
        dialog = new BottomSheetDialog(this);
        View v = LayoutInflater.from(this).inflate(R.layout.dialog_order_detail, null, false);
        dialog.setContentView(v);
        if (dialog.isShowing()) {
            dialog.dismiss();
        } else {
            dialog.show();
        }
        TextView tvOrderNum = (TextView) v.findViewById(R.id.dialog_tv_ordernum_str);//订单号
        TextView tvOrderTime = (TextView) v.findViewById(R.id.dialog_tv_orderTime_str);//支付时间
        TextView tvOrderMoney = (TextView) v.findViewById(R.id.dialog_tv_orderMoney_str);//支付金额
        TextView tvOrderState = (TextView) v.findViewById(R.id.dialog_tv_orderState_str);//出票状态
        TextView tvOrderPayState = (TextView) v.findViewById(R.id.dialog_tv_orderpayState_str);//支付状态
        TextView tvOrderIdcard = (TextView) v.findViewById(R.id.dialog_tv_orderIdCard_str);
        TextView tvOrderEndTime = (TextView) v.findViewById(R.id.dialog_tv_orderEndTime_str);
        TextView tvOrderTips = (TextView) v.findViewById(R.id.dialog_tv_tips);//出票失败提示语
        SubmitButton btnPay = (SubmitButton) v.findViewById(R.id.btn_pay);
        if (orderDetailBean != null) {
            tvOrderMoney.setText(String.valueOf(Float.parseFloat(orderDetailBean.getReal_sum()) / 100) + "元");//和服务器交互均以分为单位 所以此处显示元，换算一下
            tvOrderTime.setText(orderDetailBean.getSale_time());
            tvOrderNum.setText(orderDetailBean.getId());
            tvOrderIdcard.setText(orderDetailBean.getIdentity_code());
            tvOrderEndTime.setText(orderDetailBean.getEnd_date());
            tvOrderPayState.setText(MyTools.getOrderState(orderDetailBean.getStatus())); //支付状态

            //ticket_status 0未出票 1已出票
            if (orderDetailBean.getTicket_status().equals("1")) {
                tvOrderState.setText("出票成功");
                tvOrderTips.setVisibility(View.GONE);
            } else {
                tvOrderState.setText("出票失败");
                tvOrderTips.setVisibility(View.VISIBLE);
            }
            //控制是否显示支付按钮
            if (orderDetailBean.getStatus().equals("0")) {
                //未支付时显示支付按钮
                btnPay.setVisibility(View.VISIBLE);
            } else {
                btnPay.setVisibility(View.GONE);
            }

            btnPay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("orderDetailInfo", orderDetailBean);
                    bundle.putString("page", "2");
                    gotoActivity(PayActivity.class, false, bundle);
                }
            });

        }

    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        AllOrderBean bean = (AllOrderBean) adapter.getData().get(position);
        if (bean != null) {
//            if (bean.getStatus().equals("0")) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("orderBean", bean);
//                bundle.putString("page", "1");
//                gotoActivity(PayActivity.class, false, bundle);
//            } else {
                reqQueryOrderDetail(bean.getId());
//            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSimpleDialog != null && mSimpleDialog.isShowing()) {
            mSimpleDialog.dismiss();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
