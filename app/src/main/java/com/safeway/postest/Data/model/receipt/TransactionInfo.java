
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TransactionInfo {

    @SerializedName("terminal_number")
    @Expose
    private String terminalNumber;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public TransactionInfo() {
    }

    /**
     * 
     * @param terminalNumber
     * @param transactionStatus
     * @param transactionId
     */
    public TransactionInfo(String terminalNumber, String transactionStatus, String transactionId) {
        super();
        this.terminalNumber = terminalNumber;
        this.transactionStatus = transactionStatus;
        this.transactionId = transactionId;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public TransactionInfo withTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
        return this;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionInfo withTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionInfo withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

}
