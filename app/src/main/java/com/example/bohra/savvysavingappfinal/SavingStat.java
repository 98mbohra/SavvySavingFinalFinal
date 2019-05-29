package com.example.bohra.savvysavingappfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SavingStat extends AppCompatActivity {

    private String savingPeriod;
    private String savingPledge = "20";
    private String savingGoal = "200";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_stat);

        ProgressBar savingBar = (ProgressBar) findViewById(R.id.vertical_progressbar);
        setSavingBar();
        savingBar.setProgress((Integer.parseInt(savingPledge)) * 20);
        savingBar.setMax(Integer.parseInt(savingGoal));
    }

    public void setSavingBar()
    {
       IO io = new IO();
       String content = io.readFile("finance.txt");
       String[] arr = content.split("\n");


        for (String s : arr)
        {
           String[] arr2 = s.split(":");

            if (arr2[0].equals("Pledge Period")) savingPeriod = arr2[1];
            if (arr2[0].equals("Pledge Goal")) savingPledge = arr2[1];
            if (arr2[0].equals("Goal")) savingGoal = arr2[1];
        }




    }
}
