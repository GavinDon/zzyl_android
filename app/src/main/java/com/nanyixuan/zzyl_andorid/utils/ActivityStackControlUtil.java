package com.nanyixuan.zzyl_andorid.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * description:Activity栈的管理
 * Created by liNan on 2017/8/9 10:44
 */

public class ActivityStackControlUtil {

    private static List<Activity> activityList = new ArrayList<>();

    public static int getCounter() {
        return activityList.size();
    }

    public static void remove(Activity activity) {
        activityList.remove(activity);
    }

    public static void add(Activity activity) {
        activityList.add(activity);
    }

    public static void finishProgram() {
        for (Activity activity : activityList) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.gc();
        // android.os.Process.killProcess(android.os.Process.myPid());
    }

    public static void finishAllActivityWithout(Activity withoutActivity) {

        for (int index = activityList.size() - 1; index >= 0; index--) {
            if (withoutActivity != activityList.get(index)) {
                activityList.get(index).finish();
                activityList.remove(index);
            }
        }
    }

    /**
     * 获取栈顶的activity
     *
     * @return
     */
    public static Activity getTopClass() {
        if (!activityList.isEmpty()) {
            Activity activity = activityList.get(activityList.size() - 1);
            return activity;
        }
        return null;
    }
}
