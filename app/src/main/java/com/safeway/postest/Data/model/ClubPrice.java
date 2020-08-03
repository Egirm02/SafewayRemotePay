package com.safeway.postest.Data.model;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ClubPrice implements Parcelable {
        @SerializedName("promoFactor")
        @Expose
        private String promoFactor;
        @SerializedName("promoMethod")
        @Expose
        private String promoMethod;
        @SerializedName("promoPrice")
        @Expose
        private Double promoPrice;
        @SerializedName("promoMaxQty")
        @Expose
        private Integer promoMaxQty;
        @SerializedName("promoMinQty")
        @Expose
        private Integer promoMinQty;
        @SerializedName("offerMessage")
        @Expose
        private String offerMessage;
        @SerializedName("offerMsg1")
        @Expose
        private String offerMsg1;
        @SerializedName("offerId")
        @Expose
        private String offerId;
        @SerializedName("rawOfferPrice")
        @Expose
        private Double rawOfferPrice;

    protected ClubPrice(Parcel in) {
        promoFactor = in.readString();
        promoMethod = in.readString();
        if (in.readByte() == 0) {
            promoPrice = null;
        } else {
            promoPrice = in.readDouble();
        }
        if (in.readByte() == 0) {
            promoMaxQty = null;
        } else {
            promoMaxQty = in.readInt();
        }
        if (in.readByte() == 0) {
            promoMinQty = null;
        } else {
            promoMinQty = in.readInt();
        }
        offerMessage = in.readString();
        offerMsg1 = in.readString();
        offerId = in.readString();
        if (in.readByte() == 0) {
            rawOfferPrice = null;
        } else {
            rawOfferPrice = in.readDouble() ;
        }
    }

    public static final Creator<ClubPrice> CREATOR = new Creator<ClubPrice>() {
        @Override
        public ClubPrice createFromParcel(Parcel in) {
            return new ClubPrice(in);
        }

        @Override
        public ClubPrice[] newArray(int size) {
            return new ClubPrice[size];
        }
    };

    public String getPromoFactor() {
            return promoFactor;
        }

        public void setPromoFactor(String promoFactor) {
            this.promoFactor = promoFactor;
        }

        public ClubPrice withPromoFactor(String promoFactor) {
            this.promoFactor = promoFactor;
            return this;
        }
    public String getPromoMethod() {
        return promoMethod;
    }

    public void setPromoMethod(String promoMethod) {
        this.promoMethod = promoMethod;
    }

    public ClubPrice withPromoMethod(String promoMethod) {
        this.promoMethod = promoMethod;
        return this;
    }

        public Double getPromoPrice() {
            return promoPrice;
        }

        public void setPromoPrice(Double promoPrice) {
            this.promoPrice = promoPrice;
        }

        public ClubPrice withPromoPrice(Double promoPrice) {
            this.promoPrice = promoPrice;
            return this;
        }

        public Integer getPromoMaxQty() {
            return promoMaxQty;
        }

        public void setPromoMaxQty(Integer promoMaxQty) {
            this.promoMaxQty = promoMaxQty;
        }

        public ClubPrice withPromoMaxQty(Integer promoMaxQty) {
            this.promoMaxQty = promoMaxQty;
            return this;
        }

        public Integer getPromoMinQty() {
            return promoMinQty;
        }

        public void setPromoMinQty(Integer promoMinQty) {
            this.promoMinQty = promoMinQty;
        }

        public ClubPrice withPromoMinQty(Integer promoMinQty) {
            this.promoMinQty = promoMinQty;
            return this;
        }

        public String getOfferMessage() {
            return offerMessage;
        }

        public void setOfferMessage(String offerMessage) {
            this.offerMessage = offerMessage;
        }

        public ClubPrice withOfferMessage(String offerMessage) {
            this.offerMessage = offerMessage;
            return this;
        }
        public String getOfferMsg1() {
            return offerMsg1;
        }

        public void setOfferMsg1(String offerMsg1) {
            this.offerMsg1 = offerMsg1;
        }

        public ClubPrice withOfferMsg1(String offerMsg1) {
            this.offerMsg1 = offerMsg1;
            return this;
        }

        public String getOfferId() {
            return offerId;
        }

        public void setOfferId(String offerId) {
            this.offerId = offerId;
        }

        public ClubPrice withOfferId(String offerId) {
            this.offerId = offerId;
            return this;
        }

        public Double getRawOfferPrice() {
            return rawOfferPrice;
        }

        public void setRawOfferPrice(Double rawOfferPrice) {
            this.rawOfferPrice = rawOfferPrice;
        }

        public ClubPrice withPromoLimQty(Integer promoLimQty) {
            this.rawOfferPrice = rawOfferPrice;
            return this;
        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(promoFactor);
        parcel.writeString(promoMethod);
        if (promoPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(promoPrice);
        }
        if (promoMaxQty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(promoMaxQty);
        }
        if (promoMinQty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(promoMinQty);
        }
        parcel.writeString(offerMessage);
        parcel.writeString(offerMsg1);
        parcel.writeString(offerId);
        if (rawOfferPrice == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeDouble(rawOfferPrice);
        }

    }
}

