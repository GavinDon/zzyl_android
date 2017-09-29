package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by YixuanNan on 2017/3/26.
 *
 * @author YixuanNan
 *         首页底部列表数据。
 */

public class MainListData {

    /**
     * id : 402881ed5af4c536015af4ca036b0000
     * createdDate : 1490165564000
     * modifiedDate : 1490165564000
     * createdBy : admin
     * modifiedBy : admin
     * version : 0
     * enabled : true
     * tp : upload-files/images/2017/03/22/402881ed5af4c8ec015af4c9f2110003.jpg
     * mc : 33
     * sx : 33
     */

    private String id;
    private long createdDate;
    private long modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int version;
    private boolean enabled;
    private String tp;
    private String mc;
    private String sx;

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

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public String getSx() {
        return sx;
    }

    public void setSx(String sx) {
        this.sx = sx;
    }

    @Override
    public String toString() {
        return "MainListData{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", version=" + version +
                ", enabled=" + enabled +
                ", tp='" + tp + '\'' +
                ", mc='" + mc + '\'' +
                ", sx='" + sx + '\'' +
                '}';
    }
}
