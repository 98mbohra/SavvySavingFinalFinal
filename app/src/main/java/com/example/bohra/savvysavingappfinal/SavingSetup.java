package com.example.bohra.savvysavingappfinal;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SavingSetup extends AppCompatActivity {

    //This app will open the finance db and send the saving set up data

    android.icu.util.Calendar currentTime = android.icu.util.Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_setup);
        Long min = currentTime.getTime().getTime();
        final CalendarView savingCalendar = (CalendarView) findViewById(R.id.calendarView);
        savingCalendar.setMinDate(min);
        final EditText goalAmount = (EditText) findViewById(R.id.savingAmountEditText);
        final EditText pledgeAmount = (EditText) findViewById(R.id.pledgeAmountEditText);

        Button goHomeBtn = (Button) findViewById(R.id.continueDateButton);
        goHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int goalAmountToSend = Integer.parseInt(goalAmount.getText().toString());
                int pledgeAmountToSend = Integer.parseInt(pledgeAmount.getText().toString());
                Spinner pledgePeriodSpinner = (Spinner)  findViewById(R.id.periodSpinner3);
                //Period incomePeriod = Period.valueOf(incomePeriodSpinner.getSelectedItem().toString());



                if ((goalAmountToSend < 0) || (pledgeAmountToSend < 0))
                {
                    Toast goalLowToast = Toast.makeText(getApplicationContext(), "Your must set a pledge and goal!", Toast.LENGTH_SHORT);
                    goalLowToast.show();
                }
                else
                {

                    //send goal amount, pledge amount, period value to db
                    Date d = new Date(Long.parseLong(String.valueOf(savingCalendar.getDate())));
                    SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                    String dateText = df2.format(d);
                    String content = "Goal:" + goalAmount.getText().toString()+"\n"+
                            "Target Date Long:" + savingCalendar.getDate() +"\n"+
                            "Readable Date:"+ dateText + "\n" +
                            "Pledge Period:" + pledgePeriodSpinner.getSelectedItem().toString() +"\n"+
                            "Pledge Goal:" + pledgeAmount.getText().toString()+"\n";
                    IO io = new IO();
                    io.updateFile("finance.txt",content);

                    Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(goToHome);
                    //send goal amount, pledge amount, period value to db

                }



            }
        });







    }
}
