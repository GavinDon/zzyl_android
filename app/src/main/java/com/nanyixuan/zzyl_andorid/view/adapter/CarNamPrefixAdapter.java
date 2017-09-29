package com.nanyixuan.zzyl_andorid.view.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.nanyixuan.zzyl_andorid.R;

import java.util.List;

/**
 * description:
 * Created by liNan on 2017/9/21 11:20
 */

public class CarNamPrefixAdapter extends BaseQuickAdapter<String, com.chad.library.adapter.base.BaseViewHolder> {
    public CarNamPrefixAdapter(@LayoutRes int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_showcarnamprefix,item);
    }
}
