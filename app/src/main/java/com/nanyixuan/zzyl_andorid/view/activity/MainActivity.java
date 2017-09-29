package com.nanyixuan.zzyl_andorid.view.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.nanyixuan.zzyl_andorid.R;
import com.nanyixuan.zzyl_andorid.api.Constant;
import com.nanyixuan.zzyl_andorid.api.newapi.MySubscriber;
import com.nanyixuan.zzyl_andorid.api.newapi.RetrofitHelper;
import com.nanyixuan.zzyl_andorid.api.newapi.SubCallback;
import com.nanyixuan.zzyl_andorid.base.BaseActivity;
import com.nanyixuan.zzyl_andorid.beacon.BeaconUtil;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.bean.UpdateAppBean;
import com.nanyixuan.zzyl_andorid.presenter.MainPresenter;
import com.nanyixuan.zzyl_andorid.service.UpdateService;
import com.nanyixuan.zzyl_andorid.utils.MyTools;
import com.nanyixuan.zzyl_andorid.view.fragment.MainContentFragment;
import com.nanyixuan.zzyl_andorid.widgets.UpdateDialog;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends BaseActivity implements MainActivityController {
    private BeaconUtil beacon;
    private boolean isForce;

    @Override
    protected void initView(Bundle savedInstanceState) {
        MainPresenter.init(this);
        showFragment(MainContentFragment.TAG, MainContentFragment.newInstance());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            beacon = new BeaconUtil(MainActivity.this);
            checkBluetoothValid();
        }
    }

    @Override
    protected int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showFragment(String tag, Fragment fragment) {
        FragmentUtils.addFragment(getSupportFragmentManager(), fragment, R.id.contentFragment, false, true);
    }


    @Override
    public void initMainActivity(LoginBean userInfoData) {
//        if (userInfoData.getUser() != null) {
//            reqLogin(userInfoData.getUser().getUsername(), userInfoData.getUser().getPassword());
//        }
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {

                        }
                    }
                });
    }

    @Override
    public void initAvatar() {

    }

    @Override
    public void updateApp() {
        isForce = true;//是否强制更新,只需要更改此行代码 false不强制更新  true强制更新
        RetrofitHelper.getInstance().creat().getUpdateInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new MySubscriber<>(this, true, new SubCallback<UpdateAppBean>() {
                    @Override
                    public void onNext(final UpdateAppBean response) throws IOException {
                        if (null != response) {
                            String remoteVersion = response.getVersion();
                            if (MyTools.judgeUpdate(MainActivity.this, remoteVersion)) {
                                final UpdateDialog updateDialog = UpdateDialog.newInstance(remoteVersion, response.getContent(), isForce);
                                updateDialog.showDialog(MainActivity.this);
                                if (isForce) updateDialog.setCancelable(false);
                                RxPermissions rxPermissions = new RxPermissions(MainActivity.this);
                                rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                                        .subscribe(new Consumer<Boolean>() {
                                            @Override
                                            public void accept(Boolean aBoolean) throws Exception {
                                                if (!aBoolean) {
                                                    ToastUtils.showShort("您拒绝了授权使用外部存储卡");
                                                    updateDialog.setBtnConfirmClickable(false);
                                                    return;
                                                } else {
                                                    updateDialog.setOnBtnListener(new UpdateDialog.UpdateCallBack() {
                                                        @Override
                                                        public void setBtn(int btn) {
                                                            if (btn == 0) {
                                                                updateDialog.setConfirmTxt("升级中....");
                                                                Intent intent = new Intent(MainActivity.this, UpdateService.class);
                                                                intent.putExtra(Constant.UPDATE_URL, response.getApk());
                                                                startService(intent);
                                                            }
                                                        }
                                                    });
                                                }
                                            }
                                        });

                            }

                        }
                    }

                    @Override
                    public void onError(String msg) {
                        ToastUtils.showShort(msg);
                    }
                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
        if (beacon != null) {
            beacon.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }

    private void checkBluetoothValid() {
        final BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (adapter == null) {
            AlertDialog dialog = new AlertDialog.Builder(this).setTitle("错误").setMessage("你的设备不具备蓝牙功能!").create();
            dialog.show();
            return;
        }
        if (!adapter.isEnabled()) {
            Intent mIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(mIntent, 1);
        }
    }

    private long exitTime = 0;

    @Override
    public void onBackPressed() {
        if (!(FragmentUtils.getFragments(getSupportFragmentManager()).get(0) instanceof MainContentFragment)) {
            super.onBackPressed();
            return;
        }
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            super.onDestroy();
            finish();
            System.exit(0);
        }
    }

}