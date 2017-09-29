package com.nanyixuan.zzyl_andorid.bean;


/**
 * description: 支付宝支付
 * Created by liNan on 2017/8/29 10:44
 */

public class AliPayBean {


    /**
     * charset : UTF-8
     * code_img_url : https:
     * nonce_str : 1503300397496
     * code_url : https:
     * appid : 2016081701760348
     * result_code : 0
     * mch_id : 101520000465
     * sign_type : MD5
     * version : 2.0
     * status : 0
     */

    private String charset;
    private String code_img_url;
    private String nonce_str;
    private String code_url;
    private String appid;
    private String result_code;
    private String mch_id;
    private String sign_type;
    private String version;
    private String status;

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getCode_img_url() {
        return code_img_url;
    }

    public void setCode_img_url(String code_img_url) {
        this.code_img_url = code_img_url;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getCode_url() {
        return code_url;
    }

    public void setCode_url(String code_url) {
        this.code_url = code_url;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
