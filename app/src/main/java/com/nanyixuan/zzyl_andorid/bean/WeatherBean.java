package com.nanyixuan.zzyl_andorid.bean;

/**
 * description:
 * Created by liNan on 2017/9/12 16:06
 */

public class WeatherBean {


    /**
     * id : 23
     * text : 晴
     * details :
     * code : 0
     * ctime : 2017-09-13 09:36:47
     * brief : 较适宜
     * temperature : 28
     */

    private int id;
    private String text;
    private String details;
    private String code;
    private String ctime;
    private String brief;
    private String temperature;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
