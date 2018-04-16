package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.SaleTicketBean;
import com.nanyixuan.zzyl_andorid.view.adapter.TicketClassAdapter;

import java.io.IOException;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class TicketClassActivity extends BaseActivity implements BaseQuickAdapter.OnItemClickListener {
    @BindView(R.id.rv_tickt_class)
    RecyclerView mRecyclerView;
    private TicketClassAdapter mAdapter;

    @Override
    protected int setLayout() {
        return R.layout.activity_ticket_class;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("园区门票");
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TicketClassAdapter(R.layout.adapter_tickt_class, null);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        reqTicketInfo();
    }

    private void reqTicketInfo() {
        RetrofitHelper.getInstance().creat().getTicketClassInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<SaleTicketBean>() {
                    @Override
                    public void onNext(SaleTicketBean ticketClassBean) throws IOException {
                        if (!ticketClassBean.isFlag()) {
                            ToastUtils.showShort(ticketClassBean.getMsg());
                        } else {
                            mAdapter.setNewData(ticketClassBean.getList());
                        }
                    }

                    @Override
                    public void onError(String msg) {

                    }
                }));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        SaleTicketBean.ListBean ticketInfo = (SaleTicketBean.ListBean) adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("ticketInfo", ticketInfo);
        gotoActivity(GardenTicketActivity.class, false, bundle);
    }
}
