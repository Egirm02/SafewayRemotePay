
package com.safeway.postest.Data.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("pos_description")
    @Expose
    private String posDescription;
    @SerializedName("ext_description")
    @Expose
    private String extDescription;
    @SerializedName("sell_multiple")
    @Expose
    private Integer sellMultiple;
    @SerializedName("sell_price")
    @Expose
    private Double sellPrice;
    @SerializedName("crv")
    @Expose
    private Double crv;
    @SerializedName("link_code")
    @Expose
    private String linkCode;
    @SerializedName("food_stamp")
    @Expose
    private Boolean foodStamp;
    @SerializedName("restricted_item")
    @Expose
    private Boolean restrictedItem;
    @SerializedName("ewic")
    @Expose
    private Boolean ewic;
    @SerializedName("weight_item")
    @Expose
    private Boolean weightItem;
    @SerializedName("dept")
    @Expose
    private Integer dept;
    @SerializedName("tax_item")
    @Expose
    private Boolean taxItem;
    @SerializedName("club_item")
    @Expose
    private Boolean clubItem;
    @SerializedName("smic")
    @Expose
    private Integer smic;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("bpn_no")
    @Expose
    private String bpnNo;
    @SerializedName("clubPrice")
    @Expose
    private List<Object> clubPrice = null;
    @SerializedName("regular_price")
    @Expose
    private Double regularPrice;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("jfuOfferCount")
    @Expose
    private Integer jfuOfferCount;
    @SerializedName("jfuOffers")
    @Expose
    private List<JfuOffer> jfuOffers = null;
    @SerializedName("removed_item")
    @Expose
    private Boolean removedItem;
    @SerializedName("quantity")
    @Expose
    private int quantity;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPosDescription() {
        return posDescription;
    }

    public void setPosDescription(String posDescription) {
        this.posDescription = posDescription;
    }

    public String getExtDescription() {
        return extDescription;
    }

    public void setExtDescription(String extDescription) {
        this.extDescription = extDescription;
    }

    public Integer getSellMultiple() {
        return sellMultiple;
    }

    public void setSellMultiple(Integer sellMultiple) {
        this.sellMultiple = sellMultiple;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getCrv() {
        return crv;
    }

    public void setCrv(Double crv) {
        this.crv = crv;
    }

    public String getLinkCode() {
        return linkCode;
    }

    public void setLinkCode(String linkCode) {
        this.linkCode = linkCode;
    }

    public Boolean getFoodStamp() {
        return foodStamp;
    }

    public void setFoodStamp(Boolean foodStamp) {
        this.foodStamp = foodStamp;
    }

    public Boolean getRestrictedItem() {
        return restrictedItem;
    }

    public void setRestrictedItem(Boolean restrictedItem) {
        this.restrictedItem = restrictedItem;
    }

    public Boolean getEwic() {
        return ewic;
    }

    public void setEwic(Boolean ewic) {
        this.ewic = ewic;
    }

    public Boolean getWeightItem() {
        return weightItem;
    }

    public void setWeightItem(Boolean weightItem) {
        this.weightItem = weightItem;
    }

    public Integer getDept() {
        return dept;
    }

    public void setDept(Integer dept) {
        this.dept = dept;
    }

    public Boolean getTaxItem() {
        return taxItem;
    }

    public void setTaxItem(Boolean taxItem) {
        this.taxItem = taxItem;
    }

    public Boolean getClubItem() {
        return clubItem;
    }

    public void setClubItem(Boolean clubItem) {
        this.clubItem = clubItem;
    }

    public Integer getSmic() {
        return smic;
    }

    public void setSmic(Integer smic) {
        this.smic = smic;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getBpnNo() {
        return bpnNo;
    }

    public void setBpnNo(String bpnNo) {
        this.bpnNo = bpnNo;
    }

    public List<Object> getClubPrice() {
        return clubPrice;
    }

    public void setClubPrice(List<Object> clubPrice) {
        this.clubPrice = clubPrice;
    }

    public Double getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(Double regularPrice) {
        this.regularPrice = regularPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getJfuOfferCount() {
        return jfuOfferCount;
    }

    public void setJfuOfferCount(Integer jfuOfferCount) {
        this.jfuOfferCount = jfuOfferCount;
    }

    public List<JfuOffer> getJfuOffers() {
        return jfuOffers;
    }

    public void setJfuOffers(List<JfuOffer> jfuOffers) {
        this.jfuOffers = jfuOffers;
    }

    public Boolean getRemovedItem() {
        return removedItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
