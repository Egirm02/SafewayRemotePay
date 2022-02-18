package com.safeway.postest.Data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Item2  {

//    @SerializedName("_id")
//    private String _id;
//
//    public void setUpcType(String upcType) {
//        this.upcType = upcType;
//    }
//
//    @SerializedName("item_id")
//
//    private String itemId;
//
//    @SerializedName("sell_price")
//    private Double sellPrice;
//
//    @SerializedName("link_code")
//    private String linkCode;
////ext_description
//    @SerializedName("ext_description")
//    private String extDescription;
//
//    @SerializedName("pos_description")
//    private String posDescription;
//
//    @SerializedName("quantity")
//    private Integer sellMultiple;
//
//    @SerializedName("dept")
//    private String dept;
//
//    @SerializedName("sku")
//    private String sku;
//
//    @SerializedName("image_url")
//    private String imageUrl;
//
//    @SerializedName("weight_item")
//    private Boolean weightItem;
//
//    @SerializedName("crv_item")
//    private double crvItem;
//
//    @SerializedName("club_item")
//    private Boolean clubItem;
//
//    @SerializedName("restricted_item")
//    private Boolean restrictedItem;
//
//    @SerializedName("taxable")
//    private Boolean taxable;
//
//    @SerializedName("food_stamp")
//    private Boolean foodStamp;
//
//    @SerializedName("ewic")
//    private Boolean ewic;
//
//    @SerializedName("smic")
//    private String smic;
//
//    @SerializedName("id")
//    private String id;
//
//    @SerializedName("image_item_id")
//    private String imageItemId;
//
//    @SerializedName("upc_type")
//    private String upcType;
//
//    @SerializedName("scan_code")
//    private String scanCode;
//
//    @SerializedName("jfuOfferCount")
//    private Integer jfuOfferCount;
//
//    @SerializedName("jfuOffers")
//    private List<JfuOffers> jfuOffers;
//
//    @SerializedName("clubPrice")
//    private List<ClubPrice> clubPrice;
//
//    @SerializedName("regular_price")
//    private Double regularPrice;
//
//    @SerializedName("item_price")
//    private Double itemPrice;
//
//    @SerializedName("extended_price")
//    private Double extendedPrice;
//
//    @SerializedName("item_num")
//    private Integer itemNum;
//
//    @SerializedName("removed_item")
//    private Boolean removedItem;
//
//
//
//    public boolean isHeader = false;
//
//    protected Item(Parcel in) {
//        clubPrice = in.createTypedArrayList(ClubPrice.CREATOR); //readParcelable(ClubPrice.class.getClassLoader());
//        itemId = in.readString();
//        if (in.readByte() == 0) {
//            sellPrice = null;
//        } else {
//            sellPrice = in.readDouble();
//        }
//        linkCode = in.readString();
//        extDescription = in.readString();
//        posDescription = in.readString();
//        if (in.readByte() == 0) {
//            sellMultiple = null;
//        } else {
//            sellMultiple = in.readInt();
//        }
//        dept = in.readString();
//        sku = in.readString();
//        imageUrl = in.readString();
//        byte tmpWeightItem = in.readByte();
//        weightItem = tmpWeightItem == 0 ? null : tmpWeightItem == 1;
//        crvItem = in.readDouble();
//        byte tmpClubItem = in.readByte();
//        clubItem = tmpClubItem == 0 ? null : tmpClubItem == 1;
//        byte tmpRestrictedItem = in.readByte();
//        restrictedItem = tmpRestrictedItem == 0 ? null : tmpRestrictedItem == 1;
//        byte tmpTaxable = in.readByte();
//        taxable = tmpTaxable == 0 ? null : tmpTaxable == 1;
//        byte tmpFoodStamp = in.readByte();
//        foodStamp = tmpFoodStamp == 0 ? null : tmpFoodStamp == 1;
//        byte tmpEwic = in.readByte();
//        ewic = tmpEwic == 0 ? null : tmpEwic == 1;
//        smic = in.readString();
//        id = in.readString();
//        imageItemId = in.readString();
//        upcType = in.readString();
//        scanCode = in.readString();
////        if (in.readByte() == 0) {
////            jfuOfferCount = in.readInt();
////        } else {
////            jfuOfferCount = in.readInt();
////        }
//        if (in.readByte() == 0) {
//            regularPrice= null;
//        } else {
//            regularPrice = in.readDouble();
//        }
//        if (in.readByte() == 0) {
//            itemPrice = null;
//        } else {
//            itemPrice = in.readDouble();
//        }
//        if (in.readByte() == 0) {
//            jfuOfferCount = in.readInt();
//        } else {
//            jfuOfferCount = in.readInt();
//        }
//        if (in.readByte() == 0) {
//            extendedPrice = null;
//        } else {
//            extendedPrice = in.readDouble();
//        }
//       // this.receiptItemDetailsList = in.createTypedArrayList(ReceiptItemDetailsList.CREATOR);
//       jfuOffers = in.createTypedArrayList(JfuOffers.CREATOR); //readParcelable(JfuOffers.class.getClassLoader());
//        if (in.readByte() == 0) {
//            itemNum = in.readInt();
//        } else {
//            itemNum = in.readInt();
//        }
//        byte remvedItem = in.readByte();
//        removedItem = remvedItem == 0 ? null : remvedItem ==1;
//    }
//
//    public static final Creator<Item> CREATOR = new Creator<Item>() {
//        @Override
//        public Item createFromParcel(Parcel in) {
//            return new Item(in);
//        }
//
//        @Override
//        public Item[] newArray(int size) {
//            return new Item[size];
//        }
//    };
//    public List<ClubPrice> getClubPrice() {
//        return clubPrice;
//    }
//
//    public void setClubPrice(List<ClubPrice> clubPrice) {
//        this.clubPrice = clubPrice;
//    }
//
//    public Item withClubPrice(List<ClubPrice> clubPrice) {
//        this.clubPrice = clubPrice;
//        return this;
//    }
//
//    public Boolean getWeightItem() {
//        return weightItem;
//    }
//
//    public double getCrvItem() {
//        return crvItem;
//    }
//
//    public Boolean getClubItem() {
//        return clubItem;
//    }
//
//    public Boolean getRestrictedItem() {
//        return restrictedItem;
//    }
//
//    public Boolean getTaxable() {
//        return taxable;
//    }
//
//    public Boolean getFoodStamp() {
//        return foodStamp;
//    }
//
//    public Boolean getEwic() {
//        return ewic;
//    }
//
//    public String getSmic() {
//        return smic;
//    }
//
//    public String getImageItemId() {
//        return imageItemId;
//    }
//
//    public String getUpcType() {
//        return upcType;
//    }
//
//    public Item() {
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public String getId() {
//        return _id;
//    }
//
//    public void setId(String id) {
//        this._id = id;
//    }
//
//    public String getItemId() {
//        return itemId;
//    }
//
//    public void setItemId(String itemId) {
//        this.itemId = itemId;
//    }
//
//    public Double getSellPrice() {
//        return sellPrice;
//    }
//
//    public void setSellPrice(Double sellPrice) {
//        this.sellPrice = sellPrice;
//    }
//
//    public String getLinkCode() {
//        return linkCode;
//    }
//
//    public void setLinkCode(String linkCode) {
//        this.linkCode = linkCode;
//    }
//
//    public String getExtDescription() {
//        return extDescription;
//    }
//
//    public void setExtDescription(String extDescription) {
//        this.extDescription = extDescription;
//    }
//
//    public String getPosDescription() {
//        return posDescription;
//    }
//
//    public void setPosDescription(String posDescription) {
//        this.posDescription = posDescription;
//    }
//
//    public Integer getSellMultiple() {
//        return sellMultiple;
//    }
//
//    public void setSellMultiple(Integer sellMultiple) {
//        this.sellMultiple = sellMultiple;
//    }
//
//    public String getDept() {
//        return dept;
//    }
//
//    public void setDept(String dept) {
//        this.dept = dept;
//    }
//
//    public String getSku() {
//        return sku;
//    }
//
//    public String getScanCode() {
//        return scanCode;
//    }
//
//    public void setScanCode(String scanCode) {
//        this.scanCode = scanCode;
//    }
//
//    public Integer getJfuOfferCount() {
//        return jfuOfferCount;
//    }
//
//    public void setJfuOfferCount(Integer jfuOfferCount) {
//        this.jfuOfferCount = jfuOfferCount;
//    }
//
//    public Double getRegularPrice(){
//        return regularPrice;
//    }
//
//    public void setRegularPrice(Double regularPrice) {
//        this.regularPrice = regularPrice;
//    }
//
//    public Double getItemPrice() {
//        return itemPrice;
//    }
//
//    public void setItemPrice(Double itemPrice) {
//        this.itemPrice = itemPrice;
//    }
//
//    public List<JfuOffers> getJfuOffers() {
//        return jfuOffers;
//    }
//
//    public void setJfuOffers(List<JfuOffers> jfuOffers) {
//        this.jfuOffers = jfuOffers;
//    }
//
//    public Item withJfuOffers(List<JfuOffers> jfuOffers) {
//        this.jfuOffers = jfuOffers;
//        return this;
//    }
//
//    public Double getExtendedPrice(){
//        return extendedPrice;
//    }
//
//    public void setExtendedPrice(Double regularPrice) {
//        this.extendedPrice = regularPrice;
//    }
//
//    public Integer getItemNum() {
//        return itemNum;
//    }
//
//    public void setItemNum(Integer itemNum) {
//        this.itemNum = itemNum;
//    }
//
//    public Boolean getRemovedItem() {
//        return removedItem;
//    }
//
//
//
//    @Override
//    public String toString() {
//        return "Item{" +
//                "_id=" + _id +
//                ", itemId='" + itemId + '\'' +
//                ", sellPrice=" + sellPrice +
//                ", linkCode='" + linkCode + '\'' +
//                ", extDescription='" + extDescription + '\'' +
//                ", posDescription='" + posDescription + '\'' +
//                ", sellMultiple=" + sellMultiple +
//                ", dept='" + dept + '\'' +
//                ", sku='" + sku + '\'' +
//                ", imageUrl='" + imageUrl + '\'' +
//                ", weightItem=" + weightItem +
//                ", crvItem=" + crvItem +
//                ", clubItem=" + clubItem +
//                ", restrictedItem=" + restrictedItem +
//                ", taxable=" + taxable +
//                ", foodStamp=" + foodStamp +
//                ", ewic=" + ewic +
//                ", smic='" + smic + '\'' +
//                ", id='" + id + '\'' +
//                ", imageItemId='" + imageItemId + '\'' +
//                ", upcType='" + upcType + '\'' +
//                ", scanCode='" + scanCode + '\'' +
//                ", isHeader=" + isHeader +
//                ", jfuOfferCount='" + jfuOfferCount + '\'' +
//                ", jfuOffers=" + jfuOfferCount +
//                ", clubPrice=" + clubPrice +
//                ", regularPrice=" + regularPrice +
//                ", itemPrice=" + itemPrice +
//                ", extendedPrice" + extendedPrice +
//                ", itemNum='" + itemNum + '\'' +
//                ", removedItem=" + removedItem +
//                '}';
//    }
//
//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeTypedList(clubPrice);
//        parcel.writeString(itemId);
//        if (sellPrice == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeDouble(sellPrice);
//        }
//        parcel.writeString(linkCode);
//        parcel.writeString(extDescription);
//        parcel.writeString(posDescription);
//        if (sellMultiple == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeInt(sellMultiple);
//        }
//        parcel.writeString(dept);
//        parcel.writeString(sku);
//        parcel.writeString(imageUrl);
//        parcel.writeByte((byte) (weightItem == null ? 0 : weightItem ? 1 : 2));
//        parcel.writeDouble(crvItem);
//        parcel.writeByte((byte) (clubItem == null ? 0 : clubItem ? 1 : 2));
//        parcel.writeByte((byte) (restrictedItem == null ? 0 : restrictedItem ? 1 : 2));
//        parcel.writeByte((byte) (taxable == null ? 0 : taxable ? 1 : 2));
//        parcel.writeByte((byte) (foodStamp == null ? 0 : foodStamp ? 1 : 2));
//        parcel.writeByte((byte) (ewic == null ? 0 : ewic ? 1 : 2));
//        parcel.writeString(smic);
//        parcel.writeString(id);
//        parcel.writeString(imageItemId);
//        parcel.writeString(upcType);
//        parcel.writeString(scanCode);
//        if (regularPrice == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeDouble(regularPrice);
//        }
//        if (itemPrice == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeDouble(itemPrice);
//        }
//        if (jfuOfferCount == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeInt(jfuOfferCount);
//        }
//        parcel.writeTypedList(jfuOffers);
//        if (extendedPrice == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeDouble(extendedPrice);
//        }
//        if (itemNum == null) {
//            parcel.writeByte((byte) 0);
//        } else {
//            parcel.writeByte((byte) 1);
//            parcel.writeInt(itemNum);
//        }
//        parcel.writeByte((byte) (removedItem == null ? 0 : removedItem ? 1 : 2));
//       // parcel.writeList(jfuOffers);
//       // parcel.writeParcelable((Parcelable) jfuOffers,i);
//    }

}
