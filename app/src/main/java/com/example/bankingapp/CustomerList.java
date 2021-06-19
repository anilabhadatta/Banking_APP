package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {
    DBconn db = new DBconn(this);
    String item1;
    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customerlist);
        ListView L1;
        L1 = findViewById(R.id.List1);
        Cursor cur;
        cur = db.view_user_data(-1);
        ArrayList<String> arr = new ArrayList<>();
        while (cur.moveToNext()) {
            String s;
            s = String.valueOf(cur.getInt(0));
            s = s + " " + String.valueOf(cur.getString(1));
            s = s + " " + String.valueOf(cur.getFloat(2));
            arr.add(s);
        }
        ArrayAdapter<String> arr_adap = new ArrayAdapter<>(this, R.layout.activity_textbox, arr);
        L1.setAdapter(arr_adap);
        L1.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            item1 = (String) L1.getItemAtPosition(position);
            user2();
        });
    }


    void user2() {
        Intent i = new Intent(CustomerList.this, User2.class);
        i.putExtra("user1", item1);
        startActivity(i);
    }
}
