package com.nanyixuan.zzyl_andorid.bean;

/**
 * description: 更新APP bean
 * Created by liNan on 2017/9/13 8:53
 */

public class UpdateAppBean {

    /**
     * content : 安卓更新内容
     * id : 1
     * isticket : 0
     * applicationType : android
     * version : 0.2
     * apk : 123213
     */

    private String content;
    private int id;
    private String isticket;
    private String applicationType;
    private String version;
    private String apk;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsticket() {
        return isticket;
    }

    public void setIsticket(String isticket) {
        this.isticket = isticket;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getApk() {
        return apk;
    }

    public void setApk(String apk) {
        this.apk = apk;
    }
}
