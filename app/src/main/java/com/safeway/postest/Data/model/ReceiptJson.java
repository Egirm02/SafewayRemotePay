package com.safeway.postest.Data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ReceiptJson implements Parcelable {
    protected ReceiptJson(Parcel in) {
    }

    public static final Creator<ReceiptJson> CREATOR = new Creator<ReceiptJson>() {
        @Override
        public ReceiptJson createFromParcel(Parcel in) {
            return new ReceiptJson(in);
        }

        @Override
        public ReceiptJson[] newArray(int size) {
            return new ReceiptJson[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }

//    @SerializedName("footer")
//    @Expose
//    private String footer;
//    @SerializedName("header")
//    @Expose
//    private String header;
//
//    @SerializedName(value="userId", alternate = "user_id")
//    @Expose
//    private String userId;
//
//    @SerializedName(value="receiptTotalResult",alternate = "receipt_total_result")
//    @Expose
//    private ReceiptTotalResult receiptTotalResult;
//
//    @SerializedName(value="transactionInfo",alternate = "transaction_info")
//    @Expose
//    private TransactionInfo transactionInfo;
//
//
//    @SerializedName(value="receiptItemDetailsList",alternate = "receipt_item_details_list")
//    @Expose
//    private List<ReceiptItemDetailsList> receiptItemDetailsList;
//
//
//    @SerializedName("store_address")
//    @Expose
//    private String store_address;
//
//
//    @SerializedName(value="storeTime",alternate="store_time")
//    @Expose
//    private String storeTime;
//
//    @SerializedName(value="orderId", alternate = "order_id")
//    @Expose
//    private String orderId;
//
//
//    public String getFooter() {
//        return footer;
//    }
//
//    public void setFooter(String footer) {
//        this.footer = footer;
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
//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(String userId) {
//        this.userId = userId;
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
//    public TransactionInfo getTransactionInfo() {
//        return transactionInfo;
//    }
//
//    public void setTransactionInfo(TransactionInfo transactionInfo) {
//        this.transactionInfo = transactionInfo;
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
//    public String getStore_address() {
//        return store_address;
//    }
//
//    public void setStore_address(String store_address) {
//        this.store_address = store_address;
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
//    public String getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(String orderId) {
//        this.orderId = orderId;
//    }
//
//    @Override
//    public String toString() {
//        return "ReceiptJson{" +
//                "footer='" + footer + '\'' +
//                ", header='" + header + '\'' +
//                ", userId='" + userId + '\'' +
//                ", receiptTotalResult=" + receiptTotalResult +
//                ", transactionInfo=" + transactionInfo +
//                ", receiptItemDetailsList=" + receiptItemDetailsList +
//                ", store_address='" + store_address + '\'' +
//                ", storeTime='" + storeTime + '\'' +
//                ", orderId='" + orderId + '\'' +
//                '}';
//    }
//
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeString(this.footer);
//        dest.writeString(this.header);
//        dest.writeString(this.userId);
//        dest.writeParcelable(this.receiptTotalResult, flags);
//        dest.writeParcelable(this.transactionInfo, flags);
//        dest.writeTypedList(this.receiptItemDetailsList);
//        dest.writeString(this.store_address);
//        dest.writeString(this.storeTime);
//        dest.writeString(this.orderId);
//    }
//
//    public ReceiptJson() {
//    }
//
//    protected ReceiptJson(Parcel in) {
//        this.footer = in.readString();
//        this.header = in.readString();
//        this.userId = in.readString();
//        this.receiptTotalResult = in.readParcelable(ReceiptTotalResult.class.getClassLoader());
//        this.transactionInfo = in.readParcelable(TransactionInfo.class.getClassLoader());
//        this.receiptItemDetailsList = in.createTypedArrayList(ReceiptItemDetailsList.CREATOR);
//        this.store_address = in.readString();
//        this.storeTime = in.readString();
//        this.orderId = in.readString();
//    }
//
//    public static final Creator<ReceiptJson> CREATOR = new Creator<ReceiptJson>() {
//        @Override
//        public ReceiptJson createFromParcel(Parcel source) {
//            return new ReceiptJson(source);
//        }
//
//        @Override
//        public ReceiptJson[] newArray(int size) {
//            return new ReceiptJson[size];
//        }
//    };

}
