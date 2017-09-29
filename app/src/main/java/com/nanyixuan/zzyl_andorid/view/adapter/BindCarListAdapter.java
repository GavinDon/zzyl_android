package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.BindCarListBean;

import java.util.List;

/**
 * description: 车牌列表适配器
 * Created by liNan on 2017/9/8 11:13
 */

public class BindCarListAdapter extends BaseQuickAdapter<BindCarListBean, BaseViewHolder> {

    public BindCarListAdapter(@LayoutRes int layoutResId, @Nullable List<BindCarListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BindCarListBean item) {
        String itemCheck = item.getIs_check();
        helper.setText(R.id.item_tv_bindcar_number, item.getCar_num())
                .addOnClickListener(R.id.item_iv_close);
        if (itemCheck.equals("1")) {
            helper.getView(R.id.item_carnum_standard).setVisibility(View.VISIBLE);
        } else {
            helper.getView(R.id.item_carnum_standard).setVisibility(View.INVISIBLE);
        }

    }
}
