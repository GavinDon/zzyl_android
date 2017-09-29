package com.nanyixuan.zzyl_andorid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.FragmentUtils;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;

/**
 * description:
 * Created by liNan on 2017/7/25 9:38
 */

public abstract class BaseFragment extends Fragment {
    protected View self;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (this.self == null) {
            this.self = inflater.inflate(this.setLayout(), container, false);

        }
        if (this.self.getParent() != null) {
            ViewGroup parent = (ViewGroup) this.self.getParent();
            parent.removeView(this.self);
        }
        ButterKnife.bind(this, this.self);
        this.initViews(this.self, savedInstanceState);
        return this.self;
    }

    public abstract int setLayout();

    public abstract void initViews(View self, Bundle savedInstanceState);

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            FragmentUtils.popFragment(getFragmentManager());
        }
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    public void gotoActivity(Class<?> clazz) {
        Intent intent = new Intent(this.getActivity(), clazz);
        startActivity(intent);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
