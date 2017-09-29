package com.nanyixuan.zzyl_andorid.bean;

/**
 * description:
 * Created by liNan on 2017/8/7 15:14
 */

public class PrepayBean {

    /**
     * transaction_id : 755437000006201708071250996552
     * charset : UTF-8
     * nonce_str : 1502090194464
     * pay_info : {"sign":"6E19ACE54B66DB4F2382E1FCBC595CA8","timestamp":"1502089945","noncestr":"1502089945519","partnerid":"12723495","prepayid":"wx20170807151225a24168c0e10511906393","package":"Si{"sign":"6E19ACE54B66DB4F2382E1FCBC595CA8","timestamp":"1502089945","noncestr":"1502089945519","partnerid":"12723495","prepayid":"wx20170807151225a24168c0e10511906393","package":"Sign=WXPay","appid":"wxd3a1cdf74d0c41b3"}
     * mch_id : 755437000006
     * version : 2.0
     * out_trade_no : A1000120170807151631379s218137
     * token_id : 242d32f51217d8e2dfb0c448798b36070
     * appid : wx6ae0f4feabfcb549
     * result_code : 0
     * sign_type : MD5
     * status : 0
     */

    private String transaction_id;
    private String charset;
    private String nonce_str;
    private String pay_info;
    private String mch_id;
    private String version;
    private String out_trade_no;
    private String token_id;
    private String appid;
    private String result_code;
    private String sign_type;
    private String status;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPay_info() {
        return pay_info;
    }

    public void setPay_info(String pay_info) {
        this.pay_info = pay_info;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getToken_id() {
        return token_id;
    }

    public void setToken_id(String token_id) {
        this.token_id = token_id;
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

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



}
