package com.safeway.postest.Data.model.checkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LastUpdatedTimestamp {
    @SerializedName("$numberLong")
    @Expose
    private String $numberLong;

    public String get$numberLong() {
        return $numberLong;
    }

    public void set$numberLong(String $numberLong) {
        this.$numberLong = $numberLong;
    }

}
