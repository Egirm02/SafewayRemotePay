
package com.safeway.postest.Data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppSettings {

    @SerializedName("timeout")
    @Expose
    private Timeout timeout;
    @SerializedName("_id")
    @Expose
    private String _id;
    @SerializedName("scanditLicenseKey")
    @Expose
    private String scanditLicenseKey;
    @SerializedName("backupScanditKey")
    @Expose
    private String backupScanditKey;
    @SerializedName("OKTAClientId")
    @Expose
    private String oKTAClientId;
    @SerializedName("OKTASecret")
    @Expose
    private String oKTASecret;
    @SerializedName("OKTAAuthorizationId")
    @Expose
    private String oKTAAuthorizationId;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("forceUpdateFlag")
    @Expose
    private Boolean forceUpdateFlag;
    @SerializedName("app")
    @Expose
    private String app;
    @SerializedName("tncURL")
    @Expose
    private String tncURL;
    @SerializedName("splTncURL")
    @Expose
    private String splTncURL;
    @SerializedName("updateMessage")
    @Expose
    private String updateMessage;
    @SerializedName("azureSubscriptionKey")
    @Expose
    private String azureSubscriptionKey;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("message")
    @Expose
    private Boolean message;

    public Timeout getTimeout() {
        return timeout;
    }

    public void setTimeout(Timeout timeout) {
        this.timeout = timeout;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getScanditLicenseKey() {
        return scanditLicenseKey;
    }

    public void setScanditLicenseKey(String scanditLicenseKey) {
        this.scanditLicenseKey = scanditLicenseKey;
    }

    public String getBackupScanditKey() {
        return backupScanditKey;
    }

    public void setBackupScanditKey(String backupScanditKey) {
        this.backupScanditKey = backupScanditKey;
    }

    public String getOKTAClientId() {
        return oKTAClientId;
    }

    public void setOKTAClientId(String oKTAClientId) {
        this.oKTAClientId = oKTAClientId;
    }

    public String getOKTASecret() {
        return oKTASecret;
    }

    public void setOKTASecret(String oKTASecret) {
        this.oKTASecret = oKTASecret;
    }

    public String getOKTAAuthorizationId() {
        return oKTAAuthorizationId;
    }

    public void setOKTAAuthorizationId(String oKTAAuthorizationId) {
        this.oKTAAuthorizationId = oKTAAuthorizationId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Boolean getForceUpdateFlag() {
        return forceUpdateFlag;
    }

    public void setForceUpdateFlag(Boolean forceUpdateFlag) {
        this.forceUpdateFlag = forceUpdateFlag;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getTncURL() {
        return tncURL;
    }

    public void setTncURL(String tncURL) {
        this.tncURL = tncURL;
    }

    public String getSplTncURL() {
        return splTncURL;
    }

    public void setSplTncURL(String splTncURL) {
        this.splTncURL = splTncURL;
    }

    public String getUpdateMessage() {
        return updateMessage;
    }

    public void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    public String getAzureSubscriptionKey() {
        return azureSubscriptionKey;
    }

    public void setAzureSubscriptionKey(String azureSubscriptionKey) {
        this.azureSubscriptionKey = azureSubscriptionKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getMessage() {
        return message;
    }

    public void setMessage(Boolean message) {
        this.message = message;
    }

}
