package com.example.bankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBconn extends SQLiteOpenHelper {


    public DBconn(Context context) {
        super(context, "USERDATABASE.db",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table user_data(UId Integer primary key autoincrement, Uname Text,  Balance Real)");
        db.execSQL("create table transaction_data(TId Integer primary key autoincrement, Uname1 Text,  Uname2 Text, TAmount Real)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user_data");
        db.execSQL("drop table if exists transaction_data");
    }


    public Boolean insert_user(String Uname, Float Balance) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Uname", Uname);
        data.put("Balance", Balance);
        if (db.insert("user_data", null, data) == -1)
            return false;
        return true;
    }


    public Boolean insert_transaction(String Uname1, String Uname2, Float TAmount) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Uname1", Uname1);
        data.put("Uname2", Uname2);
        data.put("TAmount", TAmount);
        if (db.insert("transaction_data", null, data) == -1)
            return false;
        return true;
    }


    public Boolean update(Float Balance, Integer UId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues data = new ContentValues();
        data.put("Balance", Balance);
        Cursor cur;
        cur = db.rawQuery("select * from user_data where UId = ?", new String[]{UId.toString()});
        if (cur.getCount()>0){
            if (db.update("user_data", data, "UId = ?", new String[]{UId.toString()}) == -1)
                return false;
            return true;
        }
        return false;
    }


    public Cursor view_user_data(Integer UId){
        SQLiteDatabase db = this.getWritableDatabase();
        if (UId == -1){
            return db.rawQuery("select * from user_data",null);
        }
        else {
            return db.rawQuery("select * from user_data where UId <> ?", new String[]{UId.toString()});
        }
    }


    public Cursor view_transaction_data() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("select * from transaction_data", null);
    }
}
