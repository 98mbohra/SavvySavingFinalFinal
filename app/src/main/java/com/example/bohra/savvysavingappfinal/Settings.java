package com.example.bohra.savvysavingappfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.*;



public class Settings extends AppCompatActivity
{
    android.icu.util.Calendar currentTime = android.icu.util.Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        Long minimum = currentTime.getTime().getTime();

        final CalendarView savingCalendar = (CalendarView) findViewById(R.id.calendarView);
        //savingCalendar.setMinDate(minimum);

        final EditText goalAmount = (EditText) findViewById(R.id.savingAmountEditText);
        final EditText pledgeAmount = (EditText) findViewById(R.id.pledgeAmountEditText);

        Button changeIncome = (Button) findViewById(R.id.buttonChangeIncome);
        Button changeSavings = (Button) findViewById(R.id.buttonChangeIncome);

        Button changeIncomeButton = (Button) findViewById(R.id.buttonChangeIncome);

        IO ioRead = new IO();

        String[] array = ioRead.readFile("finance.txt").split("\n");

        String[] incomeValue = array[0].split(":");
        String[] savingsGoalValue = array[2].split(":");
        String[] pledgeGoalValue = array[6].split(":");

        Spinner settingsSpinner = (Spinner)findViewById(R.id.spinnerSettings);
        //settingsSpinner.setSelection();

        EditText savingsGoal = (EditText)findViewById(R.id.editText4);
        savingsGoal.setText(savingsGoalValue[1]);

        EditText incomeValueText = (EditText)findViewById(R.id.editText8);
        incomeValueText.setText(incomeValue[1]);

        EditText pledge = (EditText)findViewById(R.id.editText9);
        pledge.setText(pledgeGoalValue[1]);

        changeIncomeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Spinner pledgePeriodSpinner = (Spinner) findViewById(R.id.periodSpinner3);
                //Period incomePeriod = Period.valueOf(incomePeriodSpinner.getSelectedItem().toString());
                String goalCheck = goalAmount.getText().toString();
                String pledgeCheck = pledgeAmount.getText().toString();

                if ((goalCheck.isEmpty()) || (pledgeCheck.isEmpty()))
                {
                    Toast goalLowToast = Toast.makeText(getApplicationContext(), "Your must set a pledge and goal!", Toast.LENGTH_SHORT);
                    goalLowToast.show();
                }
                else
                {
                    int goalAmountToSend = Integer.parseInt(goalAmount.getText().toString());


                    int pledgeAmountToSend = Integer.parseInt(pledgeAmount.getText().toString());

                    if ((goalAmountToSend < 0) || (pledgeAmountToSend < 0))
                    {
                        Toast goalLowToast = Toast.makeText(getApplicationContext(), "Your must set a pledge and goal!", Toast.LENGTH_SHORT);
                        goalLowToast.show();
                    }

                    //This is the calender function that is used to allow user to set a saving goal date in a visually
                    //gratifying way.
                    else
                    {
                        //Date d = new Date(Long.parseLong(String.valueOf(savingCalendar.getDate())));
                        //SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy");
                        //String dateText = df2.format(d);

                        String content = "Goal:" + goalAmount.getText().toString() + "\n" +
                                "Target Date Long:" + savingCalendar.getDate() + "\n" +
                                "Readable Date:" + savingCalendar.getDate() + "\n" +
                                "Pledge Period:" + pledgePeriodSpinner.getSelectedItem().toString() + "\n" +
                                "Pledge Goal:" + pledgeAmount.getText().toString() + "\n";

                        IO io = new IO();
                        io.updateFile("finance.txt", content);

                        Intent goToHome = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(goToHome);
                    }
                }
            }
        });
    }

}
