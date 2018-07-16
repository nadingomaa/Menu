package com.example.connect.menu.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import static android.content.ContentValues.TAG;

public class DbHelper3 extends SQLiteOpenHelper {
    public static final int VERSION = 2;
    public static final String DATABASE ="user.db";
    public DbHelper3(Context context) {
        super(context,DATABASE,null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(LoginInfo.UserInfo.CREATE);
        try {
            db.execSQL(LoginInfo.UserInfo.DUMMY);
            Log.i(TAG, "ENTERED: done");
        } catch (Exception e) {
            Log.i(TAG, "ENTERED:" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(LoginInfo.UserInfo.DROP_TABLE);
        onCreate(db);
    }


}
