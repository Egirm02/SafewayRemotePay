package com.safeway.postest.Data.model;

import java.io.Serializable;

public class ErrorMessage implements Serializable {
    private String message;

    private String code;

    private String type;

    private String category;

    private String vendor;

    public ErrorMessage() {

    }

    public ErrorMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }


    @Override
    public String toString()
    {
        return "ClassPojo [message = "+message+", code = "+code+", type = "+type+" , category = "+category+" , vendor = "+vendor+"]";
    }
}
