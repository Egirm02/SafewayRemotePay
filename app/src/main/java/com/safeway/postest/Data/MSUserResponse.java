package com.safeway.postest.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MSUserResponse {
    @SerializedName("@odata.context")
    @Expose
    private String odataContext;
    @SerializedName("businessPhones")
    @Expose
    private List<Object> businessPhones = null;
    @SerializedName("displayName")
    @Expose
    private String displayName;
    @SerializedName("givenName")
    @Expose
    private String givenName;
    @SerializedName("jobTitle")
    @Expose
    private Object jobTitle;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("mobilePhone")
    @Expose
    private Object mobilePhone;
    @SerializedName("officeLocation")
    @Expose
    private Object officeLocation;
    @SerializedName("preferredLanguage")
    @Expose
    private Object preferredLanguage;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("userPrincipalName")
    @Expose
    private String userPrincipalName;
    @SerializedName("id")
    @Expose
    private String id;

    public String getOdataContext() {
        return odataContext;
    }

    public void setOdataContext(String odataContext) {
        this.odataContext = odataContext;
    }

    public List<Object> getBusinessPhones() {
        return businessPhones;
    }

    public void setBusinessPhones(List<Object> businessPhones) {
        this.businessPhones = businessPhones;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public Object getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(Object jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Object getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(Object mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Object getOfficeLocation() {
        return officeLocation;
    }

    public void setOfficeLocation(Object officeLocation) {
        this.officeLocation = officeLocation;
    }

    public Object getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(Object preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserPrincipalName() {
        return userPrincipalName;
    }

    public void setUserPrincipalName(String userPrincipalName) {
        this.userPrincipalName = userPrincipalName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}