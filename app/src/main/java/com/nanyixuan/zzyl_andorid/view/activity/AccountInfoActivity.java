package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;

import butterknife.BindView;

/**
 * 帐号信息
 */
public class AccountInfoActivity extends BaseActivity {
    @BindView(R.id.tv_account)
    TextView tvAccount;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("个人信息");
        String userInfo =com.blankj.utilcode.util.SPUtils.getInstance().getString(Constant.SP_USER_INFO);
        if (!TextUtils.isEmpty(userInfo)) {
            LoginBean bean = JsonUtil.fromJson(userInfo, LoginBean.class);
            tvAccount.setText("帐号:"+bean.getUser().getUsername());
        }
        addItem();
    }

    private void addItem() {


    }

    @Override
    protected int setLayout() {
        return R.layout.activity_account_info;
    }


}
