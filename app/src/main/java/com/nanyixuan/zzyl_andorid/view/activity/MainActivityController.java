package com.nanyixuan.zzyl_andorid.view.activity;

import com.nanyixuan.zzyl_andorid.bean.LoginBean;

/**
 * Created by YixuanNan on 2017/3/24.
 *
 * @author YixuanNan
 *         MainActivity控制类。
 */

public interface MainActivityController extends FragmentController {
    void initMainActivity(LoginBean userInfoData);
    void initAvatar();
    void updateApp();

}
