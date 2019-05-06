package com.example.bohra.savvysavingappworking;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    // This class will need a method to hold an arraylist of savings
    // This class will need a method to hold an arraylist of Purchases
    //This class will need to hold the income details and period.

    private float income = 0;
    private String incomePeriod;
    private String savingPeriod;
    private float savingPledge = 0;
    private float totalDailyFixed = 0;
    private float totalDailyPurchase = 0;



    private ArrayList<FixedPayment> fixedPayments = new ArrayList<FixedPayment>();
    android.icu.util.Calendar currentTime = android.icu.util.Calendar.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


        final TextView incomeText = (TextView) findViewById(R.id.incomeView);
        final TextView savingPledgeText = (TextView) findViewById(R.id.savingPledgeView);
        final TextView totalOfCosts = (TextView) findViewById(R.id.t_costsView);
        final TextView budgetView = (TextView) findViewById(R.id.dispoBudget);




        final EditText purchaseAmountEditText = (EditText) findViewById(R.id.PurchaseAmounteditText);
        final EditText purchaseCommentEditText = (EditText) findViewById(R.id.PurchaseCommentText2);
        final Spinner purchaseScategorySpinner = (Spinner) findViewById(R.id.newPurchaseCategory);
        final ProgressBar dailyBreakdown = (ProgressBar) findViewById(R.id.progressBar) ;
        //This should contain weekly income/7 to get the daily income
        dailyBreakdown.setMax(100);
        //This should contain the sum of fixed costs/number of days for each fixed cost +  total purchases for the week/7 so to make daily budget spent
        dailyBreakdown.setProgress(30);
        //This should contain the saving amount per week + the sum costs amount
        dailyBreakdown.setSecondaryProgress(40);



        calculatepreProgressAmount();


        dailyBreakdown.setMax(Math.round(income));
        float totalProgress2 = totalDailyFixed + totalDailyPurchase;
        dailyBreakdown.setProgress(Math.round(totalProgress2));

        dailyBreakdown.setSecondaryProgress(Math.round(totalProgress2+savingPledge));

        //The background will represent the remaining amount and should be calculated

        incomeText.setText("Income: "+income);
        savingPledgeText.setText("Pledge:" +savingPledge);
        totalOfCosts.setText("Total costs" +totalDailyFixed);
        budgetView.setText("Budget: " +(income - (totalDailyFixed + savingPledge)));



        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//                Snackbar.make(view, "Purchase Added!", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//


                String comment = purchaseCommentEditText.getText().toString();
                String purchaseAmount = purchaseAmountEditText.getText().toString();
                String purchaseCategory = purchaseScategorySpinner.getSelectedItem().toString();
                Long current = currentTime.getTime().getTime();
                Date d = new Date(current);
                SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                String dateText = df2.format(d);

                String content = comment+":"+purchaseAmount+":"+ purchaseCategory+":"+dateText+"\n";

                String filename = "purchase.txt";
                File file = new File(Environment.getExternalStorageDirectory(),filename);
                IO io = new IO();
                if(!file.exists()) io.writeFile(filename,content);
                else io.updateFile(filename,content);

                calculateProgressAmount();
                dailyBreakdown.setMax(Math.round(income));
                float totalProgress2 = totalDailyFixed + totalDailyPurchase;
                dailyBreakdown.setProgress(Math.round(totalProgress2));

                dailyBreakdown.setSecondaryProgress(Math.round(totalProgress2+savingPledge));
                totalOfCosts.setText("Total costs" +totalProgress2);
                budgetView.setText("Budget: " +(income - ((totalDailyFixed + savingPledge) + totalDailyPurchase)));



