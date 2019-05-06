package com.example.bohra.savvysavingappworking;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.bohra.savvysavingappworking.Period;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;

public class Finance extends AppCompatActivity
{

    public Finance()
    {
        //instantiate all data
       /* if  checkPriorUse(); is true then continue
       check pin
       LOAD
       fillSavings, fillPurchases, fillFixed costs, financesData
       All data should be set up at this point
        */

    }

    @Override
    protected void onCreate(Bundle saved)
    {
        super.onCreate(saved);
        //setContentView(R.layout.activity_home_screen);
        DatabaseHelper db = new DatabaseHelper(this);
    }
    private int income; //In finance db
    private Period incomePeriod; //In finance db
    private int fixedCosts; //in finance db
    private String savePeriod; //in finance db
    private int savingAmount; //in finance db
    private int savingGoal; //in finance db

    private int budget; // calculated on initialising

    public Date currentDate; // Holds the current date on start up, updates whenever a transaction is made


    private int pinCode; //used for setting and taking input held in file
    private String pinFileAddress; //fixed path to pin text file which contains encoded pin

    private String linkToSavingDB ;  //Fixed link to the db
    private String linkToPurchasesDB;  //Fixed link to the db
    private String linkToFixedPaymentsDB;  //Fixed link to the db

    private ArrayList<Purchase>  purchaseList= new ArrayList<Purchase>(); //arraylist which is loaded from db
    private ArrayList<FixedPayment> fixedPayments = new ArrayList<FixedPayment>(); //arraylist which is loaded from db
    private ArrayList<Saving> savings = new ArrayList<Saving>(); //filled from db

    DatabaseHelper db = new DatabaseHelper(this);

    public void closingSequence(){
        /*
        This method will use submethods to clear each database return all arraylists to database
        Close connections and datafiles
         */
    }

    public void checkPriorUse()
    {
        /*
        if pinfile is empty
        then run through the views for the starting sequence gathering new pin income, fixed costs, saving goal, amount, and period.
        Remind user that it is their responsibility to deduct funds when saving
        send data to db and files as recorded
        send user to first enter user pin screen
         */
    }

    public void addFixedCost(){
        /*Adds a new fixed cost to the arraylist
        Takes in the amount, period, category.
         */
    }



    public void fillSavings () throws ParseException {
        SQLiteDatabase saving = db.getReadableDatabase();
        Cursor c = saving.query("SAVING",new String[]{"AMOUNT","DATEMOVED"},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            int amount = Integer.parseInt(c.getString(0));
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd" );
            Date dateMoved = fm.parse(c.getString(1));      //???format of columnlndex:1 and pattern must be same
            Saving s = new Saving(amount,dateMoved);
            savings.add(s);
        }
        /*
        This method connects to savings database, and iteratively loads all the saving records into the arraylist
         */
        //ERIC

    }

    public void fillPurchases() throws ParseException{
        SQLiteDatabase purchase = db.getReadableDatabase();
        Cursor c = purchase.query("PURCHASE",new String[]{"PURCHASETYPE","COMMENT","DATEOFPURCHASE","PURCHASEAMOUNT"},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            //String purchasetype = c.getString(0);
            SpendingCategory purchasetype = SpendingCategory.valueOf(c.getString(0));
            String comment = c.getString(1);
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd" );
            Date dateOfPurchase = fm.parse(c.getString(2));
            int purchaseAmount = Integer.parseInt(c.getString(3));
            Purchase p = new Purchase(purchasetype,comment,dateOfPurchase,purchaseAmount);
            purchaseList.add(p);
        }
        /*
        This method connects to purchase database and iteratively loads all purchase records into the arraylist
         */

        //ERIC
    }

    public void fillFixed() throws ParseException
    {
        SQLiteDatabase fillFixed = db.getReadableDatabase();
        Cursor c = fillFixed.query("FIXEDPAYMENT",new String[]{"RECURRMENT","FIXEDCATEGORY","FIXEDAMOUNT","DATEOCCUR"},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            Period recurrment = Period.valueOf(c.getString(0));
            SpendingCategory fixedCategory = SpendingCategory.valueOf(c.getString(1));
            int fixedAmount = Integer.parseInt(c.getString(2));
            SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd" );
            Date dateOccur = fm.parse(c.getString(3));
            FixedPayment f = new FixedPayment(recurrment,fixedCategory,fixedAmount);
            fixedPayments.add(f);
        }
        /*
        This Method connects to the fixed payment database and iteratively loads all fixed payments into the arraylist
         */

        //ERIC
    }

    public void fillFinance() throws ParseException
    {
        SQLiteDatabase fillFinance = db.getReadableDatabase();
        Cursor c = fillFinance.query("FINANCE",new String[]{"INCOME","PERIOD","FIXEDCOSTS","SAVEPERIOD","SAVINGAMOUNT","SAVINGGOAL"},null,null,null,null,null,null);
        while (c.moveToNext())
        {
            int income = Integer.parseInt(c.getString(0));
            Period period = Period.valueOf(c.getString(1));
            int fixedCosts = Integer.parseInt(c.getString(2));
            String savePeriod = c.getString(3);
            int savingAmount = Integer.parseInt(c.getString(4));
            int savingGoal = Integer.parseInt(c.getString(5));

            //??? then? create new finance on finance class?
        }
        /*
        This method fills all other financial data from the finance database (income e.t.c.)
         */
        //ERIC
    }

    public int checkPin()
    {
        String pin;

        /*
        This method will load the encoded pin from the pincode file, decrypt the pincode, and then compare it with the entered pin
        It will return a 1 if the two match
         */

        //ARSHAD
        return 1;
    }

