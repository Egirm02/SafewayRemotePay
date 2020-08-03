
package com.safeway.postest.Data.model.retreaveOrder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrieveOrder {

    @SerializedName("ack")
    @Expose
    private String ack;
    @SerializedName("data")
    @Expose
    private Data3 data;
    @SerializedName("message")
    @Expose
    private String message;

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Data3 getData() {
        return data;
    }

    public void setData(Data3 data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
