package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by YixuanNan on 2017/3/25.
 *
 * @author YixuanNan
 *         园博资讯数据类。
 */

public class EncyclopediaData {

    /**
     * id : 00000000000000000000000000000001
     * createdDate : 1490750481000
     * modifiedDate : 1490866380000
     * createdBy : admin
     * modifiedBy : system
     * version : 3
     * enabled : true
     * fjid : 00000000000000000000000000000000
     * flmc : 花
     * tp : upload-files/images/2017/03/30/402881ed5b1e836f015b1e8f90e60002.jpg
     * sm : 花类
     */

    private String id;
    private long createdDate;
    private long modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int version;
    private boolean enabled;
    private String fjid;
    private String flmc;
    private String tp;
    private String sm;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFjid() {
        return fjid;
    }

    public void setFjid(String fjid) {
        this.fjid = fjid;
    }

    public String getFlmc() {
        return flmc;
    }

    public void setFlmc(String flmc) {
        this.flmc = flmc;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getSm() {
        return sm;
    }

    public void setSm(String sm) {
        this.sm = sm;
    }
}
