
package com.safeway.postest.Data.model.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Timeout {

    @SerializedName("default")
    @Expose
    private Integer _default;

    public Integer getDefault() {
        return _default;
    }

    public void setDefault(Integer _default) {
        this._default = _default;
    }

}
