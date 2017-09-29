package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description: 缴费验证
 * Created by liNan on 2017/9/11 11:26
 */

public class PayValidateBean implements Parcelable{

    /**
     * inrecord_id : 8a897dc45e5b5801015e5b85e541005d
     * out_time : 2017-09-11 11:45:03
     * in_time : 2017-09-07 16:47:35
     * money : 48.0
     * create_time : 2017-09-11
     * park_id : 100252
     * car_num : 豫A12789
     * id : 55524ac55e8b45f6b24491dc1d05580e
     */
    private String inrecord_id;
    private String out_time;
    private String in_time;
    private String money;
    private String create_time;
    private String park_id;
    private String car_num;
    private String id;

    protected PayValidateBean(Parcel in) {
        inrecord_id = in.readString();
        out_time = in.readString();
        in_time = in.readString();
        money = in.readString();
        create_time = in.readString();
        park_id = in.readString();
        car_num = in.readString();
        id = in.readString();
    }

    public static final Creator<PayValidateBean> CREATOR = new Creator<PayValidateBean>() {
        @Override
        public PayValidateBean createFromParcel(Parcel in) {
            return new PayValidateBean(in);
        }

        @Override
        public PayValidateBean[] newArray(int size) {
            return new PayValidateBean[size];
        }
    };

    public String getInrecord_id() {
        return inrecord_id;
    }

    public void setInrecord_id(String inrecord_id) {
        this.inrecord_id = inrecord_id;
    }

    public String getOut_time() {
        return out_time;
    }

    public void setOut_time(String out_time) {
        this.out_time = out_time;
    }

    public String getIn_time() {
        return in_time;
    }

    public void setIn_time(String in_time) {
        this.in_time = in_time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getPark_id() {
        return park_id;
    }

    public void setPark_id(String park_id) {
        this.park_id = park_id;
    }

    public String getCar_num() {
        return car_num;
    }

    public void setCar_num(String car_num) {
        this.car_num = car_num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(inrecord_id);
        dest.writeString(out_time);
        dest.writeString(in_time);
        dest.writeString(money);
        dest.writeString(create_time);
        dest.writeString(park_id);
        dest.writeString(car_num);
        dest.writeString(id);
    }
}
