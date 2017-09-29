package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by YixuanNan on 2017/3/23.
 *
 * @author YixuanNan
 *         基础返回类
 */

public class BaseData<T> {

    /**
     * success : 请求是否成功。
     * msg : 请求消息。
     * data : 请求数据。
     */

    private boolean success;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}