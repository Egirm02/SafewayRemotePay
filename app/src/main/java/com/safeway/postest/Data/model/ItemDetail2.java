package com.safeway.postest.Data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemDetail2 {

    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("scan_code")
    @Expose
    private String scanCode;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("weight_item")
    @Expose
    private Boolean weightItem;
    @SerializedName("sell_price")
    @Expose
    private Integer sellPrice;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("restricted_item")
    @Expose
    private Boolean restrictedItem;




    public ItemDetail2(String itemId, String scanCode, Integer quantity, Boolean weightItem, Integer sellPrice, String dept,Boolean restrictedItem){
        this.itemId = itemId;
        this.scanCode = scanCode;
        this.quantity=quantity;
        this.weightItem=weightItem;
        this.sellPrice=sellPrice;
        this.dept=dept;
        this.restrictedItem=restrictedItem;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getWeightItem() {
        return weightItem;
    }

    public void setWeightItem(Boolean weightItem) {
        this.weightItem = weightItem;
    }

    public Integer getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Integer sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Boolean getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(Boolean restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

}
