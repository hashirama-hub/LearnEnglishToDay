package com.example.learnenglishtoday;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DBPdfHelper extends SQLiteOpenHelper {
    public static String DB = "pdf.db";
    public DBPdfHelper(Context context) {
        super(context, DB, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase myDb) {
        myDb.execSQL("create table pdfs(groupid integer, childid integer, filename TEXT, PRIMARY KEY(groupid, childid))");
        createPDF(myDb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase myDb , int i, int i1) {
        myDb.execSQL("drop table if exists pdfs");
        createPDF(myDb);
    }
    public void createPDF(SQLiteDatabase myDb){
        List<String[]> map = new ArrayList<>();
        map.add(new String[]{"0", "0", "1.pdf"});
        map.add(new String[]{"1", "0", "2.pdf"});
        map.add(new String[]{"1", "1", "3.pdf"});
        map.add(new String[]{"1", "2", "4.pdf"});
        map.add(new String[]{"1", "3", "5.pdf"});
        map.add(new String[]{"1", "4", "6.pdf"});
        map.add(new String[]{"1", "5", "7.pdf"});
        map.add(new String[]{"1", "6", "8.pdf"});
        map.add(new String[]{"1", "7", "9.pdf"});
        map.add(new String[]{"1", "8", "10.pdf"});
        map.add(new String[]{"1", "9", "11.pdf"});
        map.add(new String[]{"1", "10", "12.pdf"});
        map.add(new String[]{"1", "11", "13.pdf"});
        map.add(new String[]{"2", "0", "ARTICLES.pdf"});
        map.add(new String[]{"2", "1", "ARTICLESG.pdf"});
        map.add(new String[]{"3", "0", "16.pdf"});
        map.add(new String[]{"4", "0", "17.pdf"});
        map.add(new String[]{"4", "1", "18.pdf"});
        map.add(new String[]{"4", "2", "19.pdf"});
        map.add(new String[]{"4", "3", "20.pdf"});
        map.add(new String[]{"4", "4", "21.pdf"});
        map.add(new String[]{"5", "0", "22.pdf"});
        map.add(new String[]{"6", "0", "definingRelativeClauses.pdf"});
        map.add(new String[]{"6", "1", "nonDefiningRelativeClauses.pdf"});
        map.add(new String[]{"7", "0", "25.pdf"});
        map.add(new String[]{"7", "1", "26.pdf"});
        map.add(new String[]{"7", "2", "27.pdf"});
        map.add(new String[]{"8", "0", "28.pdf"});
        map.add(new String[]{"8", "1", "29.pdf"});
        map.add(new String[]{"8", "2", "30.pdf"});
        map.add(new String[]{"9", "0", "idioms.pdf"});
        map.add(new String[]{"10", "0", "usermanual.pdf"});
        for (int i = 0; i < map.size(); i++) {
            String[] key = map.get(i);
            String tmp =  key[2];
            int groupid = Integer.parseInt(key[0]);
            int childid = Integer.parseInt(key[1]);
            ContentValues contentValues = new ContentValues();
            contentValues.put("groupid", groupid);
            contentValues.put("childid", childid);
            contentValues.put("filename", tmp);
            long result = myDb.insert("pdfs", null, contentValues);
            if (result == -1) continue;
            Log.e("hello", contentValues.toString());
        }
    }
    @SuppressLint("Range")
    public String getFileNamePdf(int groupid, int childid){
        String ans = "";
        SQLiteDatabase myDb = this.getWritableDatabase();
        String query = "select * from pdfs where groupid = " + groupid + " and childid = " + childid;
        Log.e("Query", query);
        Cursor cursor = myDb.rawQuery(query, new String[]{});
        Log.e("Check", cursor.toString());
        if (cursor != null){
            Log.e("Filename", "Fail");
            if (cursor.moveToFirst()){
                ans =  cursor.getString(cursor.getColumnIndex("filename"));
            }
        }
        Log.e("FileName", ans);
        return ans;
    }
}