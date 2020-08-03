
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//public class ReceiptItemDetailsList {
//
//    @SerializedName("extendedPrice")
//    @Expose
//    private Double extendedPrice;
//    @SerializedName("deliveredQty")
//    @Expose
//    private String deliveredQty;
//    @SerializedName("shortDescription")
//    @Expose
//    private String shortDescription;
//    @SerializedName("departmentNumber")
//    @Expose
//    private String departmentNumber;
//    @SerializedName("itemType")
//    @Expose
//    private String itemType;
//    @SerializedName("unitPrice")
//    @Expose
//    private Double unitPrice;
//    @SerializedName("itemId")
//    @Expose
//    private String itemId;
//    @SerializedName("departmentName")
//    @Expose
//    private String departmentName;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public ReceiptItemDetailsList() {
//    }
//
//    /**
//     *
//     * @param deliveredQty
//     * @param unitPrice
//     * @param departmentName
//     * @param itemId
//     * @param itemType
//     * @param extendedPrice
//     * @param departmentNumber
//     * @param shortDescription
//     */
//    public ReceiptItemDetailsList(Double extendedPrice, String deliveredQty, String shortDescription, String departmentNumber, String itemType, Double unitPrice, String itemId, String departmentName) {
//        super();
//        this.extendedPrice = extendedPrice;
//        this.deliveredQty = deliveredQty;
//        this.shortDescription = shortDescription;
//        this.departmentNumber = departmentNumber;
//        this.itemType = itemType;
//        this.unitPrice = unitPrice;
//        this.itemId = itemId;
//        this.departmentName = departmentName;
//    }
//
//    public Double getExtendedPrice() {
//        return extendedPrice;
//    }
//
//    public void setExtendedPrice(Double extendedPrice) {
//        this.extendedPrice = extendedPrice;
//    }
//
//    public ReceiptItemDetailsList withExtendedPrice(Double extendedPrice) {
//        this.extendedPrice = extendedPrice;
//        return this;
//    }
//
//    public String getDeliveredQty() {
//        return deliveredQty;
//    }
//
//    public void setDeliveredQty(String deliveredQty) {
//        this.deliveredQty = deliveredQty;
//    }
//
//    public ReceiptItemDetailsList withDeliveredQty(String deliveredQty) {
//        this.deliveredQty = deliveredQty;
//        return this;
//    }
//
//    public String getShortDescription() {
//        return shortDescription;
//    }
//
//    public void setShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//    }
//
//    public ReceiptItemDetailsList withShortDescription(String shortDescription) {
//        this.shortDescription = shortDescription;
//        return this;
//    }
//
//    public String getDepartmentNumber() {
//        return departmentNumber;
//    }
//
//    public void setDepartmentNumber(String departmentNumber) {
//        this.departmentNumber = departmentNumber;
//    }
//
//    public ReceiptItemDetailsList withDepartmentNumber(String departmentNumber) {
//        this.departmentNumber = departmentNumber;
//        return this;
//    }
//
//    public String getItemType() {
//        return itemType;
//    }
//
//    public void setItemType(String itemType) {
//        this.itemType = itemType;
//    }
//
//    public ReceiptItemDetailsList withItemType(String itemType) {
//        this.itemType = itemType;
//        return this;
//    }
//
//    public Double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(Double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public ReceiptItemDetailsList withUnitPrice(Double unitPrice) {
//        this.unitPrice = unitPrice;
//        return this;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public ReceiptItemDetailsList withItemId(String itemId) {
//        this.itemId = itemId;
//        return this;
//    }
//
//    public String getDepartmentName() {
//        return departmentName;
//    }
//
//    public void setDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//    }
//
//    public ReceiptItemDetailsList withDepartmentName(String departmentName) {
//        this.departmentName = departmentName;
//        return this;
//    }
//
//}

public class ReceiptItemDetailsList {

    @SerializedName("extendedPrice")
    @Expose
    private Double extendedPrice;
    @SerializedName("deliveredQty")
    @Expose
    private Double deliveredQty;
    @SerializedName("shortDescription")
    @Expose
    private String shortDescription;
    @SerializedName("departmentNumber")
    @Expose
    private String departmentNumber;
    @SerializedName("taxableFlag")
    @Expose
    private Boolean taxableFlag;
    @SerializedName("foodStamp")
    @Expose
    private Boolean foodStamp;
    @SerializedName("itemType")
    @Expose
    private String itemType;
    @SerializedName("unitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("restrictedItem")
    @Expose
    private Boolean restrictedItem;
    @SerializedName("itemId")
    @Expose
    private String itemId;
    @SerializedName("departmentName")
    @Expose
    private String departmentName;

    public Double getExtendedPrice() {
        return extendedPrice;
    }

    public void setExtendedPrice(Double extendedPrice) {
        this.extendedPrice = extendedPrice;
    }

    public Double getDeliveredQty() {
        return deliveredQty;
    }

    public void setDeliveredQty(Double deliveredQty) {
        this.deliveredQty = deliveredQty;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDepartmentNumber() {
        return departmentNumber;
    }

    public void setDepartmentNumber(String departmentNumber) {
        this.departmentNumber = departmentNumber;
    }

    public Boolean getTaxableFlag() {
        return taxableFlag;
    }

    public void setTaxableFlag(Boolean taxableFlag) {
        this.taxableFlag = taxableFlag;
    }

    public Boolean getFoodStamp() {
        return foodStamp;
    }

    public void setFoodStamp(Boolean foodStamp) {
        this.foodStamp = foodStamp;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Boolean getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(Boolean restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

}
