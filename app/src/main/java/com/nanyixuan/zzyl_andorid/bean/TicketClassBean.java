package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * description: 票面种类
 * Created by liNan on 2017/8/29 10:44
 */

public class TicketClassBean implements Parcelable {


    /**
     * price : 10
     * ticketTypeId : 19
     * ticketTypeName : 测试票
     */

    private int price;
    private String ticketTypeId;
    private String ticketTypeName;

    protected TicketClassBean(Parcel in) {
        price = in.readInt();
        ticketTypeId = in.readString();
        ticketTypeName = in.readString();
    }

    public static final Creator<TicketClassBean> CREATOR = new Creator<TicketClassBean>() {
        @Override
        public TicketClassBean createFromParcel(Parcel in) {
            return new TicketClassBean(in);
        }

        @Override
        public TicketClassBean[] newArray(int size) {
            return new TicketClassBean[size];
        }
    };

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTicketTypeId() {
        return ticketTypeId;
    }

    public void setTicketTypeId(String ticketTypeId) {
        this.ticketTypeId = ticketTypeId;
    }

    public String getTicketTypeName() {
        return ticketTypeName;
    }

    public void setTicketTypeName(String ticketTypeName) {
        this.ticketTypeName = ticketTypeName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(price);
        dest.writeString(ticketTypeId);
        dest.writeString(ticketTypeName);
    }
}
