/*
*/
package com.example.bohra.savvysavingappworking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CreatePinActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createpin);

        //this activity  uses the same pinfile from PinEnter that it writes the content to

        Button confirmButton2 = (Button) findViewById(R.id.ConfirmPinCreatebutton);
        confirmButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText enterPinText = (EditText) findViewById(R.id.enterPinText);



                //int pinSupply = Integer.parseInt(enterPinText.getText().toString());
                Integer pinToSend = Integer.parseInt(enterPinText.getText().toString());



                IO io = new IO();
                if((!enterPinText.getText().toString().equals("")) && (enterPinText.getText().toString().length() == 4))
                {
                    pinToSend *= 11;
                    io.writeFile("pin.txt",pinToSend.toString());
                    Intent incomeSetIntent = new Intent(getApplicationContext(), SetIncomeActivity.class);
                    startActivity(incomeSetIntent);
                }
                else
                {
                    Toast notToast = Toast.makeText(getApplicationContext(), "Please enter a valid 4 Digit PIN!", Toast.LENGTH_SHORT);
                    notToast.show();
                }
            }
        });


    }

}
