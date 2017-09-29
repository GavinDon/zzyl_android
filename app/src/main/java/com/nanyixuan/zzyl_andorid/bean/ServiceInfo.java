package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by YixuanNan on 2017/3/31.
 *
 * @author YixuanNan
 *         服务信息详情
 */

public class ServiceInfo {

    /**
     * id : 402881f75b18d5f5015b18dc4abe0000
     * createdDate : 1490770741000
     * modifiedDate : 1490770741000
     * createdBy : system
     * modifiedBy : system
     * version : 0
     * enabled : true
     * name : 开园时间
     * content : <p>开园时间为：2017年9月1日</p>
     */

    private String id;
    private long createdDate;
    private long modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int version;
    private boolean enabled;
    private String name;
    private String content;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ServiceInfo{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", version=" + version +
                ", enabled=" + enabled +
                ", name='" + name + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
