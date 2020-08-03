
package com.safeway.postest.Data.model.checkout;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.safeway.postest.Data.model.checkout.ItemDetail;
import com.safeway.postest.Data.model.receipt.Id;
import com.safeway.postest.Data.model.receipt.TimeStamp;

public class Data2 {

    @SerializedName("_id")
    @Expose
    private Id id;
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("item_details")
    @Expose
    private List<ItemDetail> itemDetails = null;
    @SerializedName("itemCount")
    @Expose
    private Integer itemCount;
    @SerializedName("checkout")
    @Expose
    private Boolean checkout;
    @SerializedName("vpos")
    @Expose
    private Boolean vpos;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("platform")
    @Expose
    private String platform;
    @SerializedName("version")
    @Expose
    private String version;
    @SerializedName("clubcard_nbr")
    @Expose
    private String clubcardNbr;
    @SerializedName("store_id")
    @Expose
    private String storeId;
    @SerializedName("guid")
    @Expose
    private String guid;
    @SerializedName("time_stamp")
    @Expose
    private TimeStamp timeStamp;
    @SerializedName("transaction_status")
    @Expose
    private String transactionStatus;
    @SerializedName("terminal_number")
    @Expose
    private String terminalNumber;
    @SerializedName("transaction_id")
    @Expose
    private String transactionId;
    @SerializedName("card_pan_print")
    @Expose
    private String cardPanPrint;
    @SerializedName("approval_number")
    @Expose
    private String approvalNumber;
    @SerializedName("transaction_complete_at")
    @Expose
    private String transactionCompleteAt;
    @SerializedName("card_type")
    @Expose
    private String cardType;
    @SerializedName("must_scan")
    @Expose
    private Integer mustScan;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ItemDetail> getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(List<ItemDetail> itemDetails) {
        this.itemDetails = itemDetails;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Boolean getCheckout() {
        return checkout;
    }

    public void setCheckout(Boolean checkout) {
        this.checkout = checkout;
    }

    public Boolean getVpos() {
        return vpos;
    }

    public void setVpos(Boolean vpos) {
        this.vpos = vpos;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClubcardNbr() {
        return clubcardNbr;
    }

    public void setClubcardNbr(String clubcardNbr) {
        this.clubcardNbr = clubcardNbr;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public TimeStamp getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(TimeStamp timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getTerminalNumber() {
        return terminalNumber;
    }

    public void setTerminalNumber(String terminalNumber) {
        this.terminalNumber = terminalNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCardPanPrint() {
        return cardPanPrint;
    }

    public void setCardPanPrint(String cardPanPrint) {
        this.cardPanPrint = cardPanPrint;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getTransactionCompleteAt() {
        return transactionCompleteAt;
    }

    public void setTransactionCompleteAt(String transactionCompleteAt) {
        this.transactionCompleteAt = transactionCompleteAt;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public Integer getMustScan() {
        return mustScan;
    }

    public void setMustScan(Integer mustScan) {
        this.mustScan = mustScan;
    }

}
