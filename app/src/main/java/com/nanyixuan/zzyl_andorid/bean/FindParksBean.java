package com.nanyixuan.zzyl_andorid.bean;

/**
 * description:
 * Created by liNan on 2017/9/8 16:24
 */

public class FindParksBean {


    /**
     * parklocation : 经三路财富广场8号楼
     * realnum : 123      剩余车位
     * parkposition : 113.692169,34.782997
     * realbespeaknum : 59   剩余预约车位数
     * totalnum : 200    总车位数
     * bespeaknum : 60   可预约车位总数
     * parkname : 河南视博测试车场
     * bespeaktimeout : 20
     * state : 1
     * updatetime : 2017-09-08 15:33:59.0
     * parkid : 100252
     */

    private String parklocation;
    private int realnum;
    private String parkposition;
    private int realbespeaknum;
    private int totalnum;
    private int bespeaknum;
    private String parkname;
    private int bespeaktimeout;
    private int state;
    private String updatetime;
    private int parkid;

    public String getParklocation() {
        return parklocation;
    }

    public void setParklocation(String parklocation) {
        this.parklocation = parklocation;
    }

    public int getRealnum() {
        return realnum;
    }

    public void setRealnum(int realnum) {
        this.realnum = realnum;
    }

    public String getParkposition() {
        return parkposition;
    }

    public void setParkposition(String parkposition) {
        this.parkposition = parkposition;
    }

    public int getRealbespeaknum() {
        return realbespeaknum;
    }

    public void setRealbespeaknum(int realbespeaknum) {
        this.realbespeaknum = realbespeaknum;
    }

    public int getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(int totalnum) {
        this.totalnum = totalnum;
    }

    public int getBespeaknum() {
        return bespeaknum;
    }

    public void setBespeaknum(int bespeaknum) {
        this.bespeaknum = bespeaknum;
    }

    public String getParkname() {
        return parkname;
    }

    public void setParkname(String parkname) {
        this.parkname = parkname;
    }

    public int getBespeaktimeout() {
        return bespeaktimeout;
    }

    public void setBespeaktimeout(int bespeaktimeout) {
        this.bespeaktimeout = bespeaktimeout;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public int getParkid() {
        return parkid;
    }

    public void setParkid(int parkid) {
        this.parkid = parkid;
    }
}
