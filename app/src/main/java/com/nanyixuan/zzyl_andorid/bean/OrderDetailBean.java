package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * description:
 * Created by liNan on 2017/9/22 14:09
 */

public class OrderDetailBean implements Parcelable{
    /**
     * end_date : 2217-12-31 23:59:59
     * transaction_id : 103530084865201709228247729047
     * ticket_status : 1
     * pay_price : 1
     * ticket_count : 1
     * out_transaction_id : 2017092221001004800510688803
     * real_sum : 1
     * sale_price : 1
     * pay_time : 2017-09-22 02:17:18
     * transq : TC-e0d4e34d446143dbb76fcc09088468dc
     * identity_code : 610526199104146115
     * ticket_type_id : 22222
     * user_id : 2
     * sale_detail : [{"endDate":7826083199000,"identityCode":"610526199104146115","salePrice":1,"startDate":1506017772639,"ticketTypeId":"22222"}]
     * ticket_type_name : 测试票
     * pay_type : 1
     * id : A2222220170922021612769581897
     * sale_time : 2017-09-22 02:16:12
     * start_date : 2017-09-22 02:16:12
     * status : 1
     */

    private String end_date;
    private String transaction_id;
    private String ticket_status;
    private String pay_price;
    private int ticket_count;
    private String out_transaction_id;
    private String real_sum;
    private String sale_price;
    private String pay_time;
    private String transq;
    private String identity_code;
    private String ticket_type_id;
    private String user_id;
    private String sale_detail;
    private String ticket_type_name;
    private String pay_type;
    private String id;
    private String sale_time;
    private String start_date;
    private String status;

    protected OrderDetailBean(Parcel in) {
        end_date = in.readString();
        transaction_id = in.readString();
        ticket_status = in.readString();
        pay_price = in.readString();
        ticket_count = in.readInt();
        out_transaction_id = in.readString();
        real_sum = in.readString();
        sale_price = in.readString();
        pay_time = in.readString();
        transq = in.readString();
        identity_code = in.readString();
        ticket_type_id = in.readString();
        user_id = in.readString();
        sale_detail = in.readString();
        ticket_type_name = in.readString();
        pay_type = in.readString();
        id = in.readString();
        sale_time = in.readString();
        start_date = in.readString();
        status = in.readString();
    }

    public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
        @Override
        public OrderDetailBean createFromParcel(Parcel in) {
            return new OrderDetailBean(in);
        }

        @Override
        public OrderDetailBean[] newArray(int size) {
            return new OrderDetailBean[size];
        }
    };

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getTicket_status() {
        return ticket_status;
    }

    public void setTicket_status(String ticket_status) {
        this.ticket_status = ticket_status;
    }

    public String getPay_price() {
        return pay_price;
    }

    public void setPay_price(String pay_price) {
        this.pay_price = pay_price;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public String getOut_transaction_id() {
        return out_transaction_id;
    }

    public void setOut_transaction_id(String out_transaction_id) {
        this.out_transaction_id = out_transaction_id;
    }

    public String getReal_sum() {
        return real_sum;
    }

    public void setReal_sum(String real_sum) {
        this.real_sum = real_sum;
    }

    public String getSale_price() {
        return sale_price;
    }

    public void setSale_price(String sale_price) {
        this.sale_price = sale_price;
    }

    public String getPay_time() {
        return pay_time;
    }

    public void setPay_time(String pay_time) {
        this.pay_time = pay_time;
    }

    public String getTransq() {
        return transq;
    }

    public void setTransq(String transq) {
        this.transq = transq;
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

    public String getSale_detail() {
        return sale_detail;
    }

    public void setSale_detail(String sale_detail) {
        this.sale_detail = sale_detail;
    }

    public String getTicket_type_name() {
        return ticket_type_name;
    }

    public void setTicket_type_name(String ticket_type_name) {
        this.ticket_type_name = ticket_type_name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(end_date);
        dest.writeString(transaction_id);
        dest.writeString(ticket_status);
        dest.writeString(pay_price);
        dest.writeInt(ticket_count);
        dest.writeString(out_transaction_id);
        dest.writeString(real_sum);
        dest.writeString(sale_price);
        dest.writeString(pay_time);
        dest.writeString(transq);
        dest.writeString(identity_code);
        dest.writeString(ticket_type_id);
        dest.writeString(user_id);
        dest.writeString(sale_detail);
        dest.writeString(ticket_type_name);
        dest.writeString(pay_type);
        dest.writeString(id);
        dest.writeString(sale_time);
        dest.writeString(start_date);
        dest.writeString(status);
    }
}
