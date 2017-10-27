package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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
import com.nanyixuan.zzyl_andorid.bean.BookParkBean;
import com.nanyixuan.zzyl_andorid.bean.FindParksBean;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.RespCommon;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.view.adapter.SubscriberCarInfoAdapter;
import com.nanyixuan.zzyl_andorid.widgets.SimpleDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindColor;
import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.nanyixuan.zzyl_andorid.utils.JsonUtil.fromJson;

/**
 * 预约车位
 */
public class SubscriberCarActivity extends BaseActivity implements BaseQuickAdapter.OnItemChildClickListener {
    @BindView(R.id.rv_subscriber)
    RecyclerView mRecyclerView;

    private SubscriberCarInfoAdapter mAdapter;
    private LoginBean loginBean;
    private String strCarNum;//绑定的车牌号
    private SimpleDialog dialog;
    private List<BookParkBean> bookParkBeanList;
    @BindColor(R.color.gray)
    int gray;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Bundle bundle = getIntent().getExtras();
        strCarNum = bundle.getString("carNum");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mAdapter = new SubscriberCarInfoAdapter(R.layout.adapter_subsriber, null);
        mRecyclerView.setAdapter(mAdapter);
        String userInfo = SPUtils.getInstance().getString(Constant.SP_USER_INFO);
        loginBean = JsonUtil.fromJson(userInfo, LoginBean.class);
        reqTokenAndParkInfo();
        mAdapter.setOnItemChildClickListener(this);

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_subscriber_car;
    }

    /**
     * 获取停车场信息
     */
    private void reqTokenAndParkInfo() {
        RetrofitHelper.getInstance().creat().getFindParks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon.getRetCode().equals("0")) {
                            List<FindParksBean> findParksBean = fromJson(respCommon.getRetData(), new TypeToken<List<FindParksBean>>() {
                            }.getType());
                            mAdapter.setNewData(findParksBean);
                        } else if (respCommon.getRetCode().equals("112")) {
                            //未获取到token
                            ToastUtils.showShort("网络繁忙,请稍后再试");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);

                    }
                }));
    }


    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, final View view, int position) {

        final FindParksBean findParksBean = (FindParksBean) adapter.getItem(position);
        dialog = new SimpleDialog(this);
        dialog.setTitle("预约车位")
                .showConfirmButton(true)
                .showCancelButton(true)
                .setContentText("您正在进行预约车位");
        dialog.setConfirmClickListener(new SimpleDialog.OnSweetClickListener() {
            @Override
            public void onClick(SimpleDialog simpleDialog) {
                reqSubscriber(findParksBean);
                view.setBackgroundColor(gray);
                view.setClickable(false);
                dialog.dismiss();
            }
        });

    }

    /**
     * 预约请求
     */
    private void reqSubscriber(final FindParksBean findParksBean) {
        final Map<String, String> params = new HashMap<>();
        params.put("parkid", String.valueOf(findParksBean.getParkid()));
        params.put("carnum", strCarNum);
        params.put("userid", String.valueOf(loginBean.getUser().getUsername()));
        RetrofitHelper.getInstance().creat().getBookPark(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<RespCommon>() {
                    @Override
                    public void onNext(RespCommon respCommon) {
                        if (respCommon.getRetCode().equals("0")) {
                            bookParkBeanList = JsonUtil.fromJson(respCommon.getRetData(), new TypeToken<List<BookParkBean>>() {
                            }.getType());
                            if (bookParkBeanList != null && bookParkBeanList.size() > 0) {
                                String timeOut = bookParkBeanList.get(0).getBespeaktimeout();
                                //预约成功刷新剩余车辆数据
                                int iBespeakNum= findParksBean.getRealnum();
                                findParksBean.setRealnum(iBespeakNum-1);
                                mAdapter.notifyDataSetChanged();
                                SimpleDialog simpleDialog = new SimpleDialog(SubscriberCarActivity.this);
                                simpleDialog.showConfirmButton(true)
                                        .setTitle("预约完成")
                                        .setContentText(String.format("预约成功,请在%s前入场", timeOut))
                                        .setConfirmClickListener(new SimpleDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SimpleDialog simpleDialog) {
                                                simpleDialog.dismiss();
                                            }
                                        });
                            } else {
                                ToastUtils.showShort("预约失败,请关闭当前页面重新打开");
                            }
                        } else {
                            ToastUtils.showShort(respCommon.getRetData());
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }

}
