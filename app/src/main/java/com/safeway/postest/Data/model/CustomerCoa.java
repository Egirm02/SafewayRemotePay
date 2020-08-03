
package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerCoa {

    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("coaBalance")
    @Expose
    private Double coaBalance;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Double getCoaBalance() {
        return coaBalance;
    }

    public void setCoaBalance(Double coaBalance) {
        this.coaBalance = coaBalance;
    }

}
