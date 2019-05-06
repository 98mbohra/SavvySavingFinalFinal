package com.example.bohra.savvysavingappworking;

import java.util.Date;

public class Saving {

    private int amount;
    private Date dateMoved;


    public Saving(int amount, Date dateMoved) {
        this.amount = amount;
        this.dateMoved = dateMoved;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDateMoved() {
        return dateMoved;
    }

    public void setDateMoved(Date dateMoved) {
        this.dateMoved = dateMoved;
    }



}
