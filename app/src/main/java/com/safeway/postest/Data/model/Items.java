package com.safeway.postest.Data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Items {

        @SerializedName("scan_code")
        @Expose
        private String scanCode;
        @SerializedName("upc_type")
        @Expose
        private String upcType;
        @SerializedName("quantity")
        @Expose
        private Integer quantity;
        @SerializedName("bag_item")
        @Expose
        private Boolean bagItem;
        @SerializedName("added_time_stamp")
        @Expose
        private Long addedTimeStamp;
        @SerializedName("last_updated_timestamp")
        @Expose
        private Long lastUpdatedTimestamp;
        @SerializedName("item")
        @Expose
        private Item item;
        @SerializedName("weight")
        @Expose
        private double weight;
        @SerializedName("guid")
        @Expose
        private String guid;

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getScanCode() {
            return scanCode;
        }

        public void setScanCode(String scanCode) {
            this.scanCode = scanCode;
        }

        public String getUpcType() {
            return upcType;
        }

        public void setUpcType(String upcType) {
            this.upcType = upcType;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Boolean getBagItem() {
            return bagItem;
        }

        public void setBagItem(Boolean bagItem) {
            this.bagItem = bagItem;
        }

        public Long getAddedTimeStamp() {
            return addedTimeStamp;
        }

        public void setAddedTimeStamp(Long addedTimeStamp) {
            this.addedTimeStamp = addedTimeStamp;
        }

        public Long getLastUpdatedTimestamp() {
            return lastUpdatedTimestamp;
        }

        public void setLastUpdatedTimestamp(Long lastUpdatedTimestamp) {
            this.lastUpdatedTimestamp = lastUpdatedTimestamp;
        }

        public Item getItem() {
            return item;
        }

        public void setItem(Item item) {
            this.item = item;
        }


}
