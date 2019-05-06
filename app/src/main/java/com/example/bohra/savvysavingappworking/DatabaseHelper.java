package com.example.bohra.savvysavingappworking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String name = "Savvy";
    private static final int version = 1;
    DatabaseHelper(Context context)
    {
        super(context, name, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS FINANCE(INCOME INTEGER, PERIOD VARCHAR(20), FIXEDCOSTS INTEGER, SAVEPERIOD VARCHAR(20), SAVINGAMOUNT INTEGER, SAVINGGOAL INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS PURCHASE(PURCHASETYPE VARCHAR(20), COMMENT VARCHAR(20), DATEOFPURCHASE DATE, PURCHASEAMOUNT INTEGER)");
        db.execSQL("CREATE TABLE IF NOT EXISTS SAVING(AMOUNT INTEGER, DATEMOVED DATE)");
        db.execSQL("CREATE TABLE IF NOT EXISTS FIXEDPAYMENT(RECURRMENT VARCHAR(20), FIXEDCATEGORY VARCHAR(20),FIXEDAMOUNT INTEGER, DATEOCCUR DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Operation of changing database version
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        // TODO Every time the database is successfully opened, it is first executed
    }
}

