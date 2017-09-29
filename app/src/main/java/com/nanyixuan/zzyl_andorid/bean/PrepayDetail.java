package com.nanyixuan.zzyl_andorid.bean;

import com.google.gson.annotations.SerializedName;

/**
 * description: 预支付详情
 * Created by liNan on 2017/9/23 14:31
 */

public class PrepayDetail {


    /**
     * sign : CF7C33CF118DB8B611DBC08F11BDFC73
     * timestamp : 1506148575
     * noncestr : 1506148575266
     * partnerid : 55032425
     * prepayid : wx201709231436157838b4b8740095222054
     * package : Sign=WXPay
     * appid : wxbb032cafa457f0a6
     */

    private String sign;
    private String timestamp;
    private String noncestr;
    private String partnerid;
    private String prepayid;
    @SerializedName("package")
    private String packageX;
    private String appid;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}
