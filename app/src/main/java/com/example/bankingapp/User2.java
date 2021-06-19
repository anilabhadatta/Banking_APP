package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;
import java.util.ArrayList;

public class User2 extends AppCompatActivity {
    DBconn db = new DBconn(this);
    String item2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);
        ListView L2;
        L2 = findViewById(R.id.List2);
        Cursor cur;
        Intent intent = getIntent();
        String item1 = intent.getStringExtra("user1");
        Integer prevUser = Integer.parseInt(item1.split(" ")[0]);
        cur = db.view_user_data(prevUser);
        ArrayList<String> arr = new ArrayList<>();
        while (cur.moveToNext()) {
            String s;
            s = String.valueOf(cur.getInt(0));
            s = s + " " + String.valueOf(cur.getString(1));
            s = s + " " + String.valueOf(cur.getFloat(2));
            arr.add(s);
        }
        ArrayAdapter<String> arr_adap = new ArrayAdapter<>(this, R.layout.activity_textbox, arr);
        L2.setAdapter(arr_adap);
        L2.setOnItemClickListener((arg0, arg1, position, arg3) -> {
            item2 = (String) L2.getItemAtPosition(position);
            send_money(item1);
        });
    }


    void send_money(String item1) {
        Intent i = new Intent(User2.this, SendMoney.class);
        i.putExtra("user1", item1);
        i.putExtra("user2", item2);
        startActivity(i);
    }
}