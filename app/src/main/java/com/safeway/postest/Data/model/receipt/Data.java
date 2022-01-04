
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("total_amount")
    @Expose
    private Double totalAmount;
    @SerializedName("terminal_number")
    @Expose
    private String terminalNumber;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("retrieve_barcode_symbology")
    @Expose
    private String retrieveBarcodeSymbology;
    @SerializedName("receipt_json")
    @Expose
    private ReceiptJson receiptJson;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("receipt")
    @Expose
    private String receipt;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("suspend_barcode_value")
    @Expose
    private String suspendBarcodeValue;
    @SerializedName("suspendReceiptTrailer")
    @Expose
    private String suspendReceiptTrailer;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("retrieve_barcode_value")
    @Expose
    private String retrieveBarcodeValue;
    @SerializedName("suspendReceiptHeader")
    @Expose
    private String suspendReceiptHeader;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("store_time")
    @Expose
    private String storeTime;
    @SerializedName("suspend_barcode_symbology")
    @Expose
    private String suspendBarcodeSymbology;
    @SerializedName("item_count")
    @Expose
    private Integer itemCount;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("lineItemTypeCount")
    @Expose
    private Integer lineItemTypeCount;
    @SerializedName("suspendReceiptBody")
    @Expose
    private String suspendReceiptBody;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("time_stamp")
    @Expose
    private TimeStamp timeStamp;
    @SerializedName("suspend_time")
    @Expose
    private Integer suspendTime;
    @SerializedName("checkoutToReceiptTime")
    @Expose
    private CheckoutToReceiptTime checkoutToReceiptTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param terminalNumber
     * @param orderId
     * @param retrieveBarcodeSymbology
     * @param deviceId
     * @param platform
     * @param suspendReceiptHeader
     * @param storeTime
     * @param suspendBarcodeSymbology
     * @param id
     * @param retrieveBarcodeValue
     * @param suspendReceiptBody
     * @param transactionStatus
     * @param suspendTime
     * @param storeId
     * @param version
     * @param transactionId
     * @param itemCount
     * @param timeStamp
     * @param totalAmount
     * @param receiptJson
     * @param storeAddress
     * @param lineItemTypeCount
     * @param checkoutToReceiptTime
     * @param suspendReceiptTrailer
     * @param guid
     * @param receipt
     * @param suspendBarcodeValue
     */
    public Data(Id id, Double totalAmount, String terminalNumber, String platform, String retrieveBarcodeSymbology, ReceiptJson receiptJson, String storeId, String deviceId, String receipt, String orderId, String suspendBarcodeValue, String suspendReceiptTrailer, String transactionId, String version, String retrieveBarcodeValue, String suspendReceiptHeader, String transactionStatus, String storeTime, String suspendBarcodeSymbology, Integer itemCount, String storeAddress, Integer lineItemTypeCount, String suspendReceiptBody, String guid, TimeStamp timeStamp, Integer suspendTime, CheckoutToReceiptTime checkoutToReceiptTime) {
        super();
        this.id = id;
        this.totalAmount = totalAmount;
        this.terminalNumber = terminalNumber;
        this.platform = platform;
        this.retrieveBarcodeSymbology = retrieveBarcodeSymbology;
        this.receiptJson = receiptJson;
        this.storeId = storeId;
        this.deviceId = deviceId;
        this.receipt = receipt;
        this.orderId = orderId;
        this.suspendBarcodeValue = suspendBarcodeValue;
        this.suspendReceiptTrailer = suspendReceiptTrailer;
        this.transactionId = transactionId;
        this.version = version;
        this.retrieveBarcodeValue = retrieveBarcodeValue;
        this.suspendReceiptHeader = suspendReceiptHeader;
        this.transactionStatus = transactionStatus;
        this.storeTime = storeTime;
        this.suspendBarcodeSymbology = suspendBarcodeSymbology;
        this.itemCount = itemCount;
        this.storeAddress = storeAddress;
        this.lineItemTypeCount = lineItemTypeCount;
        this.suspendReceiptBody = suspendReceiptBody;
        this.guid = guid;
        this.timeStamp = timeStamp;
        this.suspendTime = suspendTime;
        this.checkoutToReceiptTime = checkoutToReceiptTime;
    }

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Data withId(Id id) {
        this.id = id;
        return this;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Data withTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public Data withTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Data withPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getRetrieveBarcodeSymbology() {
        return retrieveBarcodeSymbology;
    }

    public void setRetrieveBarcodeSymbology(String retrieveBarcodeSymbology) {
        this.retrieveBarcodeSymbology = retrieveBarcodeSymbology;
    }

    public Data withRetrieveBarcodeSymbology(String retrieveBarcodeSymbology) {
        this.retrieveBarcodeSymbology = retrieveBarcodeSymbology;
        return this;
    }

    public ReceiptJson getReceiptJson() {
        return receiptJson;
    }

    public void setReceiptJson(ReceiptJson receiptJson) {
        this.receiptJson = receiptJson;
    }

    public Data withReceiptJson(ReceiptJson receiptJson) {
        this.receiptJson = receiptJson;
        return this;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public Data withStoreId(String storeId) {
        this.storeId = storeId;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Data withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getReceipt() {
        return receipt;
    }

    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    public Data withReceipt(String receipt) {
        this.receipt = receipt;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Data withOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getSuspendBarcodeValue() {
        return suspendBarcodeValue;
    }

    public void setSuspendBarcodeValue(String suspendBarcodeValue) {
        this.suspendBarcodeValue = suspendBarcodeValue;
    }

    public Data withSuspendBarcodeValue(String suspendBarcodeValue) {
        this.suspendBarcodeValue = suspendBarcodeValue;
        return this;
    }

    public String getSuspendReceiptTrailer() {
        return suspendReceiptTrailer;
    }

    public void setSuspendReceiptTrailer(String suspendReceiptTrailer) {
        this.suspendReceiptTrailer = suspendReceiptTrailer;
    }

    public Data withSuspendReceiptTrailer(String suspendReceiptTrailer) {
        this.suspendReceiptTrailer = suspendReceiptTrailer;
        return this;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Data withTransactionId(String transactionId) {
        this.transactionId = transactionId;
        return this;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Data withVersion(String version) {
        this.version = version;
        return this;
    }

    public String getRetrieveBarcodeValue() {
        return retrieveBarcodeValue;
    }

    public void setRetrieveBarcodeValue(String retrieveBarcodeValue) {
        this.retrieveBarcodeValue = retrieveBarcodeValue;
    }

    public Data withRetrieveBarcodeValue(String retrieveBarcodeValue) {
        this.retrieveBarcodeValue = retrieveBarcodeValue;
        return this;
    }

    public String getSuspendReceiptHeader() {
        return suspendReceiptHeader;
    }

    public void setSuspendReceiptHeader(String suspendReceiptHeader) {
        this.suspendReceiptHeader = suspendReceiptHeader;
    }

    public Data withSuspendReceiptHeader(String suspendReceiptHeader) {
        this.suspendReceiptHeader = suspendReceiptHeader;
        return this;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public Data withTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
        return this;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }

    public Data withStoreTime(String storeTime) {
        this.storeTime = storeTime;
        return this;
    }

    public String getSuspendBarcodeSymbology() {
        return suspendBarcodeSymbology;
    }

    public void setSuspendBarcodeSymbology(String suspendBarcodeSymbology) {
        this.suspendBarcodeSymbology = suspendBarcodeSymbology;
    }

    public Data withSuspendBarcodeSymbology(String suspendBarcodeSymbology) {
        this.suspendBarcodeSymbology = suspendBarcodeSymbology;
        return this;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Data withItemCount(Integer itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Data withStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
        return this;
    }

    public Integer getLineItemTypeCount() {
        return lineItemTypeCount;
    }

    public void setLineItemTypeCount(Integer lineItemTypeCount) {
        this.lineItemTypeCount = lineItemTypeCount;
    }

    public Data withLineItemTypeCount(Integer lineItemTypeCount) {
        this.lineItemTypeCount = lineItemTypeCount;
        return this;
    }

    public String getSuspendReceiptBody() {
        return suspendReceiptBody;
    }

    public void setSuspendReceiptBody(String suspendReceiptBody) {
        this.suspendReceiptBody = suspendReceiptBody;
    }

    public Data withSuspendReceiptBody(String suspendReceiptBody) {
        this.suspendReceiptBody = suspendReceiptBody;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Data withGuid(String guid) {
        this.guid = guid;
        return this;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Data withTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    public Integer getSuspendTime() {
        return suspendTime;
    }

    public void setSuspendTime(Integer suspendTime) {
        this.suspendTime = suspendTime;
    }

    public Data withSuspendTime(Integer suspendTime) {
        this.suspendTime = suspendTime;
        return this;
    }

    public CheckoutToReceiptTime getCheckoutToReceiptTime() {
        return checkoutToReceiptTime;
    }

    public void setCheckoutToReceiptTime(CheckoutToReceiptTime checkoutToReceiptTime) {
        this.checkoutToReceiptTime = checkoutToReceiptTime;
    }

    public Data withCheckoutToReceiptTime(CheckoutToReceiptTime checkoutToReceiptTime) {
        this.checkoutToReceiptTime = checkoutToReceiptTime;
        return this;
    }

}

//public class Data {
//
//    @SerializedName("_id")
//    @Expose
//    private String id;
//    @SerializedName("total_amount")
//    @Expose
//    private Double totalAmount;
//    @SerializedName("terminal_number")
//    @Expose
//    private String terminalNumber;
//    @SerializedName("retrieve_barcode_symbology")
//    @Expose
//    private String retrieveBarcodeSymbology;
//    @SerializedName("external_transaction_id")
//    @Expose
//    private String externalTransactionId;
//    @SerializedName("retrievalReceiptHeader")
//    @Expose
//    private String retrievalReceiptHeader;
//    @SerializedName("store_id")
//    @Expose
//    private String storeId;
//    @SerializedName("receipt_json")
//    @Expose
//    private ReceiptJson receiptJson;
//    @SerializedName("retrievalReceiptBody")
//    @Expose
//    private String retrievalReceiptBody;
//    @SerializedName("receipt")
//    @Expose
//    private String receipt;
//    @SerializedName("order_id")
//    @Expose
//    private String orderId;
//    @SerializedName("suspend_barcode_value")
//    @Expose
//    private String suspendBarcodeValue;
//    @SerializedName("transaction_id")
//    @Expose
//    private String transactionId;
//    @SerializedName("retrieve_barcode_value")
//    @Expose
//    private String retrieveBarcodeValue;
//    @SerializedName("transaction_status")
//    @Expose
//    private String transactionStatus;
//    @SerializedName("store_time")
//    @Expose
//    private String storeTime;
//    @SerializedName("is_recalculation_needed")
//    @Expose
//    private Boolean isRecalculationNeeded;
//    @SerializedName("retrievalReceiptTrailer")
//    @Expose
//    private String retrievalReceiptTrailer;
//    @SerializedName("suspend_barcode_symbology")
//    @Expose
//    private String suspendBarcodeSymbology;
//    @SerializedName("item_count")
//    @Expose
//    private Integer itemCount;
//    @SerializedName("external_terminal_number")
//    @Expose
//    private String externalTerminalNumber;
//    @SerializedName("lineItemTypeCount")
//    @Expose
//    private Integer lineItemTypeCount;
//    @SerializedName("store_address")
//    @Expose
//    private String storeAddress;
//    @SerializedName("guid")
//    @Expose
//    private String guid;
//    @SerializedName("time_stamp")
//    @Expose
//    private String timeStamp;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public Double getTotalAmount() {
//        return totalAmount;
//    }
//
//    public void setTotalAmount(Double totalAmount) {
//        this.totalAmount = totalAmount;
//    }
//
//    public String getTerminalNumber() {
//        return terminalNumber;
//    }
//
//    public void setTerminalNumber(String terminalNumber) {
//        this.terminalNumber = terminalNumber;
//    }
//
//    public String getRetrieveBarcodeSymbology() {
//        return retrieveBarcodeSymbology;
//    }
//
//    public void setRetrieveBarcodeSymbology(String retrieveBarcodeSymbology) {
//        this.retrieveBarcodeSymbology = retrieveBarcodeSymbology;
//    }
//
//    public String getExternalTransactionId() {
//        return externalTransactionId;
//    }
//
//    public void setExternalTransactionId(String externalTransactionId) {
//        this.externalTransactionId = externalTransactionId;
//    }
//
//    public String getRetrievalReceiptHeader() {
//        return retrievalReceiptHeader;
//    }
//
//    public void setRetrievalReceiptHeader(String retrievalReceiptHeader) {
//        this.retrievalReceiptHeader = retrievalReceiptHeader;
//    }
//
//    public String getStoreId() {
//        return storeId;
//    }
//
//    public void setStoreId(String storeId) {
//        this.storeId = storeId;
//    }
//
//    public ReceiptJson getReceiptJson() {
//        return receiptJson;
//    }
//
//    public void setReceiptJson(ReceiptJson receiptJson) {
//        this.receiptJson = receiptJson;
//    }
//
//    public String getRetrievalReceiptBody() {
//        return retrievalReceiptBody;
//    }
//
//    public void setRetrievalReceiptBody(String retrievalReceiptBody) {
//        this.retrievalReceiptBody = retrievalReceiptBody;
//    }
//
//    public String getReceipt() {
//        return receipt;
//    }
//
//    public void setReceipt(String receipt) {
//        this.receipt = receipt;
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
//    public String getSuspendBarcodeValue() {
//        return suspendBarcodeValue;
//    }
//
//    public void setSuspendBarcodeValue(String suspendBarcodeValue) {
//        this.suspendBarcodeValue = suspendBarcodeValue;
//    }
//
//    public String getTransactionId() {
//        return transactionId;
//    }
//
//    public void setTransactionId(String transactionId) {
//        this.transactionId = transactionId;
//    }
//
//    public String getRetrieveBarcodeValue() {
//        return retrieveBarcodeValue;
//    }
//
//    public void setRetrieveBarcodeValue(String retrieveBarcodeValue) {
//        this.retrieveBarcodeValue = retrieveBarcodeValue;
//    }
//
//    public String getTransactionStatus() {
//        return transactionStatus;
//    }
//
//    public void setTransactionStatus(String transactionStatus) {
//        this.transactionStatus = transactionStatus;
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
//    public Boolean getIsRecalculationNeeded() {
//        return isRecalculationNeeded;
//    }
//
//    public void setIsRecalculationNeeded(Boolean isRecalculationNeeded) {
//        this.isRecalculationNeeded = isRecalculationNeeded;
//    }
//
//    public String getRetrievalReceiptTrailer() {
//        return retrievalReceiptTrailer;
//    }
//
//    public void setRetrievalReceiptTrailer(String retrievalReceiptTrailer) {
//        this.retrievalReceiptTrailer = retrievalReceiptTrailer;
//    }
//
//    public String getSuspendBarcodeSymbology() {
//        return suspendBarcodeSymbology;
//    }
//
//    public void setSuspendBarcodeSymbology(String suspendBarcodeSymbology) {
//        this.suspendBarcodeSymbology = suspendBarcodeSymbology;
//    }
//
//    public Integer getItemCount() {
//        return itemCount;
//    }
//
//    public void setItemCount(Integer itemCount) {
//        this.itemCount = itemCount;
//    }
//
//    public String getExternalTerminalNumber() {
//        return externalTerminalNumber;
//    }
//
//    public void setExternalTerminalNumber(String externalTerminalNumber) {
//        this.externalTerminalNumber = externalTerminalNumber;
//    }
//
//    public Integer getLineItemTypeCount() {
//        return lineItemTypeCount;
//    }
//
//    public void setLineItemTypeCount(Integer lineItemTypeCount) {
//        this.lineItemTypeCount = lineItemTypeCount;
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
//    public String getGuid() {
//        return guid;
//    }
//
//    public void setGuid(String guid) {
//        this.guid = guid;
//    }
//
//    public String getTimeStamp() {
//        return timeStamp;
//    }
//
//    public void setTimeStamp(String timeStamp) {
//        this.timeStamp = timeStamp;
//    }
//
//}
