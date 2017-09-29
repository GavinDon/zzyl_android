package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.FindParksBean;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * description: 停车场预约信息适配器
 * Created by liNan on 2017/9/8 15:37
 */

public class SubscriberCarInfoAdapter extends BaseQuickAdapter<FindParksBean, BaseViewHolder> {
    public SubscriberCarInfoAdapter(@LayoutRes int layoutResId, @Nullable List<FindParksBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, FindParksBean item) {
        helper.setText(R.id.ada_tv_subscriber_retain, String.valueOf(item.getBespeaknum()))
                .setText(R.id.ada_tv_subscriber_total,"/"+item.getTotalnum())
                .setText(R.id.ada_tv_title,item.getParkname())
                .setText(R.id.ada_tv_detail,item.getParklocation())
                .addOnClickListener(R.id.ada_tv_subscriber);
    }
}
