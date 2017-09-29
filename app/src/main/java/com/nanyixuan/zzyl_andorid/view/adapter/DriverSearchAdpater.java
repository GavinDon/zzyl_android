package com.nanyixuan.zzyl_andorid.view.adapter;

import android.content.Context;

import com.zhy.adapter.abslistview.CommonAdapter;
import com.zhy.adapter.abslistview.ViewHolder;

import java.util.List;

/**
 * description:
 * Created by liNan on 2017/8/15 15:18
 */

public class DriverSearchAdpater extends CommonAdapter {
    public DriverSearchAdpater(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder viewHolder, Object item, int position) {
        viewHolder.setText(android.R.id.text1,item.toString());
    }
}
