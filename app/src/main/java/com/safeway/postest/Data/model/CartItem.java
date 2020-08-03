package com.safeway.postest.Data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CartItem extends BaseObservable {
//
//    @SerializedName("_id")
//    @Expose
//    private Id _id;

//    @SerializedName("item")
//    @Expose
//    private Item item;

    @SerializedName("items")
    @Expose
    private List<Item> item = null;

    @SerializedName("quantity")
    @Expose
    private int quantity;

    @SerializedName("upc_type")
    @Expose
    private String upcType;


    @SerializedName("weight_item")
    @Expose
    private boolean weightItem;

    @SerializedName("weight")
    @Expose
    private double weight;

    @SerializedName("scan_code")
    @Expose
    private String scanCode;

    public String getScanCode() {
        return scanCode;
    }

    public void setScanCode(String scanCode) {
        this.scanCode = scanCode;
    }

    public boolean isWeightItem() {
        return weightItem;
    }

    public void setWeightItem(boolean weightItem) {
        this.weightItem = weightItem;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    private boolean editable;

    private boolean isSelected;


    CartItem() {

    }

    public String getUpcType() {
        return upcType;
    }

    public void setUpcType(String upcType) {
        this.upcType = upcType;
    }

    //    public void set_id(Id _id) {
//        this._id = _id;
//    }
    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

//    public Id get_id() {
//        return _id;
//    }

    public void setEditable(boolean editable) {
        this.editable = editable;
       // notifyPropertyChanged(com.safeway.scanandgo.BR.editable);
    }

    @Bindable
    public boolean getEditable() {
        return editable;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


}
