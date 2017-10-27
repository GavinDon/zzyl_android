package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.widget.CompoundButton;

import com.blankj.utilcode.util.SPUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.widgets.SwitchButton;

import butterknife.BindView;

/**
 * description:设置页面
 * Created by liNan on 2017/10/25 14:04
 */

public class SettingActivity extends BaseActivity implements CompoundButton.OnCheckedChangeListener {
    @BindView(R.id.sb_gps)
    SwitchButton sbGps;
    @BindView(R.id.sb_msg_push)
    SwitchButton sbMsgPush;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("设置");
        sbGps.setOnCheckedChangeListener(this);
        sbMsgPush.setOnCheckedChangeListener(this);
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_setting;
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.sb_msg_push:
                if (isChecked) {
                    SPUtils.getInstance().put(Constant.SP_MSG_PUSH, isChecked);
                }
                break;
            case R.id.sb_gps:
                if (isChecked) {
                    SPUtils.getInstance().put(Constant.SP_GPS, isChecked);
                }
                break;
        }
    }
}
