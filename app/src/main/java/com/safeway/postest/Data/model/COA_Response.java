
package com.safeway.postest.Data.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class COA_Response {

    @SerializedName("coaEntryIds")
    @Expose
    private List<String> coaEntryIds = null;
    @SerializedName("customerCoa")
    @Expose
    private CustomerCoa customerCoa;

    public List<String> getCoaEntryIds() {
        return coaEntryIds;
    }

    public void setCoaEntryIds(List<String> coaEntryIds) {
        this.coaEntryIds = coaEntryIds;
    }

    public CustomerCoa getCustomerCoa() {
        return customerCoa;
    }

    public void setCustomerCoa(CustomerCoa customerCoa) {
        this.customerCoa = customerCoa;
    }

}
