package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.SaleTicketBean;

import java.util.List;

/**
 * description:票样信息适配器
 * Created by liNan on 2017/9/21 14:31
 */

public class TicketClassAdapter extends BaseQuickAdapter<SaleTicketBean.ListBean, com.chad.library.adapter.base.BaseViewHolder> {

    public TicketClassAdapter(@LayoutRes int layoutResId, @Nullable List<SaleTicketBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SaleTicketBean.ListBean item) {
        SimpleDraweeView draweeView = helper.getView(R.id.iv_ticket_class);
        draweeView.setImageURI(item.getImgurl());
    }
}
