package com.nanyixuan.zzyl_andorid.api.newapi;

import java.io.IOException;

/**
 *
 * 观察者回调事件到view
 * Created by linan   on 2016/10/12.
 */
public interface SubCallback<T> {
    void onNext(T t) throws IOException;
    void onError(String msg);
}
