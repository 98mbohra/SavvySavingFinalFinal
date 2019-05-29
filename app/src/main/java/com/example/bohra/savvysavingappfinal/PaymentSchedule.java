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
        final EditText enterDay = (EditText) findViewById(R.id.enterDay);
        final EditText enterMonth = (EditText) findViewById(R.id.enterMonth);
        final IO io = new IO();
        final Button butt = (Button) findViewById(R.id.pushnotification);



        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                String date = enterMonth.getText().toString();
                String day = enterDay.getText().toString();
                if (!date.isEmpty() && !day.isEmpty()) {

                    int dateValue = Integer.parseInt(date);

                    int dateday = Integer.parseInt(day);

                    if ((enterMonth.getText().toString().equals(""))&&(enterDay.getText().toString().equals(""))) {

                        io.writeFile("due.txt", date);
                        Intent dateschedule = new Intent(getApplicationContext(), Period.class);
                        startActivity(dateschedule);
                    }
                    else {
                        Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a date", Toast.LENGTH_SHORT);
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
