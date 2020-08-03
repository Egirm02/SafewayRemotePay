package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.Transactions;

import java.util.List;

public class FinalizeSplitTransaction {

    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("transactionInfoList")
    @Expose
    private List<Transactions> transactions = null;

    public FinalizeSplitTransaction(String orderId, List<Transactions> transactions) {
        this.orderId = orderId;
        this.transactions = transactions;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Transactions> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transactions> transactions) {
        this.transactions = transactions;
    }

}
