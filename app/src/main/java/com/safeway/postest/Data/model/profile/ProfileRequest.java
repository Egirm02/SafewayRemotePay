package com.safeway.postest.Data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileRequest {

    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("longitude")
    @Expose
    private Double longitude;
    @SerializedName("app")
    @Expose
    private String app;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("latitude")
    @Expose
    private Double latitude;
    @SerializedName("GUID")
    @Expose
    private String gUID;
    @SerializedName("version")
    @Expose
    private String version;

    public ProfileRequest() {
        this("safeway", -122.815, "scanandgo", "android", "testrqa@safeway.com", 33.706, "test113", "1.0.0");
    }

    public ProfileRequest(
            String banner, Double longitude, String app, String platform, String email, Double latitude, String gUID, String version) {
        this.banner = banner;
        this.longitude = longitude;
        this.app = app;
        this.platform = platform;
        this.email = email;
        this.latitude = latitude;
        this.gUID = gUID;
        this.version = version;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getGUID() {
        return gUID;
    }

    public void setGUID(String gUID) {
        this.gUID = gUID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


}

