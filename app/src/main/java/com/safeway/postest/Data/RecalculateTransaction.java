package com.safeway.postest.Data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecalculateTransaction {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("transactionInfoList")
    @Expose
    private List<Transactions> transactionInfoList = null;

    public RecalculateTransaction(String orderId, List<Transactions> transactionInfo){
        this.orderId = orderId;
        this.transactionInfoList = transactionInfo;
    }


    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Transactions> getTransactions() {
        return transactionInfoList;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactionInfoList = transactions;
    }

}
