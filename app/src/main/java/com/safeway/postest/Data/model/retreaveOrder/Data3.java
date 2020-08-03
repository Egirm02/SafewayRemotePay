
package com.safeway.postest.Data.model.retreaveOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.model.receipt.Id;
import com.safeway.postest.Data.model.receipt.TimeStamp;

public class Data3 {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("checkout")
    @Expose
    private Boolean checkout;
    @SerializedName("clover_vpos")
    @Expose
    private Boolean cloverVpos;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("terminal_number")
    @Expose
    private String terminalNumber;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("time_stamp")
    @Expose
    private TimeStamp timeStamp;
    @SerializedName("_id")
    @Expose
    private Id id;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }

    public Boolean getCloverVpos() {
        return cloverVpos;
    }

    public void setCloverVpos(Boolean cloverVpos) {
        this.cloverVpos = cloverVpos;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

}
