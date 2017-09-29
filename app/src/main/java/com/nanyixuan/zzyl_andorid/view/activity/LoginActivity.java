package com.nanyixuan.zzyl_andorid.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.MyApplication;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.utils.SPUtils;
import com.nanyixuan.zzyl_andorid.utils.ToolAES;
import com.nanyixuan.zzyl_andorid.view.fragment.MainContentFragment;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends BaseActivity {


    @BindView(R.id.et_login_account)
    AppCompatEditText etLoginAccount;
    @BindView(R.id.et_login_psw)
    AppCompatEditText etLoginPsw;
    @BindView(R.id.btn_login)
    AppCompatButton btnLogin;
    @BindView(R.id.tv_forget_psw)
    TextView tvForgetPsw;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setTitle("登录");
        initStyle();
        //设置帐号
        String account = (String) SPUtils.get(MyApplication.appContext, Constant.SP_ACCOUNT, "");
        if (!TextUtils.isEmpty(account)) {
            etLoginAccount.setText(account);
            etLoginAccount.requestFocus();
            etLoginAccount.setSelection(account.length());
        }
    }

    /**
     * 设置按扭颜色 以及 ‘注册，忘记密码'文字按下效果
     */
    private void initStyle() {
        btnLogin.setBackgroundDrawable(MyTools.setSelector(ContextCompat.getColor(this, R.color.lightGreen), ContextCompat.getColor(this, R.color.light_green_500), 4));
        tvRegister.setTextColor(MyTools.setTextColor(new int[]{ContextCompat.getColor(this, R.color.lightGreen), ContextCompat.getColor(this, R.color.black_alpha_96)}));
        tvForgetPsw.setTextColor(MyTools.setTextColor(new int[]{ContextCompat.getColor(this, R.color.lightGreen), ContextCompat.getColor(this, R.color.black_alpha_96)}));

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }


    @OnClick({R.id.et_login_account, R.id.et_login_psw, R.id.btn_login, R.id.tv_forget_psw, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_login_account:
                break;
            case R.id.et_login_psw:
                break;
            case R.id.btn_login:
                String strAccount = etLoginAccount.getText().toString();
                String strPsw = etLoginPsw.getText().toString();
                if (TextUtils.isEmpty(strAccount)) {
                    ToastUtils.showShort("您还未输入帐号");
                } else if (TextUtils.isEmpty(strPsw)) {
                    ToastUtils.showShort("您还未输入密码");
                } else {
                    // TODO: 2017/8/10 登录
                    reqLogin(strAccount, strPsw);
                }
                break;
            case R.id.tv_forget_psw:
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.putExtra("register", "0");
                startActivity(intent);
                break;
            case R.id.tv_register:
                Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                        e.onNext("d");
                    }
                });
                gotoActivity(RegisterActivity.class);
                break;
        }
    }

//    private void goOnather() {
//        String package_name=" com.example.administrator.myapplications";
//        String activity_path = "com.example.administrator.MainActivity";
//        Intent intent = new Intent();
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//可选
//        ComponentName comp = new ComponentName(package_name,activity_path);
//        intent.setComponent(comp);
//        startActivity(intent);
//    }

    /**
     * 登录请求
     */
    private void reqLogin(final String account, final String psw) {
        RetrofitHelper.getInstance().creat().getLogin(account, psw)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<LoginBean>() {
                    @Override
                    public void onNext(LoginBean tBaseData) {
                        if (tBaseData.isFlag()) {
                            ToastUtils.showShort("登录成功");
                            Intent intent = new Intent(LoginActivity.this, MainContentFragment.newInstance().getClass());
                            com.blankj.utilcode.util.SPUtils.getInstance().put(Constant.SP_LOGIN, true);
                            com.blankj.utilcode.util.SPUtils.getInstance().put(Constant.SP_USER_INFO, JsonUtil.toJson(tBaseData));
                            com.blankj.utilcode.util.SPUtils.getInstance().put(Constant.SP_PSW, ToolAES.encode(psw));
                            intent.putExtra("login", tBaseData.getUser().getUsername());
                            setResult(Constant.REQUEST_LOGIN, intent);
                            finish();
                        } else {
                            ToastUtils.showShort("登录失败,请检查帐号密码是否正确");
                        }
                    }

                    @Override
                    public void onError(String msg) {
                        if (!TextUtils.isEmpty(msg)) {
                            ToastUtils.showShort(msg);
                        }
                    }
                }));
    }

}
