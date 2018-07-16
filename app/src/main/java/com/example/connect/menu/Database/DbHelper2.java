package com.example.connect.menu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;


public class DbHelper2 extends SQLiteOpenHelper {
    public static final String DATABASE= "Meals.db";
    public static final int VERSION=1;
    public DbHelper2(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MenuItems.items.CREATE);
      try{  db.execSQL(MenuItems.items.DUMMY);
        Log.i("JJ", "ENTERED: done");}
        catch(Exception e){
            Log.i("JJ", "ENTERED:"+e);
    }}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MenuItems.items.Drop_TABLE);
    }
}
