
package com.safeway.postest.Data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataProfile {

    @SerializedName("sngStoreHours")
    @Expose
    private Boolean sngStoreHours;
    @SerializedName("customerdetails")
    @Expose
    private Customerdetails customerdetails;
    @SerializedName("stores")
    @Expose
    private Stores stores;
    @SerializedName("appSettings")
    @Expose
    private AppSettings appSettings;

    public Boolean getSngStoreHours() {
        return sngStoreHours;
    }

    public void setSngStoreHours(Boolean sngStoreHours) {
        this.sngStoreHours = sngStoreHours;
    }

    public Customerdetails getCustomerdetails() {
        return customerdetails;
    }

    public void setCustomerdetails(Customerdetails customerdetails) {
        this.customerdetails = customerdetails;
    }

    public Stores getStores() {
        return stores;
    }

    public void setStores(Stores stores) {
        this.stores = stores;
    }

    public AppSettings getAppSettings() {
        return appSettings;
    }

    public void setAppSettings(AppSettings appSettings) {
        this.appSettings = appSettings;
    }

}
