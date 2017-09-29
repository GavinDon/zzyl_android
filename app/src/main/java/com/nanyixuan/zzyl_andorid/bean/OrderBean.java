package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description: 生成订单信息
 * Created by liNan on 2017/8/7 14:22
 */

public class OrderBean implements Parcelable{

    /**
     * end_date : 2217-12-31
     * identity_code : 610526199104146115
     * ticket_type_id : 10001
     * user_id : 10001
     * ticket_count : 1
     * sale_detail : [{"endDate":7826083199000,"identityCode":"610526199104146115","salePrice":2000,"startDate":1502088957011,"ticketTypeID":"10001"}]
     * real_sum : 2000
     * pay_type : 03
     * id : A1000120170807145557113i627266
     * sale_price : 2000
     * sale_time : 2017-08-07
     * start_date : 2017-08-07
     */

    private String end_date;
    private String identity_code;
    private String ticket_type_id;
    private String user_id;
    private int ticket_count;
    private String sale_detail;
    private String real_sum;
    private String pay_type;
    private String id;
    private String sale_price;
    private String sale_time;
    private String start_date;

    protected OrderBean(Parcel in) {
        end_date = in.readString();
        identity_code = in.readString();
        ticket_type_id = in.readString();
        user_id = in.readString();
        ticket_count = in.readInt();
        sale_detail = in.readString();
        real_sum = in.readString();
        pay_type = in.readString();
        id = in.readString();
        sale_price = in.readString();
        sale_time = in.readString();
        start_date = in.readString();
    }

    public static final Creator<OrderBean> CREATOR = new Creator<OrderBean>() {
        @Override
        public OrderBean createFromParcel(Parcel in) {
            return new OrderBean(in);
        }

        @Override
        public OrderBean[] newArray(int size) {
            return new OrderBean[size];
        }
    };

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getIdentity_code() {
        return identity_code;
    }

    public void setIdentity_code(String identity_code) {
        this.identity_code = identity_code;
    }

    public String getTicket_type_id() {
        return ticket_type_id;
    }

    public void setTicket_type_id(String ticket_type_id) {
        this.ticket_type_id = ticket_type_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public String getSale_detail() {
        return sale_detail;
    }

    public void setSale_detail(String sale_detail) {
        this.sale_detail = sale_detail;
    }

    public String getReal_sum() {
        return real_sum;
    }

    public void setReal_sum(String real_sum) {
        this.real_sum = real_sum;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getSale_time() {
        return sale_time;
    }

    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(end_date);
        dest.writeString(identity_code);
        dest.writeString(ticket_type_id);
        dest.writeString(user_id);
        dest.writeInt(ticket_count);
        dest.writeString(sale_detail);
        dest.writeString(real_sum);
        dest.writeString(pay_type);
        dest.writeString(id);
        dest.writeString(sale_price);
        dest.writeString(sale_time);
        dest.writeString(start_date);
    }
}
