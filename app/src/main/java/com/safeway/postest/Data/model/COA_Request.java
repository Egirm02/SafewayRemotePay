
package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class COA_Request {

    @SerializedName("clubcard")
    @Expose
    private String clubcard;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("refundAmount")
    @Expose
    private Double refundAmount;

    public COA_Request(String clubcard, String orderId, Double refundAmount){
        this.clubcard = clubcard;
        this.orderId = orderId;
        this.refundAmount = refundAmount;
    }
    public String getClubcard() {
        return clubcard;
    }

    public void setClubcard(String clubcard) {
        this.clubcard = clubcard;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(Double refundAmount) {
        this.refundAmount = refundAmount;
    }

}
