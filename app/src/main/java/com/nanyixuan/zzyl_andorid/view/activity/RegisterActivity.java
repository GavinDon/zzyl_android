package com.nanyixuan.zzyl_andorid.view.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.MyApplication;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.ApiManager;
import com.nanyixuan.zzyl_andorid.api.ApiService;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.utils.MyTools;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.currentTimeMillis;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.et_register_phone)
    AppCompatEditText etRegisterPhone;
    @BindView(R.id.btn_close)
    ImageView btnClose;
    @BindView(R.id.et_register_sms)
    AppCompatEditText etRegisterSms;
    @BindView(R.id.tv_sms_timer)
    TextView tvSmsTimer;
    @BindView(R.id.et_register_psw)
    AppCompatEditText etRegisterPsw;
    @BindView(R.id.et_register_againPsw)
    AppCompatEditText etRegisterAgainPsw;
    @BindView(R.id.btn_confirm)
    Button btnRegisterConfirm;
    @BindColor(R.color.lightGreen)
    int lightGreen;
    @BindColor(R.color.pressGreen)
    int pressGreen;
    private long countTime; //计时
    private long lTime; //倒计时的当前时间
    private final String TIME = "time"; //倒计时的时间key
    private final String CTIME = "ctime"; //退出页面时的时间key
    private String smsCode = "";
    private String phone;
    Map<String, String> phoneCode = new HashMap<>();
    private String whoPage;
    private String localSmsCode;//本地生成的五位随机数

    @Override
    protected void initView(Bundle savedInstanceState) {
        whoPage = getIntent().getStringExtra("register");
        if (TextUtils.isEmpty(whoPage)) {
            setTitle("注册");
        } else {
            setTitle("找回密码");
        }
        retryCountDownTime();
        tvSmsTimer.setBackgroundDrawable(MyTools.setSelector(lightGreen, pressGreen, 32));
        btnRegisterConfirm.setBackgroundDrawable(MyTools.setSelector(lightGreen, pressGreen, 4));
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_register;
    }

    /**
     * 获取验证码
     */
    private void getSmsCode() {
        countDownTime(60); //倒计时60s
        localSmsCode = MyTools.noneStr();
        ApiManager.mRetrofit.create(ApiService.class)
                .getSms(etRegisterPhone.getText().toString(), String.format(" 您申请的验证码为:%s ,请于十分钟内输入使用。温馨提醒：网络购票后，请务必携带二代身份证刷卡入园。", localSmsCode))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (null != response && response.isSuccessful()) {
                                String resp = response.body().string();
                                JSONObject jsonObject = new JSONObject(resp);
                                if (jsonObject.getBoolean("flag")) {
//                                    smsCode = jsonObject.getString("code");
                                    smsCode = localSmsCode;
                                    phoneCode.put(phone, smsCode);
//                                    Intent intent=new Intent(RegisterActivity.this,SmsCountDownService.class);
//                                    intent.putExtra("smsCode",smsCode);
//                                    startService(intent);

                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

    }

    @OnClick({R.id.btn_confirm, R.id.btn_close, R.id.tv_sms_timer})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                etRegisterPhone.setText("");
                break;
            case R.id.tv_sms_timer:
                if (RegexUtils.isMobileExact(etRegisterPhone.getText().toString())) {
                    phone = etRegisterPhone.getText().toString();
                    getSmsCode();//获取验证码
                } else {
                    ToastUtils.showShort("手机号格式不正确");
                }
                break;
            case R.id.btn_confirm:
                String strPsw = etRegisterPsw.getText().toString().trim();
                String strAgainPsw = etRegisterAgainPsw.getText().toString().trim();
                String code = etRegisterSms.getText().toString();
                String account = etRegisterPhone.getText().toString();
                if (!RegexUtils.isMobileExact(account)) {
                    ToastUtils.showShort("手机号格式不正确");
                } else if (TextUtils.isEmpty(code) || !(code.equals(smsCode))) {
                    ToastUtils.showShort("验证码错误");
                } else if (TextUtils.isEmpty(strPsw) || TextUtils.isEmpty(strAgainPsw)) {
                    ToastUtils.showShort("密码不能为空");
                } else if (!strPsw.equals(strAgainPsw)) {
                    ToastUtils.showShort("两次密码输入不一致");
                } else {
                    // TODO: 2017/8/9  请求注册
                    if (TextUtils.isEmpty(whoPage)) {
                        reqRegister(account, strAgainPsw);
                    } else {
                        reqForgetPsw(account, strPsw);
                    }
                }

                break;
        }
    }

    /**
     * 注册请求
     *
     * @param account 手机号
     * @param psw     密码
     */
    private void reqRegister(final String account, final String psw) {
        ApiManager.mRetrofit.create(ApiService.class)
                .getRegister(account, psw)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.isSuccessful()) {
                                String resp = response.body().string();
                                if (resp.equals("true")) {
                                    ToastUtils.showShort("成功");
                                    finish();
                                } else {
                                    ToastUtils.showShort("注册失败");
                                }
                            }
                        } catch (IOException e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ToastUtils.showShort(t.getLocalizedMessage());
                    }
                });
    }

    /**
     * 找回密码
     */
    private void reqForgetPsw(final String account, final String psw) {
        ApiManager.mRetrofit.create(ApiService.class)
                .getFoegetPsw(account, psw)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            if (response.isSuccessful()) {
                                String resp = response.body().string();
                                if (resp.equals("true")) {
                                    ToastUtils.showShort("修改密码成功");
                                    finish();
                                } else {
                                    ToastUtils.showShort("修改密码失败");
                                }
                            }
                        } catch (IOException e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        ToastUtils.showShort(t.getLocalizedMessage());
                    }
                });
    }

    @OnTextChanged(value = R.id.et_register_phone, callback = OnTextChanged.Callback.TEXT_CHANGED)
    void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() >= 1) {
            btnClose.setVisibility(View.VISIBLE);
        } else {
            btnClose.setVisibility(View.GONE);
        }
    }

    private Disposable disposable;

    /**
     * 倒计时
     *
     * @param time
     */
    private void countDownTime(long time) {
        countTime = time;
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        lTime = countTime - aLong;
                        return lTime; //转换数据 1,2,3为60，59，58
                    }
                })
                .take(countTime + 1) //countTime+1s后结束计时
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(@NonNull Long aLong) {
                        if (0 == aLong) {
                            tvSmsTimer.setText("获取验证码");
                            tvSmsTimer.setClickable(true);
                        } else {
                            tvSmsTimer.setText(String.format(Locale.getDefault(), "%d重新获取验证码", aLong));
                            tvSmsTimer.setClickable(false);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        ToastUtils.showShort(e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                });


    }

    /**
     * 退出之后再次进入页面开始之前未完成的计时
     */
    private void retryCountDownTime() {
        if (MyApplication.map == null || MyApplication.map.size() <= 0) {
            return;
        }
        long time = MyApplication.map.get(TIME); //退出时计时的秒数
        long cTime = MyApplication.map.get(CTIME);//退出时当前的毫秒秒
        long t = System.currentTimeMillis() / 1000 - cTime / 1000 - time; //把毫秒转成秒计算
        if (t < 0) {
            countDownTime(Math.abs(t));
        }
    }

    /**
     * 退出页面时记录短信倒计时时间
     */
    @Override
    protected void onPause() {
        super.onPause();
        if (MyApplication.map == null) {
            MyApplication.map = new HashMap<>();
        } else {
            MyApplication.map.put(TIME, lTime);
            MyApplication.map.put(CTIME, currentTimeMillis());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
