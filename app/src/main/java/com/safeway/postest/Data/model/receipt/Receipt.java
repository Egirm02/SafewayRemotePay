
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Receipt {

    @SerializedName("ack")
    @Expose
    private String ack;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("messsage")
    @Expose
    private String messsage;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Receipt() {
    }

    /**
     * 
     * @param data
     * @param ack
     * @param messsage
     */
    public Receipt(String ack, Data data, String messsage) {
        super();
        this.ack = ack;
        this.data = data;
        this.messsage = messsage;
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public Receipt withAck(String ack) {
        this.ack = ack;
        return this;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Receipt withData(Data data) {
        this.data = data;
        return this;
    }

    public String getMesssage() {
        return messsage;
    }

    public void setMesssage(String messsage) {
        this.messsage = messsage;
    }

    public Receipt withMesssage(String messsage) {
        this.messsage = messsage;
        return this;
    }

}