//                Snackbar.make(view, "Purchase Added" + totalDailyPurchase, Snackbar.LENGTH_LONG)
//                      .setAction("Action", null).show();

                Snackbar.make(view, "Purchase Added" + totalDailyPurchase, Snackbar.LENGTH_LONG)
                      .setAction("Action", null).show();




            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void calculateProgressAmount() {
        IO io = new IO();


        String content = io.readFile("finance.txt");
        String[] arr = content.split("\n");
        for (String s : arr) {
            String[] arr2 = s.split(":");
            if (arr2[0].equals("income")) income = Float.parseFloat(arr2[1]);
            if (arr2[0].equals("incomePeriod")) incomePeriod = arr2[1];
            if (arr2[0].equals("Pledge Period")) savingPeriod = arr2[1];
            if (arr2[0].equals("Pledge Goal")) savingPledge = Float.parseFloat(arr2[1]);


        }
        switch (savingPeriod) {
            case "Week":
                savingPledge /= 7;
                break;
            case "Fortnight":
                savingPledge /= 14;
                break;
            case "Month":
                savingPledge /= 30;
                break;
        }

        switch (incomePeriod) {
            case "Week":
                income /= 7;
                break;
            case "Fortnight":
                income /= 14;
                break;
            case "Month":
                income /= 30;
                break;
        }

        Long current = currentTime.getTime().getTime();
        Date d = new Date(current);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(d);


        String p_content = io.readFile("purchase.txt");
        String[] p_arr = p_content.split("\n");
        for (String s : p_arr) {
            String[] arr2 = s.split(":");
            if(arr2[3].equals(dateText))
            totalDailyPurchase += Float.parseFloat(arr2[1]);
        }

        String fc_content = io.readFile("FixedCosts.txt");
        String[] fc_arr = fc_content.split("\n");
        for (String s : fc_arr) {
            String[] arr2 = s.split(":");
            double fixedTemp = (Float.parseFloat(arr2[2]));
            String fixedCategory = arr2[0].toString();
            switch (fixedCategory) {
                case "Week":
                    fixedTemp /= 7;
                    break;
                case "Fortnight":
                    fixedTemp /= 14;
                    break;
                case "Month":
                    fixedTemp /= 30;
                    break;
            }
            totalDailyFixed += fixedTemp;

        }

    }

    public void calculatepreProgressAmount() {
        IO io = new IO();


        String content = io.readFile("finance.txt");
        String[] arr = content.split("\n");
        for (String s : arr) {
            String[] arr2 = s.split(":");
            if (arr2[0].equals("income")) income = Float.parseFloat(arr2[1]);
            if (arr2[0].equals("incomePeriod")) incomePeriod = arr2[1];
            if (arr2[0].equals("Pledge Period")) savingPeriod = arr2[1];
            if (arr2[0].equals("Pledge Goal")) savingPledge = Float.parseFloat(arr2[1]);


        }
        switch (savingPeriod) {
            case "Week":
                savingPledge /= 7;
                break;
            case "Fortnight":
                savingPledge /= 14;
                break;
            case "Month":
                savingPledge /= 30;
                break;
        }

        switch (incomePeriod) {
            case "Week":
                income /= 7;
                break;
            case "Fortnight":
                income /= 14;
                break;
            case "Month":
                income /= 30;
                break;
        }

        Long current = currentTime.getTime().getTime();
        Date d = new Date(current);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
        String dateText = df2.format(d);

        if(io.checkFileExists("purchase.txt") == 1) {
            String p_content = io.readFile("purchase.txt");
            String[] p_arr = p_content.split("\n");
            for (String s : p_arr) {
                String[] arr2 = s.split(":");
                if (arr2[3].equals(dateText))
                    totalDailyPurchase += Float.parseFloat(arr2[1]);
            }
        }

        String fc_content = io.readFile("FixedCosts.txt");
        String[] fc_arr = fc_content.split("\n");
        for (String s : fc_arr) {
            String[] arr2 = s.split(":");
            double fixedTemp = (Float.parseFloat(arr2[2]));
            String fixedCategory = arr2[0].toString();
            switch (fixedCategory) {
                case "Week":
                    fixedTemp /= 7;
                    break;
                case "Fortnight":
                    fixedTemp /= 14;
                    break;
                case "Month":
                    fixedTemp /= 30;
                    break;
            }
            totalDailyFixed += fixedTemp;

        }

    }
}





