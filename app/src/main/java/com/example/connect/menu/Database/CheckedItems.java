package com.example.connect.menu.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import com.example.connect.menu.Models.Meal;

import static android.content.ContentValues.TAG;

public class CheckedItems {

   public static class Meals implements BaseColumns{

        public static final String TABLE = "checkedItems";
        public static final String TITLE = "title";
        public static final String PRICE= "price";
        public static final String IMAGE_RECOURCE_ID = "imageRecourceId";

        public static final String CREATE="CREATE TABLE " +
                TABLE + "( " + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                             TITLE +" TEXT NOT NULL," +
                             PRICE +" DOUBLE NOT NULL," +
                IMAGE_RECOURCE_ID  +" DOUBLE NOT NULL );" ;

        public static final String Drop_TABLE = "DROP TABLE IF EXISTS " + TABLE;

    }


    public  static  void  addMeal(Meal meal, SQLiteDatabase db) {
        ContentValues values = new ContentValues();
        values.put(Meals.TITLE, meal.getTitle());
        values.put(Meals.PRICE, meal.getPrice());
        values.put(Meals.IMAGE_RECOURCE_ID,meal.getImageResourceId());



        long rowId = db.insert(Meals.TABLE, null, values);
        Log.i(TAG, "addJob: " + rowId);


    }

}
