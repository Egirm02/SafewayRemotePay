
package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Stores {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("postCode")
    @Expose
    private String postCode;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phoneNumber")
    @Expose
    private String phoneNumber;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("fuelHours")
    @Expose
    private String fuelHours;
    @SerializedName("storeId")
    @Expose
    private String storeId;
    @SerializedName("userSupportLevel")
    @Expose
    private String userSupportLevel;
    @SerializedName("sngEnabled")
    @Expose
    private Boolean sngEnabled;
    @SerializedName("sngStoreHours")
    @Expose
    private String sngStoreHours;
    @SerializedName("storeRadius")
    @Expose
    private Integer storeRadius;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getFuelHours() {
        return fuelHours;
    }

    public void setFuelHours(String fuelHours) {
        this.fuelHours = fuelHours;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public String getSngStoreHours() {
        return sngStoreHours;
    }

    public void setSngStoreHours(String sngStoreHours) {
        this.sngStoreHours = sngStoreHours;
    }

    public Integer getStoreRadius() {
        return storeRadius;
    }

    public void setStoreRadius(Integer storeRadius) {
        this.storeRadius = storeRadius;
    }

}
