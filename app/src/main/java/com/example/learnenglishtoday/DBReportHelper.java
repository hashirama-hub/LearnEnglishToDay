package com.example.learnenglishtoday;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class DBReportHelper extends SQLiteOpenHelper {
    public static String DB = "report.db";
    public DBReportHelper(Context context) {
        super(context, DB, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create table reports(username TEXT, level TEXT, score REAL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb , int i, int i1) {
        myDb.execSQL("drop table if exists reports");
    }

    public void addTestResult(Context context, String username, String level, float score){
        SQLiteDatabase myDb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("level", level);
        contentValues.put("score", score);
        long result = myDb.insert("reports", null, contentValues);
        if (result == -1) Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        else  Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
    }
    @SuppressLint("Range")
    public ArrayList<String> getReports(String username) {
        SQLiteDatabase myDb = this.getReadableDatabase();
        Cursor cursor = myDb.rawQuery("select * from reports where username = ?", new String[]{username});
        cursor.moveToFirst();
        ArrayList<String> ans = new ArrayList<>();
        while(!cursor.isAfterLast()){
            @SuppressLint("Range") String cur = "Level: " + cursor.getString(cursor.getColumnIndex("level")) + ", Score: ";
            cur += cursor.getString(cursor.getColumnIndex("score")) + '\n';
            ans.add(cur);
            cursor.moveToNext();
        }
        return ans;
    }
}