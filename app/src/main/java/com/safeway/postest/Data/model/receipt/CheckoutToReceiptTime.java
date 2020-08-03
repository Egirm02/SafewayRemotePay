
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckoutToReceiptTime {

    @SerializedName("$numberLong")
    @Expose
    private String $numberLong;

    /**
     * No args constructor for use in serialization
     * 
     */
    public CheckoutToReceiptTime() {
    }

    /**
     * 
     * @param $numberLong
     */
    public CheckoutToReceiptTime(String $numberLong) {
        super();
        this.$numberLong = $numberLong;
    }

    public String get$numberLong() {
        return $numberLong;
    }

    public void set$numberLong(String $numberLong) {
        this.$numberLong = $numberLong;
    }

    public CheckoutToReceiptTime with$numberLong(String $numberLong) {
        this.$numberLong = $numberLong;
        return this;
    }

}
