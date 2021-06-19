package com.example.bankingapp;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Transactions extends AppCompatActivity {
    DBconn db = new DBconn(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        ListView L2;
        L2 = findViewById(R.id.List2);
        Cursor cur;
        cur = db.view_transaction_data();
        ArrayList<String> arr = new ArrayList<>();
        while (cur.moveToNext()){
            String s;
            s = String.valueOf(cur.getInt(0));
            s = s + ".    -" + String.valueOf(cur.getString(1));
            s = s + "    +" + String.valueOf(cur.getString(2));
            s = s + "    Amount: " + String.valueOf(cur.getFloat(3));
            arr.add(s);
        }
        ArrayAdapter<String> arr_adap = new ArrayAdapter<>(this,R.layout.activity_textbox, arr);
        L2.setAdapter(arr_adap);
    }
}
