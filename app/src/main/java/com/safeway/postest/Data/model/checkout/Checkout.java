
package com.safeway.postest.Data.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Checkout {

    @SerializedName("ack")
    @Expose
    private String ack;
    @SerializedName("data")
    @Expose
    private Data2 data;
    @SerializedName("messsage")
    @Expose
    private String messsage;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Data2 getData() {
        return data;
    }

    public void setData(Data2 data) {
        this.data = data;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

}
