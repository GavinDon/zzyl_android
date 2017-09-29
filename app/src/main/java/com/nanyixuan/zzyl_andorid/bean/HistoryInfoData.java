package com.nanyixuan.zzyl_andorid.bean;

/**
 * Created by nanyixuan on 2017/4/23.
 * @author nanyixuan
 * 历史足迹数据
 */

public class HistoryInfoData {
    @Override
    public String toString() {
        return "HistoryInfoData{" +
                "id='" + id + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", version=" + version +
                ", enabled=" + enabled +
                ", title='" + title + '\'' +
                ", hybhId='" + hybhId + '\'' +
                ", hybh=" + hybh +
                ", url='" + url + '\'' +
                ", tp='" + tp + '\'' +
                '}';
    }

    /**
     * id : 402881f75b19b311015b19be4af20000
     * createdDate : 1490943589000
     * modifiedDate : 1490943589000
     * createdBy : admin
     * modifiedBy : system
     * version : 4
     * enabled : true
     * title : 北京馆
     * hybhId : 402881f75b14f5ca015b153fed4e0001
     * hybh : {"id":"402881f75b14f5ca015b153fed4e0001","createdDate":1490710162000,"modifiedDate":1490710162000,"createdBy":"system","modifiedBy":"system","version":0,"enabled":true,"yhm":"15929316619","sjh":"15929316619","dlmm":"","zt":"","tp":""}
     * url : zzwhe/yqdlgcyq/details?id=402881ed5b1993db015b19bdac430000&yhId=402881f75b14f5ca015b153fed4e0001
     */

    private String id;
    private long createdDate;
    private long modifiedDate;
    private String createdBy;
    private String modifiedBy;
    private int version;
    private boolean enabled;
    private String title;
    private String hybhId;
    private HybhBean hybh;
    private String url;
    private String tp;

    public String getTp() {
        return tp;
    }

    public void setTp(String tp) {
        this.tp = tp;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHybhId() {
        return hybhId;
    }

    public void setHybhId(String hybhId) {
        this.hybhId = hybhId;
    }

    public HybhBean getHybh() {
        return hybh;
    }

    public void setHybh(HybhBean hybh) {
        this.hybh = hybh;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class HybhBean {
        @Override
        public String toString() {
            return "HybhBean{" +
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
                    ", zt='" + zt + '\'' +
                    ", tp='" + tp + '\'' +
                    '}';
        }

        /**
         * id : 402881f75b14f5ca015b153fed4e0001
         * createdDate : 1490710162000
         * modifiedDate : 1490710162000
         * createdBy : system
         * modifiedBy : system
         * version : 0
         * enabled : true
         * yhm : 15929316619
         * sjh : 15929316619
         * dlmm :
         * zt :
         * tp :
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
        private String zt;
        private String tp;

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

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }

        public String getTp() {
            return tp;
        }

        public void setTp(String tp) {
            this.tp = tp;
        }
    }
}
