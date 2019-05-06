package com.example.bohra.savvysavingappworking;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;

public class FixedCostsSetup extends AppCompatActivity {

    //This class will need a connection to the fixed costs database
    //It will have a fixed costs arraylist
    //When add is pressed it will take the given data to add to arraylist, when next is pressed it will send data to db

    private ArrayList<FixedPayment> fixedPayments = new ArrayList<FixedPayment>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixedcostssetup);

        Button continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //This method will run a method to send the arraylist to the db, it will then navigate to the saving set up page

                String filename = "FixedCosts.txt";
                File file = new File(Environment.getExternalStorageDirectory(),filename);
                IO io = new IO();
                String content = "";
                for(FixedPayment f:fixedPayments)
                {
                    String recurrment = f.getRecurrment().toString()+":";
                    String fixedAtegory = f.getFixedCategory().toString()+":";
                    String fixAmount = String.valueOf(f.getFixedAmount())+"\n";

                    content += recurrment += fixedAtegory +=fixAmount;

                }
                if(!file.exists()) io.writeFile(filename,content);
                else io.writeFile(filename,content);

                Intent savingSetupIntent = new Intent(getApplicationContext(), SavingSetup.class);
                startActivity(savingSetupIntent);
            }
        });

        Button addButton = (Button)findViewById(R.id.addFixedCostButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText fixedCostAmount = (EditText) findViewById(R.id.amountEditText);
                int fixedAmount = Integer.parseInt(fixedCostAmount.getText().toString());

                Spinner fixedPeriodSpinner = (Spinner)  findViewById(R.id.periodSpinner);
                Spinner categorySpinner = (Spinner) findViewById(R.id.categorySpinner);

                Period fixedPeriod = Period.valueOf(fixedPeriodSpinner.getSelectedItem().toString());
                SpendingCategory chosenCategory = SpendingCategory.valueOf(categorySpinner.getSelectedItem().toString());


                FixedPayment instanceFixedPayment = new FixedPayment(fixedPeriod, chosenCategory, fixedAmount);

                if (fixedAmount <= 0)
                {
                    Toast invalidAmount = Toast.makeText(getApplicationContext(), "Must be a positive amount!", Toast.LENGTH_SHORT);
                    invalidAmount.show();
                }
                else
                {
                    fixedPayments.add(instanceFixedPayment);
                    fixedCostAmount.setText("");
                    Toast fixedAdded = Toast.makeText(getApplicationContext(), "Your fixed payment was added", Toast.LENGTH_SHORT);
                    fixedAdded.show();

                }


            }
        });





    }
}
