
package com.safeway.postest.Data.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class JfuOffer {

    @SerializedName("offerId")
    @Expose
    private String offerId;

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

}
