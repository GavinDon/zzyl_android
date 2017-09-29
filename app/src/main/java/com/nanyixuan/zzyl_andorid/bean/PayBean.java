package com.nanyixuan.zzyl_andorid.bean;

/**
 * description:
 * Created by liNan on 2017/8/5 14:47
 */

public class PayBean {


    private String payTotal; //支付金额
    private String idCard;   //身份证号
    private String ticketId;   //车票id
    private String ticketName;   //车票名
    private String ticketCount;   //车票数量
    private String orderId;   //订单号

    public PayBean() {
    }

    public PayBean(String payTotal, String orderId) {
        this.payTotal = payTotal;
        this.orderId = orderId;
    }

    public PayBean(String payTotal, String idCard, String ticketId, String ticketName, String ticketCount, String orderId) {
        this.payTotal = payTotal;
        this.idCard = idCard;
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.ticketCount = ticketCount;
        this.orderId = orderId;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketCount() {
        return ticketCount;
    }

    public void setTicketCount(String ticketCount) {
        this.ticketCount = ticketCount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
