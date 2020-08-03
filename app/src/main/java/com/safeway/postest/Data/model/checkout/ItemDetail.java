
package com.safeway.postest.Data.model.checkout;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.model.ClubPrice;
import com.safeway.postest.Data.model.JfuOffers;

public class ItemDetail {

    @SerializedName("added_time_stamp")
    @Expose
    private Long addedTimeStamp;
//    @SerializedName("last_updated_timestamp")
//    @Expose
//    private Long lastUpdatedTimestamp;
    @SerializedName("last_updated_timestamp")
    @Expose
    private LastUpdatedTimestamp lastUpdatedTimestamp;
    @SerializedName("_id")
    @Expose
    private Id_ id;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("upc_type")
    @Expose
    private String upcType;
    @SerializedName("scan_code")
    @Expose
    private String scanCode;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("bag_item")
    @Expose
    private Boolean bagItem;
    @SerializedName("weight_item")
    @Expose
    private Boolean weightItem;
    @SerializedName("clubPrice")
    @Expose
    private List<ClubPrice> clubPrice = null;
    @SerializedName("jfuOffers")
    @Expose
    private List<JfuOffers> jfuOffers = null;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("sell_price")
    @Expose
    private Double sellPrice;
    @SerializedName("dept")
    @Expose
    private String dept;
    @SerializedName("ext_description")
    @Expose
    private String extDescription;
    @SerializedName("pos_description")
    @Expose
    private String posDescription;
    @SerializedName("restricted_item")
    @Expose
    private Boolean restrictedItem;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;

    public Long getAddedTimeStamp() {
        return addedTimeStamp;
    }

    public void setAddedTimeStamp(Long addedTimeStamp) {
        this.addedTimeStamp = addedTimeStamp;
    }

    public LastUpdatedTimestamp getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(LastUpdatedTimestamp lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public Id_ getId() {
        return id;
    }

    public void setId(Id_ id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUpcType() {
        return upcType;
    }

    public void setUpcType(String upcType) {
        this.upcType = upcType;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getBagItem() {
        return bagItem;
    }

    public void setBagItem(Boolean bagItem) {
        this.bagItem = bagItem;
    }

    public Boolean getWeightItem() {
        return weightItem;
    }

    public void setWeightItem(Boolean weightItem) {
        this.weightItem = weightItem;
    }

    public List<ClubPrice> getClubPrice() {
        return clubPrice;
    }

    public void setClubPrice(List<ClubPrice> clubPrice) {
        this.clubPrice = clubPrice;
    }

    public List<JfuOffers> getJfuOffers() {
        return jfuOffers;
    }

    public void setJfuOffers(List<JfuOffers> jfuOffers) {
        this.jfuOffers = jfuOffers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getExtDescription() {
        return extDescription;
    }

    public void setExtDescription(String extDescription) {
        this.extDescription = extDescription;
    }

    public String getPosDescription() {
        return posDescription;
    }

    public void setPosDescription(String posDescription) {
        this.posDescription = posDescription;
    }

    public Boolean getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(Boolean restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
