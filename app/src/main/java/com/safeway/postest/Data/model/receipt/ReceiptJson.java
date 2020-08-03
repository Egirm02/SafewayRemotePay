
package com.safeway.postest.Data.model.receipt;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//public class ReceiptJson {
//
//    @SerializedName("footer")
//    @Expose
//    private String footer;
//    @SerializedName("receiptItemDetailsList")
//    @Expose
//    private List<ReceiptItemDetailsList> receiptItemDetailsList = null;
//    @SerializedName("transactionInfo")
//    @Expose
//    private TransactionInfo transactionInfo;
//    @SerializedName("receiptTotalResult")
//    @Expose
//    private ReceiptTotalResult receiptTotalResult;
//    @SerializedName("storeTime")
//    @Expose
//    private String storeTime;
//    @SerializedName("userId")
//    @Expose
//    private String userId;
//    @SerializedName("orderId")
//    @Expose
//    private String orderId;
//    @SerializedName("header")
//    @Expose
//    private String header;
//    @SerializedName("store_address")
//    @Expose
//    private String storeAddress;
//
//    /**
//     * No args constructor for use in serialization
//     *
//     */
//    public ReceiptJson() {
//    }
//
//    /**
//     *
//     * @param receiptTotalResult
//     * @param footer
//     * @param orderId
//     * @param storeAddress
//     * @param storeTime
//     * @param header
//     * @param receiptItemDetailsList
//     * @param userId
//     * @param transactionInfo
//     */
//    public ReceiptJson(String footer, List<ReceiptItemDetailsList> receiptItemDetailsList, TransactionInfo transactionInfo, ReceiptTotalResult receiptTotalResult, String storeTime, String userId, String orderId, String header, String storeAddress) {
//        super();
//        this.footer = footer;
//        this.receiptItemDetailsList = receiptItemDetailsList;
//        this.transactionInfo = transactionInfo;
//        this.receiptTotalResult = receiptTotalResult;
//        this.storeTime = storeTime;
//        this.userId = userId;
//        this.orderId = orderId;
//        this.header = header;
//        this.storeAddress = storeAddress;
//    }
//
//    public String getFooter() {
//        return footer;
//    }
//
//    public void setFooter(String footer) {
//        this.footer = footer;
//    }
//
//    public ReceiptJson withFooter(String footer) {
//        this.footer = footer;
//        return this;
//    }
//
//    public List<ReceiptItemDetailsList> getReceiptItemDetailsList() {
//        return receiptItemDetailsList;
//    }
//
//    public void setReceiptItemDetailsList(List<ReceiptItemDetailsList> receiptItemDetailsList) {
//        this.receiptItemDetailsList = receiptItemDetailsList;
//    }
//
//    public ReceiptJson withReceiptItemDetailsList(List<ReceiptItemDetailsList> receiptItemDetailsList) {
//        this.receiptItemDetailsList = receiptItemDetailsList;
//        return this;
//    }
//
//    public TransactionInfo getTransactionInfo() {
//        return transactionInfo;
//    }
//
//    public void setTransactionInfo(TransactionInfo transactionInfo) {
//        this.transactionInfo = transactionInfo;
//    }
//
//    public ReceiptJson withTransactionInfo(TransactionInfo transactionInfo) {
//        this.transactionInfo = transactionInfo;
//        return this;
//    }
//
//    public ReceiptTotalResult getReceiptTotalResult() {
//        return receiptTotalResult;
//    }
//
//    public void setReceiptTotalResult(ReceiptTotalResult receiptTotalResult) {
//        this.receiptTotalResult = receiptTotalResult;
//    }
//
//    public ReceiptJson withReceiptTotalResult(ReceiptTotalResult receiptTotalResult) {
//        this.receiptTotalResult = receiptTotalResult;
//        return this;
//    }
//
//    public String getStoreTime() {
//        return storeTime;
//    }
//
//    public void setStoreTime(String storeTime) {
//        this.storeTime = storeTime;
//    }
//
//    public ReceiptJson withStoreTime(String storeTime) {
//        this.storeTime = storeTime;
//        return this;
//    }
//
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
//    }
//
//    public ReceiptJson withUserId(String userId) {
//        this.userId = userId;
//        return this;
//    }
//
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    public ReceiptJson withOrderId(String orderId) {
//        this.orderId = orderId;
//        return this;
//    }
//
//    public String getHeader() {
//        return header;
//    }
//
//    public void setHeader(String header) {
//        this.header = header;
//    }
//
//    public ReceiptJson withHeader(String header) {
//        this.header = header;
//        return this;
//    }
//
//    public String getStoreAddress() {
//        return storeAddress;
//    }
//
//    public void setStoreAddress(String storeAddress) {
//        this.storeAddress = storeAddress;
//    }
//
//    public ReceiptJson withStoreAddress(String storeAddress) {
//        this.storeAddress = storeAddress;
//        return this;
//    }
//
//}

public class ReceiptJson {

    @SerializedName("footer")
    @Expose
    private String footer;
    @SerializedName("receiptItemDetailsList")
    @Expose
    private List<ReceiptItemDetailsList> receiptItemDetailsList = null;
    @SerializedName("receiptTotalResult")
    @Expose
    private ReceiptTotalResult receiptTotalResult;
    @SerializedName("storeTime")
    @Expose
    private String storeTime;
    @SerializedName("transactionInfoList")
    @Expose
    private List<Object> transactionInfoList = null;
    @SerializedName("orderId")
    @Expose
    private String orderId;
    @SerializedName("header")
    @Expose
    private String header;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public List<ReceiptItemDetailsList> getReceiptItemDetailsList() {
        return receiptItemDetailsList;
    }

    public void setReceiptItemDetailsList(List<ReceiptItemDetailsList> receiptItemDetailsList) {
        this.receiptItemDetailsList = receiptItemDetailsList;
    }

    public ReceiptTotalResult getReceiptTotalResult() {
        return receiptTotalResult;
    }

    public void setReceiptTotalResult(ReceiptTotalResult receiptTotalResult) {
        this.receiptTotalResult = receiptTotalResult;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public List<Object> getTransactionInfoList() {
        return transactionInfoList;
    }

    public void setTransactionInfoList(List<Object> transactionInfoList) {
        this.transactionInfoList = transactionInfoList;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

}