package com.example.bankingapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.net.Inet4Address;
import java.util.ArrayList;

public class SendMoney extends AppCompatActivity {
    DBconn db = new DBconn(this);
    Float ubal1,ubal2;
    String uname1,uname2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmoney);
        Intent intent = getIntent();
        String item1 = intent.getStringExtra("user1");
        String item2 = intent.getStringExtra("user2");

        Integer uid1 = Integer.parseInt(item1.split(" ")[0]);
        uname1 = item1.split(" ")[1];
        ubal1 = Float.parseFloat(item1.split(" ")[2]);
        Integer uid2 = Integer.parseInt(item2.split(" ")[0]);
        uname2 = item2.split(" ")[1];
        ubal2 = Float.parseFloat(item2.split(" ")[2]);


        TextView username1 = findViewById(R.id.username1);
        TextView username2 = findViewById(R.id.username2);
        TextView useramount1 = findViewById(R.id.useramount1);
        TextView useramount2 = findViewById(R.id.useramount2);
        username1.setText(uname1);
        username2.setText(uname2);
        useramount1.setText(ubal1.toString());
        useramount2.setText(ubal2.toString());


        EditText t = findViewById(R.id.amount);
        Button btn1 = findViewById(R.id.btn_sendmoney);
        btn1.setOnClickListener(v -> {
            Log.d("Clicks", "You clicked Register");
            try {
                Float amount = Float.parseFloat(t.getText().toString());
                if (amount <= ubal1) {
                    ubal1 -= amount;
                    ubal2 += amount;
                    db.update(ubal1, uid1);
                    db.update(ubal2, uid2);
                    db.insert_transaction(uname1, uname2, amount);
                    Toast.makeText(getApplicationContext(), "Successful Transaction!", Toast.LENGTH_LONG).show();
                    go_to_main();
                } else
                    Toast.makeText(getApplicationContext(), "Can't afford the transaction,Reduce the amount or cancel the transaction", Toast.LENGTH_LONG).show();
            }
            catch(Exception e) {
                Toast.makeText(getApplicationContext(), "Invalid Amount", Toast.LENGTH_LONG).show();
            }
        });

        Button btn2 = findViewById(R.id.btn_cancel);
        btn2.setOnClickListener(v -> {
            Log.d("Clicks", "You clicked Register");
            Toast.makeText(getApplicationContext(), "Transaction Cancelled!" , Toast.LENGTH_LONG).show();
            go_to_main();
        });
    }


//    Transaction Cancelled
    void go_to_main(){
        Intent i = new Intent(SendMoney.this, MainActivity.class);
        startActivity(i);
    }


    @Override
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(), "Back is disabled during transaction" , Toast.LENGTH_LONG).show();
    }

}