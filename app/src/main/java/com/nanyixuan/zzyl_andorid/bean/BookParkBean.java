package com.nanyixuan.zzyl_andorid.bean;

/**
 * description: 停车场预约
 * Created by liNan on 2017/9/11 10:17
 */

public class BookParkBean {

    /**
     * parklocation : 经三路财富广场8号楼
     * bespeaktime : 2017-09-11 10:27:25
     * parkname : 河南视博测试车场
     * bespeaktimeout : 2017-09-11 10:47:25
     * parkid : 100252
     */

    private String parklocation;
    private String bespeaktime;
    private String parkname;
    private String bespeaktimeout;
    private int parkid;

    public String getParklocation() {
        return parklocation;
    }

    public void setParklocation(String parklocation) {
        this.parklocation = parklocation;
    }

    public String getBespeaktime() {
        return bespeaktime;
    }

    public void setBespeaktime(String bespeaktime) {
        this.bespeaktime = bespeaktime;
    }

    public String getParkname() {
        return parkname;
    }

    public void setParkname(String parkname) {
        this.parkname = parkname;
    }

    public String getBespeaktimeout() {
        return bespeaktimeout;
    }

    public void setBespeaktimeout(String bespeaktimeout) {
        this.bespeaktimeout = bespeaktimeout;
    }

    public int getParkid() {
        return parkid;
    }

    public void setParkid(int parkid) {
        this.parkid = parkid;
    }
}
