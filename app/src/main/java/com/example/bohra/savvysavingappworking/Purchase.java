package com.example.bohra.savvysavingappworking;

import java.util.Date;

public class Purchase
{
    private SpendingCategory purchaseType;
    private String comment;
    private Date dateOfPurchase;
    private int purchaseAmount;


    public Purchase(SpendingCategory purchaseType, String comment, Date dateOfPurchase, int purchaseAmount) {
        this.purchaseType = purchaseType;
        this.comment = comment;
        this.dateOfPurchase = dateOfPurchase;
        this.purchaseAmount = purchaseAmount;
    }


    public SpendingCategory getPurchaseType() {
        return purchaseType;
    }

    public void setPurchaseType(SpendingCategory purchaseType) {
        this.purchaseType = purchaseType;
    }



    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }



    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }




}
