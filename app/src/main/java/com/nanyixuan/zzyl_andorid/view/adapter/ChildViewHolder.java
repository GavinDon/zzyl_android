package com.nanyixuan.zzyl_andorid.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.bean.DataBean;


/**
 * description:子viewHolder
 * Created by liNan on 2017/9/16 15:18
 */
public class ChildViewHolder extends BaseViewHolder {

    private Context mContext;
    private View view;
    private LinearLayout ll_child;

    public ChildViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.view = itemView;
    }

    public void bindView(DataBean dataBean, int pos) {
        ll_child = (LinearLayout) view.findViewById(R.id.ll_child);
        addNotice(dataBean);
    }

    /**
     * 购票须知
     */
    private void addNotice(DataBean dataBean) {
        String itemNotice[] = dataBean.getChildLeftTxt().split("\n");
        ll_child.removeAllViews();
        for (int i = 1; i <= itemNotice.length; i++) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_notice, ll_child, false);
            TextView tvNumber = (TextView) view.findViewById(R.id.tv_number);
            TextView tvNotice = (TextView) view.findViewById(R.id.tv_notice);
            tvNumber.setText(i + "、");
            tvNotice.setText(itemNotice[i - 1]);
            ll_child.addView(view);
        }
    }
}
