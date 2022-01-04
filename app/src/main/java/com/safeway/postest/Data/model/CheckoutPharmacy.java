package com.safeway.postest.Data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.Transactions;


public class CheckoutPharmacy {

    @SerializedName("item_details")
    @Expose
    private List<ItemDetail2> itemDetails = null;
    @SerializedName("transaction_details")
    @Expose
    private TransactionDetails transactionDetails;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("store_id")
    @Expose
    private String storeId;

    public CheckoutPharmacy(List<ItemDetail2> itemDetails, TransactionDetails transactionDetails, Integer itemCount, String storeId){
        this.itemDetails = itemDetails;
        this.transactionDetails = transactionDetails;
        this.itemCount=itemCount;
        this.storeId=storeId;
    }

    public List<ItemDetail2> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail2> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public TransactionDetails getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(TransactionDetails transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

}