    public void assignPin(String pin){
        /*
        This method will take a new pin encrypt it, return it to the pin file.
         */

        //ARSHAD


    }

    public void changePin(){
        /*
        This method will first use check pin, if successful it will then
        use the assignPin method to send the new pin to the file.
         */

        //ARSHAD
    }

    public void addPurchase(SpendingCategory purchaseType, String comment, Date currentDate, int purchaseAmount){
        /*
        This method will take a purchase values specified by the input fields, and device specified current date
          to generate a new purchase object, and add it to the arraylist
        Subtracts amount from the income value
         */
    }

    public void handleSaving(){
        /*
        This method checks the data of last saving contribution from saving.getLast() or whatever,
        and then uses the saving period and decides whether a saving needs to be made. If monthly it checks that next date is the second of month
        It deducts from the income.
         */
    }

    public void undoPurchase(){
        /*
        This method will remove the last purchase from the arraylist, prompting the user with the details of the last purchase before
        Accepting
         */
    }


    public void closeSavings(){
        SQLiteDatabase saving = db.getWritableDatabase();
        saving.execSQL("DROP TABLE IF EXISTS SAVING");
        saving.execSQL("CREATE TABLE IF NOT EXISTS SAVING(AMOUNT INTEGER, DATEMOVED DATE)");
        for(Saving s:savings)
        {
            int amount = s.getAmount();
            Date dateMoved = s.getDateMoved();
            saving.execSQL("INSERT INTO SAVING (AMOUNT, DATEMOVED) VALUES(amount,dateMoved)");
        }
        /*
        return arraylist savings to db
        //ERIC
         */
    }

    public void closePurchases(){
        SQLiteDatabase purchase = db.getWritableDatabase();
        purchase.execSQL("DROP TABLE IF EXISTS PURCHASE");
        purchase.execSQL("CREATE TABLE IF NOT EXISTS PURCHASE(PURCHASETYPE VARCHAR(20), COMMENT VARCHAR(20), DATEOFPURCHASE DATE, PURCHASEAMOUNT INTEGER)");
        for(Purchase p:purchaseList)
        {
            String purchaseType = p.getPurchaseType().toString();
            String comment = p.getComment();
            Date dateOfPurchase = p.getDateOfPurchase();
            int purchaseAmount = p.getPurchaseAmount();
            purchase.execSQL("INSERT INTO SAVING (PURCHASETYPE, COMMENT, DATEOFPURCHASE, PURCHASEAMOUNT) VALUES(purchaseType,comment,dateOfPurchase,purchaseAmount)");
        }
        /*
        return arraylist purchases to db
        //ERIC
         */
    }

    public void closeFixedPayments(){
        SQLiteDatabase fixedPayment = db.getWritableDatabase();
        fixedPayment.execSQL("DROP TABLE IF EXISTS PURCHASE");
        fixedPayment.execSQL("CREATE TABLE IF NOT EXISTS FIXEDPAYMENT(RECURRMENT VARCHAR(20), FIXEDCATEGORY VARCHAR(20),FIXEDAMOUNT INTEGER, DATEOCCUR DATE)");
        for(FixedPayment f:fixedPayments)
        {
            String recurrment = f.getRecurrment().toString();
            String fixedAtegory = f.getFixedCategory().toString();
            int fixAmount = f.getFixedAmount();

            // ???no getDateOccur() method
            //fixedPayment.execSQL("INSERT INTO FIXEDPAYMENT () VALUES()");
        }

        /*
        return arraylist fixedPayments to db
        //ERIC
         */
    }

    public void closeFinances(){
        /*
        Return finance data to the db
        //ERIC
         */
    }

    public ArrayList<Saving> getSavings() {
        return savings;
    }

    public void setSavings(ArrayList<Saving> savings) {
        this.savings = savings;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public int getFixedCosts() {
        return fixedCosts;
    }

    public void setFixedCosts(int fixedCosts) {
        this.fixedCosts = fixedCosts;
    }

    public String getLinkToSavingDB() {
        return linkToSavingDB;
    }

    public void setLinkToSavingDB(String linkToSavingDB) {
        this.linkToSavingDB = linkToSavingDB;
    }

    public String getLinkToPurchasesDB() {
        return linkToPurchasesDB;
    }

    public void setLinkToPurchasesDB(String linkToPurchasesDB) {
        this.linkToPurchasesDB = linkToPurchasesDB;
    }

    public String getLinkToFixedPaymentsDB() {
        return linkToFixedPaymentsDB;
    }

    public void setLinkToFixedPaymentsDB(String linkToFixedPaymentsDB) {
        this.linkToFixedPaymentsDB = linkToFixedPaymentsDB;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }


    public String getPinFileAddress() {
        return pinFileAddress;
    }

    public void setPinFileAddress(String pinFileAddress) {
        this.pinFileAddress = pinFileAddress;
    }

    public ArrayList<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(ArrayList<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    public ArrayList<FixedPayment> getFixedPayments() {
        return fixedPayments;
    }

    public void setFixedPayments(ArrayList<FixedPayment> fixedPayments) {
        this.fixedPayments = fixedPayments;
    }


    public String getSavePeriod() {
        return savePeriod;
    }

    public void setSavePeriod(String savePeriod) {
        this.savePeriod = savePeriod;
    }

    public int getSavingAmount() {
        return savingAmount;
    }

    public void setSavingAmount(int savingAmount) {
        this.savingAmount = savingAmount;
    }

    public int getSavingGoal() {
        return savingGoal;
    }

    public void setSavingGoal(int savingGoal) {
        this.savingGoal = savingGoal;
    }


}
