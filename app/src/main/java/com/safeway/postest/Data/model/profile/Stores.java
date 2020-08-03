
package com.safeway.postest.Data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("divName")
    @Expose
    private String divName;
    @SerializedName("divNumber")
    @Expose
    private String divNumber;
    @SerializedName("bagFeePLU")
    @Expose
    private String bagFeePLU;
    @SerializedName("storeRadius")
    @Expose
    private Integer storeRadius;
    @SerializedName("userSupportLevel")
    @Expose
    private String userSupportLevel;
    @SerializedName("sngEnabled")
    @Expose
    private Boolean sngEnabled;
    @SerializedName("restrictedItemsAllowed")
    @Expose
    private Boolean restrictedItemsAllowed;
    @SerializedName("sngStoreHours")
    @Expose
    private String sngStoreHours;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getDivNumber() {
        return divNumber;
    }

    public void setDivNumber(String divNumber) {
        this.divNumber = divNumber;
    }

    public String getBagFeePLU() {
        return bagFeePLU;
    }

    public void setBagFeePLU(String bagFeePLU) {
        this.bagFeePLU = bagFeePLU;
    }

    public Integer getStoreRadius() {
        return storeRadius;
    }

    public void setStoreRadius(Integer storeRadius) {
        this.storeRadius = storeRadius;
    }

    public String getUserSupportLevel() {
        return userSupportLevel;
    }

    public void setUserSupportLevel(String userSupportLevel) {
        this.userSupportLevel = userSupportLevel;
    }

    public Boolean getSngEnabled() {
        return sngEnabled;
    }

    public void setSngEnabled(Boolean sngEnabled) {
        this.sngEnabled = sngEnabled;
    }

    public Boolean getRestrictedItemsAllowed() {
        return restrictedItemsAllowed;
    }

    public void setRestrictedItemsAllowed(Boolean restrictedItemsAllowed) {
        this.restrictedItemsAllowed = restrictedItemsAllowed;
    }

    public String getSngStoreHours() {
        return sngStoreHours;
    }

    public void setSngStoreHours(String sngStoreHours) {
        this.sngStoreHours = sngStoreHours;
    }

}
