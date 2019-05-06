package com.example.bohra.savvysavingappfinal;

public enum Period {
    Day(1),
    Week(7),
    Fortnite(14),
    Month(30);

    private int dayCount;

    private int getDayCount(){
        return dayCount;
    }


    private Period(int dayCount){
        this.dayCount = dayCount;
    }
}
