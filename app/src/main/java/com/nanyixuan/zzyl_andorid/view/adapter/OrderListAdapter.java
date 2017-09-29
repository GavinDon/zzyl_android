package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.AllOrderBean;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.widgets.SubmitButton;

import java.util.List;

/**
 * description: 订单列表
 * Created by liNan on 2017/9/21 20:57
 */

public class OrderListAdapter extends BaseQuickAdapter<AllOrderBean, com.chad.library.adapter.base.BaseViewHolder> {


    public OrderListAdapter(@LayoutRes int layoutResId, @Nullable List<AllOrderBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, AllOrderBean item) {
        SubmitButton btnQuery = helper.getView(R.id.btn_query);//查看订单按钮
        SubmitButton btnReturn = helper.getView(R.id.btn_return_ticket);//退票按钮
        SubmitButton btnDeleter=helper.getView(R.id.btn_deleter);

        //当支付状态为
        if (item.getStatus().equals("0")) {
            //只有支付状态为支付时或者未支付时
            btnReturn.setVisibility(View.GONE);
            btnQuery.setVisibility(View.VISIBLE);
            btnDeleter.setVisibility(View.VISIBLE);
        } else if (item.getStatus().equals("1")) {
            btnReturn.setVisibility(View.VISIBLE);
            btnQuery.setVisibility(View.VISIBLE);
            btnDeleter.setVisibility(View.GONE);
        } else  {
            btnReturn.setVisibility(View.GONE);
            btnQuery.setVisibility(View.GONE);
            btnDeleter.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_order_num_str, item.getId())
                .setText(R.id.tv_order_goods_str, item.getTicket_type_name())
                .setText(R.id.tv_pay_state, MyTools.getOrderState(item.getStatus()))
                .setText(R.id.tv_order_time_str, item.getSale_time() + "")
                .setText(R.id.tv_order_idCard_str,item.getIdentity_code())
                .setText(R.id.tv_order_goTime_str,item.getEnd_date())
                .setText(R.id.tv_order_money_str, String.valueOf(Float.parseFloat(item.getReal_sum()) / 100))
                .addOnClickListener(R.id.btn_query)
                .addOnClickListener(R.id.btn_pay)
                .addOnClickListener(R.id.btn_return_ticket)
                .addOnClickListener(R.id.btn_deleter);
    }
}
