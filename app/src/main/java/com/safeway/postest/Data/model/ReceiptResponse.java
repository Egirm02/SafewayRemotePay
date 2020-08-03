package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ReceiptResponse implements Serializable {


    @SerializedName(value="storeTime",alternate="store_time")
    @Expose
    private String storeTime;

    @SerializedName(value="orderId", alternate = "order_id")
    @Expose
    private String orderId;

    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName(value="totalAmount",alternate = "total_amount")
    @Expose
    private Double total_amount;
    @SerializedName(value="receipt_json")
    @Expose
    private ReceiptJson receiptJson;
    @SerializedName("guid")
    @Expose
    private String guid;

    @SerializedName("suspend_barcode_value")
    @Expose
    private String suspendBarcodeValue;
    @SerializedName("suspend_barcode_symbology")
    @Expose
    private String suspendBarcodeSymbology;

    @SerializedName("transaction_status")
    @Expose
    private String transaction_status;
    @SerializedName("retrieve_barcode_value")
    private String barcodeValue;

    @SerializedName("platform")
    @Expose
    private String platform;

    @SerializedName("terminal_number")
    @Expose
    private String terminal_number;

    @SerializedName("device_id")
    @Expose
    private String device_id;


    @SerializedName("transaction_id")
    @Expose
    private String transaction_id;

    @SerializedName("store_address")
    @Expose
    private String store_address;

    @SerializedName("version")
    @Expose
    private String version;

    @SerializedName("item_count")
    @Expose
    private Integer itemCount;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStore_address() {
        return store_address;
    }

    public void setStore_address(String store_address) {
        this.store_address = store_address;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getTerminal_number() {
        return terminal_number;
    }

    public void setTerminal_number(String terminal_number) {
        this.terminal_number = terminal_number;
    }

    public Double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Double getTotalAmount() {
        return total_amount;
    }

    public void setTotalAmount(Double total_amount) {
        this.total_amount = total_amount;
    }

    public ReceiptJson getReceiptJson() {
        return receiptJson;
    }

    public void setReceiptJson(ReceiptJson receiptJson) {
        this.receiptJson = receiptJson;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }


    public String getSuspendBarcodeValue() {
        return suspendBarcodeValue;
    }

    public void setSuspendBarcodeValue(String suspendBarcodeValue) {
        this.suspendBarcodeValue = suspendBarcodeValue;
    }

    public String getSuspendBarcodeSymbology() {
        return suspendBarcodeSymbology;
    }

    public void setSuspendBarcodeSymbology(String suspendBarcodeSymbology) {
        this.suspendBarcodeSymbology = suspendBarcodeSymbology;
    }

    public String getTransaction_status() {
        return transaction_status;
    }

    public void setTransaction_status(String transaction_status) {
        this.transaction_status = transaction_status;
    }

    public String getBarcodeValue() {
        return barcodeValue;
    }

    public void setBarcodeValue(String barcodeValue) {
        this.barcodeValue = barcodeValue;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    @Override
    public String toString() {
        return "ReceiptResponse{" +
                "storeTime='" + storeTime + '\'' +
                ", orderId='" + orderId + '\'' +
                ", receipt='" + receipt + '\'' +
                ", total_amount=" + total_amount +
                ", receiptJson=" + receiptJson +
                ", guid='" + guid + '\'' +
                ", suspendBarcodeValue='" + suspendBarcodeValue + '\'' +
                ", suspendBarcodeSymbology='" + suspendBarcodeSymbology + '\'' +
                ", transaction_status='" + transaction_status + '\'' +
                ", barcodeValue='" + barcodeValue + '\'' +
                ", platform='" + platform + '\'' +
                ", terminal_number='" + terminal_number + '\'' +
                ", device_id='" + device_id + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", store_address='" + store_address + '\'' +
                ", version='" + version + '\'' +
                ", itemCount='" + itemCount + '\'' +
                '}';
    }
}
