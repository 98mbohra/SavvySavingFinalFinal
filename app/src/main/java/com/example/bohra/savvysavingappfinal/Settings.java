/*
This class contains the Java logical code for the settings page
It allows the tex fields to show the set data that was entered in the app setup process
 */

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
    //Calender Functionality for selecting savings goal date
    android.icu.util.Calendar currentTime = android.icu.util.Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        //Setting the layout to be the setting page xml file
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //Getting the long time from the calender function
        Long minimum = currentTime.getTime().getTime();

        //Deceleration of variables
        final CalendarView savingCalendar = (CalendarView) findViewById(R.id.calendarView);
        final EditText goalAmount = (EditText) findViewById(R.id.savingAmountEditText);
        final EditText pledgeAmount = (EditText) findViewById(R.id.pledgeAmountEditText);
        Button changeIncome = (Button) findViewById(R.id.buttonChangeIncome);
        Button changeSavings = (Button) findViewById(R.id.buttonChangeIncome);
        Button changeSettings = (Button) findViewById(R.id.buttonChangeIncome);

        //Creation of the IO class variable to push and pull data to and from the text file.
        IO ioRead = new IO();

        //Arrays that store the users data and show it in the text fields through the xml file
        String[] array = ioRead.readFile("finance.txt").split("\n");
        String[] incomeValue = array[0].split(":");
        String[] savingsGoalValue = array[2].split(":");
        String[] pledgeGoalValue = array[6].split(":");
        Spinner settingsSpinner = (Spinner)findViewById(R.id.spinnerSettings);

        //Setting the text to be viewed in the the xml file
        EditText savingsGoal = (EditText)findViewById(R.id.editText4);
        savingsGoal.setText(savingsGoalValue[1]);

        EditText incomeValueText = (EditText)findViewById(R.id.editText8);
        incomeValueText.setText(incomeValue[1]);

        EditText pledge = (EditText)findViewById(R.id.editText9);
        pledge.setText(pledgeGoalValue[1]);

        //This is the action listener for the button that changed the vales of the textfields
        changeSettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Spinner pledgePeriodSpinner = (Spinner) findViewById(R.id.periodSpinner3);
                String goalCheck = goalAmount.getText().toString();
                String pledgeCheck = pledgeAmount.getText().toString();

                //Constraints to ensure sure users input is correct
                if ((goalCheck.isEmpty()) || (pledgeCheck.isEmpty()))
                {
                    Toast goalLowToast = Toast.makeText(getApplicationContext(), "You must have values in the text areas!", Toast.LENGTH_SHORT);
                    goalLowToast.show();
                }
                else
                {
                    int goalAmountToSend = Integer.parseInt(goalAmount.getText().toString());


                    int pledgeAmountToSend = Integer.parseInt(pledgeAmount.getText().toString());

                    if ((goalAmountToSend < 0) || (pledgeAmountToSend < 0))
                    {
                        Toast goalLowToast = Toast.makeText(getApplicationContext(), "You must have values in the text areas!", Toast.LENGTH_SHORT);
                        goalLowToast.show();
                    }
                    else
                    {
                        //Adding and appending the text file with the new information declared by the user
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
