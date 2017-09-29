package com.nanyixuan.zzyl_andorid.view.adapter;


import com.nanyixuan.zzyl_andorid.bean.DataBean;

/**
 * description:父布局Item点击监听接口
 * Created by liNan on 2017/9/16 15:18
 */
public interface ItemClickListener {
    /**
     * 展开子Item
     * @param bean
     */
    void onExpandChildren(DataBean bean);

    /**
     * 隐藏子Item
     * @param bean
     */
    void onHideChildren(DataBean bean);
}
