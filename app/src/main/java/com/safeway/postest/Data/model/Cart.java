package com.safeway.postest.Data.model;

import androidx.databinding.BaseObservable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Cart extends BaseObservable {

    @SerializedName("_id")
    private String _id;

    @SerializedName("items")
    private List<Items> items;


    @SerializedName("total_quantity")
    private int totalQuantity;


    @SerializedName("total_price")
    private double totalPrice;

    @SerializedName("time_stamp")
    private Long timeStamp;

    @SerializedName("transaction_status")
    private String transactionStatus;

    private boolean deleteMode;

    public Cart() {
    }

    public Cart(List<Items> data) {
        this.items = data;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setData(List<Items> data) {
        this.items = data;
    }

    public List<Items> getData() {
        return items;
    }

    public String get_id() {
        return _id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public void setDeleteMode(boolean deleteMode) {
        this.deleteMode = deleteMode;
    }

//    public boolean getAllChecked() {
//        if (!this.deleteMode) return false;
//        List<Item> listCartItem = this.data;
//        for (Item cartItem : listCartItem) {
//            if (cartItem.)
//                return false;
//        }
//        return true;
//
//    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "data=" + items +
                ", totalQuantity=" + totalQuantity +
                ", totalPrice=" + totalPrice +
                ", timeStamp=" + timeStamp +
                ", deleteMode=" + deleteMode +
                '}';
    }

}
