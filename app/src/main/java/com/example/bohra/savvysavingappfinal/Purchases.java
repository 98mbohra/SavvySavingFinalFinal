package com.example.bohra.savvysavingappfinal;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Purchases extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchases);
        IO io = new IO();
        String[] arr = io.readFile("purchase.txt").split("\n");
        String[] arr2 = new String[20];

        if(arr.length<=20)
        {
            arr2 = arr;
        }
        else
        {
            for (int i = 0;i<20;i++)
            {
                arr2[i] = arr[arr.length-20+i];
            }
        }
        for(int i = 0;i<arr2.length;i++)
        {
            String[] arr3 = arr2[i].split(":");
            arr2[i] = (i+1)+".Price: $" + arr3[1] + "\nCategory: " + arr3[2] + "\nPurchase Date: "+arr3[3] +"\nComment: " +arr3[0];
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.activety_listview,arr2);
        ListView listView = (ListView)findViewById(R.id.list_view);
        listView.setAdapter(adapter);
    }
}

