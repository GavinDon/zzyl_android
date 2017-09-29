package com.nanyixuan.zzyl_andorid.bean;

/**
 * description:
 * Created by liNan on 2017/8/31 11:27
 */

public class RefundBean {


    /**
     * transaction_id : 12345
     * identity_code : 610526199104146111
     * user_id : 10001
     * refund_AMT : 1
     * ticket_count : 1
     * refund_time : 2017-08-31
     * refund_ticket : [{"identityCode":"610526199104146111","refundAmt":1}]
     * id : RE-668657f0466f463a9bb3d2d0b08a1cdc
     * order_id : A1920170831112932515i997416
     * refund_AMT_sum : 1
     * transq : TR-ee54da138eee4bc8b0a911817baa8354
     */

    private String transaction_id;
    private String identity_code;
    private String user_id;
    private String refund_AMT;
    private int ticket_count;
    private String refund_time;
    private String refund_ticket;
    private String id;
    private String order_id;
    private String refund_AMT_sum;
    private String transq;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getIdentity_code() {
        return identity_code;
    }

    public void setIdentity_code(String identity_code) {
        this.identity_code = identity_code;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRefund_AMT() {
        return refund_AMT;
    }

    public void setRefund_AMT(String refund_AMT) {
        this.refund_AMT = refund_AMT;
    }

    public int getTicket_count() {
        return ticket_count;
    }

    public void setTicket_count(int ticket_count) {
        this.ticket_count = ticket_count;
    }

    public String getRefund_time() {
        return refund_time;
    }

    public void setRefund_time(String refund_time) {
        this.refund_time = refund_time;
    }

    public String getRefund_ticket() {
        return refund_ticket;
    }

    public void setRefund_ticket(String refund_ticket) {
        this.refund_ticket = refund_ticket;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getRefund_AMT_sum() {
        return refund_AMT_sum;
    }

    public void setRefund_AMT_sum(String refund_AMT_sum) {
        this.refund_AMT_sum = refund_AMT_sum;
    }

    public String getTransq() {
        return transq;
    }

    public void setTransq(String transq) {
        this.transq = transq;
    }
}
