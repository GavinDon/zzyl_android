package com.nanyixuan.zzyl_andorid.presenter;

import android.support.v4.app.Fragment;

import com.nanyixuan.zzyl_andorid.api.UserManager;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.view.activity.MainActivityController;

/**
 * Created by YixuanNan on 2017/3/22.
 *
 * @author YixuanNan
 *         MainActivity操作类
 */

public class MainPresenter {
    private static MainActivityController mainActivityController;

    private static volatile MainPresenter instance = null;

    public static MainPresenter getInstance() throws Throwable {
        if (MainPresenter.mainActivityController == null) {
            throw new Throwable("MainPresenter未初始化,请使用init()方法进行初始化");
        }
        if (instance == null) {
            synchronized (MainPresenter.class) {
                if (instance == null) {
                    instance = new MainPresenter();
                }
            }
        }
        return instance;
    }

    public static void init(MainActivityController mainActivityController) {
        MainPresenter.mainActivityController = mainActivityController;
        LoginBean loginBean=UserManager.getInstance().getUserInfo();
        if (loginBean != null) {
            mainActivityController.initMainActivity(loginBean);
        }else {

        }
        mainActivityController.updateApp(); //更新app

    }

    public void showFragment(String TAG, Fragment fragment){
        mainActivityController.showFragment(TAG,fragment);
    }

    public void initAvatar(){
        mainActivityController.initAvatar();
    }
}