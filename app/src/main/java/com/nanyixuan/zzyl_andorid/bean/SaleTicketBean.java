package com.nanyixuan.zzyl_andorid.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * description: 票面信息
 * Created by liNan on 2017/9/21 14:12
 */

public class SaleTicketBean {


    /**
     * msg : 平台正常售票!
     * flag : true
     * list : [{"ticketname":"国庆票1号","explain":"国庆节门票1","limitnumber":18000,"endtime":"2017-09-21 23:59:59","yprice":"60","starttime":"2017-09-21 00:00:00","imgurl":"http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/333333.png","deleted":"0","imgurltwo":"http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/c.png","xprice":"55","enable":"0","ticketnumber":18000,"id":1,"ticketsystemid":"11111","remarks":"国庆节门票1"},{"ticketname":"国庆票2号","explain":"国庆节门票2","limitnumber":18000,"endtime":"2017-09-22 23:59:59","yprice":"60","starttime":"2017-09-22 00:00:00","imgurl":"http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/111111.png","deleted":"0","imgurltwo":"http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/d.png","xprice":"55","enable":"0","ticketnumber":18000,"id":2,"ticketsystemid":"22222","remarks":"国庆节门票2"}]
     */

    private String msg;
    private boolean flag;
    private List<ListBean> list;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean implements Parcelable {
        /**
         * ticketname : 国庆票1号
         * explain : 国庆节门票1
         * limitnumber : 18000
         * endtime : 2017-09-21 23:59:59
         * yprice : 60
         * starttime : 2017-09-21 00:00:00
         * imgurl : http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/333333.png
         * deleted : 0
         * imgurltwo : http://zhihui.expo2017.net.cn/zhcomplaint/upload/ticketImg/c.png
         * xprice : 55
         * enable : 0
         * ticketnumber : 18000
         * id : 1
         * ticketsystemid : 11111
         * remarks : 国庆节门票1
         */

        private String ticketname;
        private String explain;
        private int limitnumber;
        private String endtime;
        private String yprice;
        private String starttime;
        private String imgurl;
        private String deleted;
        private String imgurltwo;
        private String xprice;
        private String enable;

        private String is_limited;
        private int ticketnumber;
        private int id;
        private String ticketsystemid;
        private String remarks;


        protected ListBean(Parcel in) {
            ticketname = in.readString();
            explain = in.readString();
            limitnumber = in.readInt();
            endtime = in.readString();
            yprice = in.readString();
            starttime = in.readString();
            imgurl = in.readString();
            deleted = in.readString();
            imgurltwo = in.readString();
            xprice = in.readString();
            enable = in.readString();
            is_limited = in.readString();
            ticketnumber = in.readInt();
            id = in.readInt();
            ticketsystemid = in.readString();
            remarks = in.readString();
        }

        public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
            @Override
            public ListBean createFromParcel(Parcel in) {
                return new ListBean(in);
            }

            @Override
            public ListBean[] newArray(int size) {
                return new ListBean[size];
            }
        };

        public String getTicketname() {
            return ticketname;
        }

        public void setTicketname(String ticketname) {
            this.ticketname = ticketname;
        }

        public String getExplain() {
            return explain;
        }

        public void setExplain(String explain) {
            this.explain = explain;
        }

        public int getLimitnumber() {
            return limitnumber;
        }

        public void setLimitnumber(int limitnumber) {
            this.limitnumber = limitnumber;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }

        public String getYprice() {
            return yprice;
        }

        public void setYprice(String yprice) {
            this.yprice = yprice;
        }

        public String getStarttime() {
            return starttime;
        }

        public void setStarttime(String starttime) {
            this.starttime = starttime;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getImgurltwo() {
            return imgurltwo;
        }

        public void setImgurltwo(String imgurltwo) {
            this.imgurltwo = imgurltwo;
        }

        public String getXprice() {
            return xprice;
        }

        public void setXprice(String xprice) {
            this.xprice = xprice;
        }

        public String getEnable() {
            return enable;
        }

        public void setEnable(String enable) {
            this.enable = enable;
        }

        public int getTicketnumber() {
            return ticketnumber;
        }

        public void setTicketnumber(int ticketnumber) {
            this.ticketnumber = ticketnumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTicketsystemid() {
            return ticketsystemid;
        }

        public void setTicketsystemid(String ticketsystemid) {
            this.ticketsystemid = ticketsystemid;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }

        public String getIs_limited() {
            return is_limited;
        }

        public void setIs_limited(String is_limited) {
            this.is_limited = is_limited;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(ticketname);
            dest.writeString(explain);
            dest.writeInt(limitnumber);
            dest.writeString(endtime);
            dest.writeString(yprice);
            dest.writeString(starttime);
            dest.writeString(imgurl);
            dest.writeString(deleted);
            dest.writeString(imgurltwo);
            dest.writeString(xprice);
            dest.writeString(enable);
            dest.writeString(is_limited);
            dest.writeInt(ticketnumber);
            dest.writeInt(id);
            dest.writeString(ticketsystemid);
            dest.writeString(remarks);
        }
    }
}
