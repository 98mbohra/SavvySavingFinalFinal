package com.example.bohra.savvysavingappworking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SetIncomeActivity extends AppCompatActivity {

    //in this class initialise the financeDB
    //Send the income, and period to the financeDB
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_set_activity);

        Button confirm3 = (Button) findViewById(R.id.confirmIncomeButton);
        confirm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText incomeEdit = (EditText) findViewById(R.id.incomeEditText);

                int income = Integer.parseInt(incomeEdit.getText().toString());

                Spinner incomePeriodSpinner = (Spinner)  findViewById(R.id.incomePeriodSpinner);
                Period incomePeriod = Period.valueOf(incomePeriodSpinner.getSelectedItem().toString());


                if ((income == 0) || (incomeEdit.getText().equals("")))
                {
                    Toast budgetHighToast = Toast.makeText(getApplicationContext(), "You must enter your income!", Toast.LENGTH_SHORT);
                    budgetHighToast.show();
                }
                else
                {
                    //send income, budget, income period to file

                    IO io = new IO();
                    String str = "income:"+income+"\n"+
                            "incomePeriod:"+incomePeriodSpinner.getSelectedItem().toString()+"\n";
                    io.writeFile("finance.txt",str);

                    Intent fixedCostsSetupIntent = new Intent(getApplicationContext(), FixedCostsSetup.class);
                    startActivity(fixedCostsSetupIntent);
                }


            }
        });

    }
}
