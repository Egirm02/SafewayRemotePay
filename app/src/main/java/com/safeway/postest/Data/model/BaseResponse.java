package com.safeway.postest.Data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BaseResponse<T> implements Parcelable {

    @SerializedName("ack")
    private String ack;

    @SerializedName("data")
    private T response;

    @SerializedName("message")
    private String message;

    @SerializedName("order_id")
    private String orderId;

    @SerializedName("errors")
    private List<ErrorMessage> errorMessage;


    protected BaseResponse(Parcel in) {
        ack = in.readString();
        message = in.readString();
        orderId = in.readString();
    }

    public static final Creator<BaseResponse> CREATOR = new Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel in) {
            return new BaseResponse(in);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(ack);
        parcel.writeString(message);
        parcel.writeString(orderId);
    }

    public String getAck() {
        return ack;
    }

    public void setAck(String ack) {
        this.ack = ack;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public List<ErrorMessage> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<ErrorMessage> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public static Creator<BaseResponse> getCREATOR() {
        return CREATOR;
    }
}
