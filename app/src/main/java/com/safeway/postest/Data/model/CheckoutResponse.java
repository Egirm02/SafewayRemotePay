package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.model.receipt.Id;

import java.io.Serializable;
import java.util.List;

public class CheckoutResponse implements Serializable {
    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("item_details")
    @Expose
    private List<Item2> itemDetails = null;
    @SerializedName("checkout")
    @Expose
    private Boolean checkout;
    @SerializedName("vpos")
    @Expose
    private Boolean vpos;
    @SerializedName("device_id")
    @Expose
    private Object deviceId;
    @SerializedName("platform")
    @Expose
    private Object platform;
    @SerializedName("version")
    @Expose
    private Object version;
    @SerializedName("clubcard_nbr")
    @Expose
    private Object clubCardNbr;
    @SerializedName("guid")
    @Expose
    private String guid;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<Item2> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<Item2> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }

    public Boolean getVpos() {
        return vpos;
    }

    public void setVpos(Boolean vpos) {
        this.vpos = vpos;
    }

    public Object getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Object deviceId) {
        this.deviceId = deviceId;
    }

    public Object getPlatform() {
        return platform;
    }

    public void setPlatform(Object platform) {
        this.platform = platform;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(Object version) {
        this.version = version;
    }

    public Object getClubCardNbr() {
        return clubCardNbr;
    }

    public void setClubCardNbr(Object clubCardNbr) {
        this.clubCardNbr = clubCardNbr;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public String toString() {
        return "CheckoutResponse{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", itemDetails=" + itemDetails +
                ", checkout=" + checkout +
                ", vpos=" + vpos +
                ", deviceId=" + deviceId +
                ", platform=" + platform +
                ", version=" + version +
                ", clubCardNbr=" + clubCardNbr +
                ", guid='" + guid + '\'' +
                '}';
    }
}
