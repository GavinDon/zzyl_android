package com.nanyixuan.zzyl_andorid.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.utils.ActivityStackControlUtil;
import com.umeng.analytics.MobclickAgent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * description:baseActivity
 * Created by liNan on 2017/7/25 9:30
 */

public abstract class BaseActivity extends AppCompatActivity {

    //    private Toolbar mToolbar;
    @BindView(R.id.app_txt)
    TextView mTitle;
    public String mClassName;
    private Unbinder mUnbinder;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(this.setLayout());
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);// 隐藏软键盘
        mUnbinder = ButterKnife.bind(this);
        ActivityStackControlUtil.add(this);
//        initToolbar();
        this.initView(savedInstanceState);
        mClassName = this.getClass().getSimpleName();
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int setLayout();

    /**
     * 初始化toolbar
     */
    protected void initToolbar() {
//        mToolbar = (Toolbar) findViewById(R.id.app_toolbar);
//        mTitle = (TextView) findViewById(R.id.app_txt);
//        mToolbar.setTitle("");
//        setSupportActionBar(mToolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }

//    public Toolbar getToolbar() {
//        return mToolbar;
//    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        if (null != title) {
            mTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    /**
     * 修改为返回箭头带文字的监听事件
     */
    @OnClick(R.id.tv_back)
    public void goBack() {
        onBackPressed();
    }


    /**
     * 打开Activity并不需要传值
     */
    public void gotoActivity(Class<?> clz, boolean isClose) {
        gotoActivity(clz, isClose, null);

    }

    /**
     * 打开Activity并传值跳转成功关闭当前Activity
     *
     * @param clz     需要跳转到activity
     * @param isClose 是否需要关闭当前Activity
     * @param bundle  传递bundle数据
     */
    public void gotoActivity(Class<?> clz, boolean isClose, Bundle bundle) {
        Intent mIntent = new Intent(this, clz);
        if (bundle != null) mIntent.putExtras(bundle);
        startActivity(mIntent);
        if (isClose) {
            this.finish();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);//统计时长,也就是Session时长
        MobclickAgent.onPageStart(this.getClass().getSimpleName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        MobclickAgent.onPageEnd(this.getClass().getSimpleName());
    }

    public void gotoActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
        }
    }
}
