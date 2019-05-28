package com.example.bohra.savvysavingappfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.app.Notification;
import android.app.NotificationManager;

public class PaymentSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_schedule);

        final RadioButton week = (RadioButton) findViewById(R.id.weeklybutton);
        final RadioButton fort = (RadioButton) findViewById(R.id.fortnightly);
        final RadioButton month = (RadioButton) findViewById(R.id.monthly);
        final EditText enterdate = (EditText) findViewById(R.id.editText);
        final IO io = new IO();
        final Button butt = (Button) findViewById(R.id.pushnotification);


        final CompoundButton.OnCheckedChangeListener checkedIn = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    switch (buttonView.getId()) {
                        case R.id.weeklybutton:
                            int length = 7;
                            break;
                        case R.id.fortnightly:
                            int length2 = 14;
                            break;
                        case R.id.monthly:
                            int length3 = 30;
                            break;
                    }
                }
            }
        };
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String date = enterdate.getText().toString();
                if (date.isEmpty() == false) {

                    Integer dateValue = Integer.parseInt(enterdate.getText().toString());

                    if ((!enterdate.getText().toString().equals("")) && (enterdate.getText().toString().length() == 9)) {
                        dateValue += 7;
                        io.writeFile("due.txt", dateValue.toString());
                        Intent dateschedule = new Intent(getApplicationContext(), Period.class);
                        startActivity(dateschedule);
                    }
                    //If the pin is the incorrect format, then it will display an error message asking for a valid pin
                    else {
                        Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a date entered like 20082019", Toast.LENGTH_SHORT);
                        notToast.show();
                    }
                } else {
                    Toast invalidToast = Toast.makeText(getApplicationContext(), "Please enter a valid date!", Toast.LENGTH_SHORT);
                    invalidToast.show();
                }

            }


        });
        fort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String date = enterdate.getText().toString();
                if (date.isEmpty() == false) {

                    Integer dateValue = Integer.parseInt(enterdate.getText().toString());

                    if ((!enterdate.getText().toString().equals("")) && (enterdate.getText().toString().length() == 9)) {
                        dateValue += 14;
                        io.writeFile("due.txt", dateValue.toString());
                        Intent dateschedule = new Intent(getApplicationContext(), Period.class);
                        startActivity(dateschedule);
                    }
                    //If the pin is the incorrect format, then it will display an error message asking for a valid pin
                    else {
                        Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a date entered like 20082019", Toast.LENGTH_SHORT);
                        notToast.show();
                    }
                } else {
                    Toast invalidToast = Toast.makeText(getApplicationContext(), "Please enter a valid date!", Toast.LENGTH_SHORT);
                    invalidToast.show();
                }

            }


        });


        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String date = enterdate.getText().toString();
                if (date.isEmpty() == false) {

                    Integer dateValue = Integer.parseInt(enterdate.getText().toString());

                    if ((!enterdate.getText().toString().equals("")) && (enterdate.getText().toString().length() == 9)) {
                        dateValue += 30;
                        io.writeFile("due.txt", dateValue.toString());
                        Intent dateschedule = new Intent(getApplicationContext(), Period.class);
                        startActivity(dateschedule);
                    } else {
                        Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a date entered like 20082019", Toast.LENGTH_SHORT);
                        notToast.show();
                    }
                } else {
                    Toast invalidToast = Toast.makeText(getApplicationContext(), "Please enter a valid date!", Toast.LENGTH_SHORT);
                    invalidToast.show();
                }

            }


        });

         butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager notif = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                Notification notify = new Notification.Builder
                        (getApplicationContext()).setContentTitle("Due date").setContentText("due date is: ").
                        setContentTitle("2019").setSmallIcon(R.drawable.ic_notifications_black_24dp).build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notif.notify(0, notify);
            }



        });
    }

}
