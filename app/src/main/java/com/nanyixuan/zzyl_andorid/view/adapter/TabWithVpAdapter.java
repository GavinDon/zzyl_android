package com.nanyixuan.zzyl_andorid.view.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**导游服务TAB与Viewpager适配器
 * Created by linan   on 2017/3/9.
 */

public class TabWithVpAdapter extends FragmentPagerAdapter {
    private String[] title = {"公共交通", "驾车"};
    private List<Fragment> views;

    public TabWithVpAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);
        this.views = views;
    }

    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
