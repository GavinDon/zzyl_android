package com.nanyixuan.zzyl_andorid.view.activity.traffic;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.view.adapter.TabWithVpAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * description:交通信息
 * Created by liNan on 2017/7/31 15:22
 */
public class TrafficInfoActivity extends BaseActivity {
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;

    private List<Fragment> mFragmentLst = new ArrayList<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("交通信息");
        mFragmentLst.add(new PublicTrafficFragment());
        mFragmentLst.add(new DriverFragment());
        mViewPager.setAdapter(new TabWithVpAdapter(this.getSupportFragmentManager(),mFragmentLst));
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
        //设置tab属性
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.getTabAt(0).select(); //设置第一个选中状态tab
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_traffic_info;
    }
}
