
package com.safeway.postest.Data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JfuOffers implements Parcelable
{

    @SerializedName("offerId")
    @Expose
    private String offerId;
    @SerializedName("priceType")
    @Expose
    private String priceType;
    @SerializedName("offerPrice")
    @Expose
    private String offerPrice;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("offerTs")
    @Expose
    private String offerTs;
    @SerializedName("usageType")
    @Expose
    private String usageType;
    @SerializedName("offerPgm")
    @Expose
    private String offerPgm;
    @SerializedName("purchaseInd")
    @Expose
    private String purchaseInd;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("minPurchaseQty")
    @Expose
    private Integer minPurchaseQty;
    @SerializedName("maxPurchaseQty")
    @Expose
    private Integer maxPurchaseQty;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("imageId")
    @Expose
    private String imageId;
    @SerializedName("deleted")
    @Expose
    private Boolean deleted;

    protected JfuOffers(Parcel in) {
        offerId = in.readString();
        priceType = in.readString();
        offerPrice = in.readString();
        description = in.readString();
        status = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        offerTs = in.readString();
        usageType = in.readString();
        offerPgm = in.readString();
        purchaseInd = in.readString();
        brand = in.readString();
        category = in.readString();

        if (in.readByte() == 0) {
            minPurchaseQty = 0;
        } else {
            minPurchaseQty = in.readInt();
        }
        if (in.readByte() == 0) {
            maxPurchaseQty = 0;
        } else {
            maxPurchaseQty = in.readInt();
        }
        if (in.readByte() == 0) {
            price = 0;
        } else {price = in.readInt();
        }
        imageId = in.readString();
        offerId = in.readString();
        byte tmpDelete = in.readByte();
        deleted = tmpDelete == 0 ? null : tmpDelete == 1;
    }



    public static final Creator<JfuOffers> CREATOR = new Creator<JfuOffers>() {
        @Override
        public JfuOffers createFromParcel(Parcel in) {
            return new JfuOffers(in);
        }

        @Override
        public JfuOffers[] newArray(int size) {
            return new JfuOffers[size];
        }
    };

//    public final static Parcelable.Creator<JfuOffer> CREATOR = new Creator<JfuOffer>() {
//
//
//        @SuppressWarnings({
//                "unchecked"
//        })
//        public JfuOffer createFromParcel(Parcel in) {
//            return new JfuOffer(in);
//        }
//
//        public JfuOffer[] newArray(int size) {
//            return (new JfuOffer[size]);
//        }
//
//    };

//    protected JfuOffer(Parcel in) {
//        this.offerId = ((String) in.readValue((String.class.getClassLoader())));
//        this.priceType = ((String) in.readValue((String.class.getClassLoader())));
//        this.offerPrice = ((String) in.readValue((String.class.getClassLoader())));
//        this.description = ((String) in.readValue((String.class.getClassLoader())));
//        this.status = ((String) in.readValue((String.class.getClassLoader())));
//        this.startDate = ((String) in.readValue((String.class.getClassLoader())));
//        this.endDate = ((String) in.readValue((String.class.getClassLoader())));
//        this.offerTs = ((String) in.readValue((String.class.getClassLoader())));
//        this.usageType = ((String) in.readValue((String.class.getClassLoader())));
//        this.offerPgm = ((String) in.readValue((String.class.getClassLoader())));
//        this.purchaseInd = ((String) in.readValue((String.class.getClassLoader())));
//        this.brand = ((String) in.readValue((String.class.getClassLoader())));
//        this.category = ((String) in.readValue((String.class.getClassLoader())));
//        this.minPurchaseQty = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.maxPurchaseQty = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.price = ((Integer) in.readValue((Integer.class.getClassLoader())));
//        this.imageId = ((String) in.readValue((String.class.getClassLoader())));
//        this.deleted = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
//    }

    public JfuOffers() {
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
    }

    public JfuOffers withOfferId(String offerId) {
        this.offerId = offerId;
        return this;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public JfuOffers withPriceType(String priceType) {
        this.priceType = priceType;
        return this;
    }

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public JfuOffers withOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JfuOffers withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public JfuOffers withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public JfuOffers withStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public JfuOffers withEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getOfferTs() {
        return offerTs;
    }

    public void setOfferTs(String offerTs) {
        this.offerTs = offerTs;
    }

    public JfuOffers withOfferTs(String offerTs) {
        this.offerTs = offerTs;
        return this;
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        this.usageType = usageType;
    }

    public JfuOffers withUsageType(String usageType) {
        this.usageType = usageType;
        return this;
    }

    public String getOfferPgm() {
        return offerPgm;
    }

    public void setOfferPgm(String offerPgm) {
        this.offerPgm = offerPgm;
    }

    public JfuOffers withOfferPgm(String offerPgm) {
        this.offerPgm = offerPgm;
        return this;
    }

    public String getPurchaseInd() {
        return purchaseInd;
    }

    public void setPurchaseInd(String purchaseInd) {
        this.purchaseInd = purchaseInd;
    }

    public JfuOffers withPurchaseInd(String purchaseInd) {
        this.purchaseInd = purchaseInd;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public JfuOffers withBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public JfuOffers withCategory(String category) {
        this.category = category;
        return this;
    }

    public Integer getMinPurchaseQty() {
        return minPurchaseQty;
    }

    public void setMinPurchaseQty(Integer minPurchaseQty) {
        this.minPurchaseQty = minPurchaseQty;
    }

    public JfuOffers withMinPurchaseQty(Integer minPurchaseQty) {
        this.minPurchaseQty = minPurchaseQty;
        return this;
    }

    public Integer getMaxPurchaseQty() {
        return maxPurchaseQty;
    }

    public void setMaxPurchaseQty(Integer maxPurchaseQty) {
        this.maxPurchaseQty = maxPurchaseQty;
    }

    public JfuOffers withMaxPurchaseQty(Integer maxPurchaseQty) {
        this.maxPurchaseQty = maxPurchaseQty;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public JfuOffers withPrice(Integer price) {
        this.price = price;
        return this;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public JfuOffers withImageId(String imageId) {
        this.imageId = imageId;
        return this;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public JfuOffers withDeleted(Boolean deleted) {
        this.deleted = deleted;
        return this;
    }

//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeValue(offerId);
//        dest.writeValue(priceType);
//        dest.writeValue(offerPrice);
//        dest.writeValue(description);
//        dest.writeValue(status);
//        dest.writeValue(startDate);
//        dest.writeValue(endDate);
//        dest.writeValue(offerTs);
//        dest.writeValue(usageType);
//        dest.writeValue(offerPgm);
//        dest.writeValue(purchaseInd);
//        dest.writeValue(brand);
//        dest.writeValue(category);
//        dest.writeValue(minPurchaseQty);
//        dest.writeValue(maxPurchaseQty);
//        dest.writeValue(price);
//        dest.writeValue(imageId);
//        dest.writeValue(deleted);
//    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(offerId);
        parcel.writeString(priceType);
        parcel.writeString(offerPrice);
        parcel.writeString(description);
        parcel.writeString(status);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        parcel.writeString(offerTs);
        parcel.writeString(usageType);
        parcel.writeString(offerPgm);
        parcel.writeString(purchaseInd);
        parcel.writeString(brand);
        parcel.writeString(category);
        if (minPurchaseQty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(minPurchaseQty);
        }
        if (maxPurchaseQty == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(maxPurchaseQty);
        }
        if (price == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(price);
        }
        parcel.writeString(imageId);
        parcel.writeString(offerId);
        parcel.writeByte((byte) (deleted == null ? 0 : deleted ? 1 : 2));

    }

    public int describeContents() {
        return 0;
    }

}