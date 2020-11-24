package com.sample.apps.is4447.gobusker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table busker(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists busker");
    }

    //inserting in database
    public boolean insert(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.insert("busker", null, contentValues);

        if (ins == -1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean checkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from busker where email=?", new String[]{email});
        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking the email and password for login
    public Boolean emailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from busker where email=? and password=?", new String[]{email, password});
        if(cursor.getCount()>0) return true;
        else return false;
    }
}