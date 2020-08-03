
package com.safeway.postest.Data.model.receipt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiptTotalResult {

    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("j4uCouponsSavings")
    @Expose
    private Double j4uCouponsSavings;
    @SerializedName("totalSavings")
    @Expose
    private Double totalSavings;
    @SerializedName("tax")
    @Expose
    private Double tax;
    @SerializedName("ebtTotal")
    @Expose
    private Double ebt;
    @SerializedName("subTotal")
    @Expose
    private Double subTotal;
    @SerializedName("clubCardSavings")
    @Expose
    private Double clubCardSavings;
    @SerializedName("balanceDue")
    @Expose
    private Double balanceDue;
    /**
     * No args constructor for use in serialization
     * 
     */
    public ReceiptTotalResult() {
    }

    /**
     * 
     * @param total
     * @param clubCardSavings
     * @param tax
     * @param subTotal
     * @param j4uCouponsSavings
     * @param totalSavings
     * @param ebt
     * @param balanceDue
     */
    public ReceiptTotalResult(Double total, Double j4uCouponsSavings, Double totalSavings, Double tax, Double ebt,Double subTotal, Double clubCardSavings,Double balanceDue) {
        super();
        this.total = total;
        this.j4uCouponsSavings = j4uCouponsSavings;
        this.totalSavings = totalSavings;
        this.tax = tax;
        this.ebt = ebt;
        this.subTotal = subTotal;
        this.clubCardSavings = clubCardSavings;
        this.balanceDue = balanceDue;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ReceiptTotalResult withTotal(Double total) {
        this.total = total;
        return this;
    }

    public Double getJ4uCouponsSavings() {
        return j4uCouponsSavings;
    }

    public void setJ4uCouponsSavings(Double j4uCouponsSavings) {
        this.j4uCouponsSavings = j4uCouponsSavings;
    }

    public ReceiptTotalResult withJ4uCouponsSavings(Double j4uCouponsSavings) {
        this.j4uCouponsSavings = j4uCouponsSavings;
        return this;
    }

    public Double getTotalSavings() {
        return totalSavings;
    }

    public void setTotalSavings(Double totalSavings) {
        this.totalSavings = totalSavings;
    }

    public ReceiptTotalResult withTotalSavings(Double totalSavings) {
        this.totalSavings = totalSavings;
        return this;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }

    public ReceiptTotalResult withTax(Double tax) {
        this.tax = tax;
        return this;
    }
    public Double getEbt() {
        return ebt;
    }

    public void setEbt(Double ebt) {
        this.ebt = ebt;
    }

    public ReceiptTotalResult withEbt(Double ebt) {
        this.ebt = ebt;
        return this;
    }
    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public ReceiptTotalResult withSubTotal(Double subTotal) {
        this.subTotal = subTotal;
        return this;
    }

    public Double getClubCardSavings() {
        return clubCardSavings;
    }

    public void setClubCardSavings(Double clubCardSavings) {
        this.clubCardSavings = clubCardSavings;
    }

    public ReceiptTotalResult withClubCardSavings(Double clubCardSavings) {
        this.clubCardSavings = clubCardSavings;
        return this;
    }

    public Double getBalanceDue() {
        return balanceDue;
    }

    public void setBalanceDue(Double balanceDue) {
        this.balanceDue = balanceDue;
    }

    public ReceiptTotalResult withBalanceDue(Double balanceDue) {
        this.balanceDue = balanceDue;
        return this;
    }


}
