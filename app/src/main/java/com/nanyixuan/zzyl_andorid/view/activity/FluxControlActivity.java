package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;

/**
 * description: 人流监控
 * Created by liNan on 2017/7/31 9:22
 */
public class FluxControlActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("人流监控");

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_flux_control;
    }
}
