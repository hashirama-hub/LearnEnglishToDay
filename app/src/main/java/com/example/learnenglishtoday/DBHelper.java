package com.example.learnenglishtoday;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHelper extends SQLiteOpenHelper {
    public static String DB = "login.db";

    public DBHelper(Context context) {
        super(context, DB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create table users(username TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb, int i, int i1) {
        myDb.execSQL("drop table if exists users");
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        long result = myDb.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean checkUserName(String username) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where username = ?", new String[]{username});

        if (cursor.getCount() > 0) return true;
        return false;
    }

    public Boolean checkUserNamePassWord(String username, String password) {
        SQLiteDatabase myDb = this.getWritableDatabase();
        Cursor cursor = myDb.rawQuery("select * from users where username = ? and password = ?", new String[]{username, password});
        Log.e("Check", cursor.toString());
        if (cursor.getCount() > 0) return true;
        else return false;
    }

    public void deleteAccount(Context context, String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        long cur = db.delete("users", "username" + "=?", new String[]{username});
        if (cur == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }
}