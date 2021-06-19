package com.example.bankingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBconn db = new DBconn(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Cursor cur = db.view_user_data(-1);
        if (cur.getCount() == 0)
            createdb();

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(v -> {
            Log.d("Clicks", "You clicked Register");
            Intent i = new Intent(MainActivity.this, Transactions.class);
            startActivity(i);
        });
        Button b2 = findViewById(R.id.b2);
        b2.setOnClickListener(v -> {
            Log.d("Clicks", "You clicked Register");
            Intent i = new Intent(MainActivity.this, CustomerList.class);
            startActivity(i);
        });
    }


    void createdb() {
        db = new DBconn(this);
        db.insert_user("John", (float) (1300));
        db.insert_user("Loki", (float) (2000));
        db.insert_user("Tony", (float) (3500));
        db.insert_user("Thor", (float) (4000));
        db.insert_user("Steve", (float) (5000));
        db.insert_user("Bruce", (float) (6400));
        db.insert_user("Paul", (float) (7000));
        db.insert_user("Wanda", (float) (8500));
        db.insert_user("Sam", (float) (9000));
        db.insert_user("Bucky", (float) (12000));
    }


    @Override
    public void onBackPressed(){
    }
}