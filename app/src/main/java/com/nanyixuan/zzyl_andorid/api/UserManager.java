package com.nanyixuan.zzyl_andorid.api;

import android.content.Context;
import android.content.SharedPreferences;

import com.nanyixuan.zzyl_andorid.MyApplication;
import com.nanyixuan.zzyl_andorid.bean.LoginBean;
import com.nanyixuan.zzyl_andorid.utils.JsonUtil;
import com.nanyixuan.zzyl_andorid.utils.SPUtils;

/**
 * Created by YixuanNan on 2017/3/25.
 *
 * @author YixuanNan
 *         用户信息管理。
 */

public class UserManager {
    public static final String TAG = "UserManager";
    private static UserManager userManager;
    private static Context context;
    private static SharedPreferences sp;

    public static void init(Context context) {
        UserManager.context = context;
    }

    public static UserManager getInstance() {
        if (userManager == null) {
            userManager = new UserManager();
            sp = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        }
        return userManager;
    }

    public LoginBean getUserInfo() {
        String userInfo = (String) SPUtils.get(MyApplication.appContext, Constant.SP_USER_INFO,"");
        if (userInfo==null) {
            return null;
        }
        return JsonUtil.fromJson(userInfo, LoginBean.class);
    }
    public void setUserInfo(LoginBean tBaseData){
        SPUtils.put(MyApplication.appContext,Constant.SP_USER_INFO,  JsonUtil.toJson(tBaseData));
    }

}
