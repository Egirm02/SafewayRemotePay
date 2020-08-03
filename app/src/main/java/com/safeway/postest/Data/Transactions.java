package com.safeway.postest.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Transactions {

    @SerializedName("card_pan_print")
    @Expose
    private String cardPanPrint;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    @SerializedName("approval_number")
    @Expose
    private String approvalNumber;
    @SerializedName("transaction_amount")
    @Expose
    private String transactionAmount;
    @SerializedName("ebt_flag")
    @Expose
    private Boolean ebtFlag;
    @SerializedName("transaction_complete_at")
    @Expose
    private String transactionCompleteAt;

    public Transactions(String cardPanPrint, String cardType, String approvalNumber, String transactionAmount, Boolean ebtFlag, String transactionCompleteAt){
        this.cardPanPrint = cardPanPrint;
        this.cardType = cardType;
        this.approvalNumber=approvalNumber;
        this.transactionAmount=transactionAmount;
        this.ebtFlag=ebtFlag;
        this.transactionCompleteAt=transactionCompleteAt;
    }

    public String getCardPanPrint() {
        return cardPanPrint;
    }

    public void setCardPanPrint(String cardPanPrint) {
        this.cardPanPrint = cardPanPrint;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Boolean getEbtFlag() {
        return ebtFlag;
    }

    public void setEbtFlag(Boolean ebtFlag) {
        this.ebtFlag = ebtFlag;
    }

    public String getTransactionCompleteAt() {
        return transactionCompleteAt;
    }

    public void setTransactionCompleteAt(String transactionCompleteAt) {
        this.transactionCompleteAt = transactionCompleteAt;
    }

}