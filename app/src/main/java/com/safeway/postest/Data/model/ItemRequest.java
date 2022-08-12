package com.safeway.postest.Data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ItemRequest {

    private String item_id;

    private int quantity;

    private String upc_type;

    private Double weight;

    private String scan_code;

    @SerializedName("clubPrice")
    private List<ClubPrice> clubPrice;

    @SerializedName("jfuOffers")
    private List<JfuOffers> jfuOffers;

//    private Boolean bag_item;

    public ItemRequest(String item_id, int quantity, String upcType, String scan_code, Boolean bag_item) {
        this.item_id = item_id;
        this.quantity = quantity;
        this.upc_type = upcType;
        this.scan_code = scan_code;
//        this.bag_item = bag_item;
    }

    public ItemRequest(String item_id, int quantity, String upcType, String scancode, Boolean bag_item, List<ClubPrice> clubPrice, List<JfuOffers> jfuOffers) {
        this.item_id = item_id;
        this.quantity = quantity;
        this.upc_type = upcType;
        this.scan_code = scancode;
//        this.bag_item = bag_item;
        this.clubPrice = clubPrice;
        this.jfuOffers = jfuOffers;
    }

    public ItemRequest(String item_id, double weight, String upcType, String scancode, Boolean bag_item, List<ClubPrice> clubPrice, List<JfuOffers> jfuOffers) {
        this.item_id = item_id;
        this.weight = weight;
        this.upc_type = upcType;
        this.scan_code = scancode;
//        this.bag_item = bag_item;
        this.clubPrice= clubPrice;
        this.jfuOffers = jfuOffers;
    }

    public ItemRequest(String item_id, int quantity, String upcType, String scancode, Boolean bag_item, List<JfuOffers> jfuOffers) {
        this.item_id = item_id;
        this.quantity = quantity;
        this.upc_type = upcType;
        this.scan_code = scancode;
//        this.bag_item = bag_item;
        this.jfuOffers = jfuOffers;
    }

    public ItemRequest(String item_id, double weight, String upcType, String scancode, Boolean bag_item, List<JfuOffers> jfuOffers) {
        this.item_id = item_id;
        this.weight = weight;
        this.upc_type = upcType;
        this.scan_code = scancode;
//        this.bag_item = bag_item;
        this.jfuOffers = jfuOffers;
    }


    public ItemRequest(String item_id, double weight, String upcType, int quantity, String scan_code) {
        this.item_id = item_id;
        this.weight = weight;
        this.upc_type = upcType;
        this.scan_code = scan_code;
        this.quantity = quantity;
    }

    public String getScancode() {
        return scan_code;
    }

    public void setScancode(String scancode) {
        this.scan_code = scancode;
    }

//    public Boolean getBag_item(){
//        return bag_item;
//    }
//
//    public void  setBag_item(Boolean bag_item){
//        this.bag_item = bag_item;
//    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getItem_id() {
        return item_id;
    }

    public String getUpcType() {
        return upc_type;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setUpcType(String upcType) {
        this.upc_type = upcType;
    }

    public String getScan_code() {
        return scan_code;
    }

    public void setScan_code(String scan_code) {
        this.scan_code = scan_code;
    }

    public List<ClubPrice> getClubPrice() {
        return clubPrice;
    }

    public void setClubPrice(List<ClubPrice>  clubPrice) {
        this.clubPrice = clubPrice;
    }

    public List<JfuOffers> getJfuOffers() {
        return jfuOffers;
    }

    public void setJfuOffers(List<JfuOffers>  jfuOffers) {
        this.jfuOffers = jfuOffers;
    }



    @Override
    public String toString() {
        return "ItemRequest{" +
                "item_id='" + item_id + '\'' +
                ", quantity=" + quantity +
                ", upcType='" + upc_type + '\'' +
//                ", bag_item='" + bag_item+'\''+
                ", clubPrice=" + clubPrice + '\'' +
                ", jfuOffers=" + jfuOffers + '\'' +
                '}';
    }

}
