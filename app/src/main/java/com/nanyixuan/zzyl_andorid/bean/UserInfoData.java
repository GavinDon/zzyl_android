package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by YixuanNan on 2017/3/24.
 * @author YixuanNan
 */

public class UserInfoData {

    /**
     * id : 402883b2588145c30158816c33100011
     * createdDate : 1490090352000
     * modifiedDate : 1490090355000
     * createdBy : root
     * modifiedBy : root
     * version : 0
     * enabled : true
     * yhm : admin
     * sjh : 13669184137
     * dlmm : 123456
     * tp :
     * zt : null
     */

    private String id;
    private long createdDate;
    private long modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int version;
    private boolean enabled;
    private String yhm;
    private String sjh;
    private String dlmm;
    private String tp;
    private Object zt;

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

    public String getYhm() {
        return yhm;
    }

    public void setYhm(String yhm) {
        this.yhm = yhm;
    }

    public String getSjh() {
        return sjh;
    }

    public void setSjh(String sjh) {
        this.sjh = sjh;
    }

    public String getDlmm() {
        return dlmm;
    }

    public void setDlmm(String dlmm) {
        this.dlmm = dlmm;
    }

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public Object getZt() {
        return zt;
    }

    public void setZt(Object zt) {
        this.zt = zt;
    }

    @Override
    public String toString() {
        return "UserInfoData{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", version=" + version +
                ", enabled=" + enabled +
                ", yhm='" + yhm + '\'' +
                ", sjh='" + sjh + '\'' +
                ", dlmm='" + dlmm + '\'' +
                ", tp='" + tp + '\'' +
                ", zt=" + zt +
                '}';
    }
}
