package com.example.bohra.savvysavingappfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SavingStat extends AppCompatActivity {

    private String savingPeriod = "";
    private float savingPledge;
    private String savingPledgeTxt = "";
    private String savingGoal = "200";
    private float savingGoalInt = 0;
    private float savedTotalInt = 0;
    private String savedTotal;
    private float savedPercent;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_stat);

        ProgressBar savingBar = (ProgressBar) findViewById(R.id.vertical_progressbar);
        TextView pledgeVal = (TextView) findViewById(R.id.pledge_text);
        TextView period = (TextView) findViewById(R.id.periodText);
        TextView goalText = (TextView) findViewById(R.id.goalText);
        TextView accruedTotal = (TextView) findViewById(R.id.savedAmountText);
        TextView savePercent  = (TextView) findViewById(R.id.savedPercent);




        loadSavingValues();

        savingBar.setProgress(Math.round(savingPledge));

        savingBar.setMax(Integer.parseInt(savingGoal));
        savingPledgeTxt = "Pledge: $"+savingPledge;


        pledgeVal.setText(savingPledgeTxt);

        period.setText("Period: Week");

        savedTotal = "Saved: $" +Float.toString(savingPledge);
        savingGoal = "Goal: $"+Integer.parseInt(savingGoal);

        goalText.setText(savingGoal);


        accruedTotal.setText(savedTotal);

        savedTotalInt = savingPledge;

        savedPercent = (savedTotalInt/savingGoalInt) * 100;
        savedPercent = 10;
        String percentString = Float.toString(savedPercent) + "%";

        savePercent.setText(percentString);
    }

    public void loadSavingValues()
    {
       IO io = new IO();
       String content = io.readFile("finance.txt");
       String[] arr = content.split("\n");


        for (String s : arr)
        {
           String[] arr2 = s.split(":");

            if (arr2[0].equals("Pledge Period")) savingPeriod.concat("Week");
            if (arr2[0].equals("Pledge Goal")) savingPledge = Float.parseFloat(arr2[1]);

            if (arr2[0].equals("Goal")){ savingGoal = arr2[1];
            savingGoalInt = Float.parseFloat(arr2[1]);}
        }
    }

}
