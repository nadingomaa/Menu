package com.example.connect.menu;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.connect.menu.Database.CheckedItems;
import com.example.connect.menu.Database.DbHelper;
import com.example.connect.menu.Models.Meal;
import com.example.connect.menu.adapters.CheckedMealsAdapter;
import com.example.connect.menu.adapters.MealAdapter;

import java.util.ArrayList;

public class UserItemsCheckedActivity extends AppCompatActivity {
    final Context context = this;
    SQLiteDatabase db;
    DbHelper dbHelper;
    TextView totalPriceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_items_checked);

        dbHelper = new DbHelper(this);

        db = dbHelper.getWritableDatabase();


        final Cursor cursor = db.query(CheckedItems.Meals.TABLE, null, null, null, null, null, null, null);

        final CheckedMealsAdapter adapter = new CheckedMealsAdapter(this,
                R.layout.list_item, cursor);

        ListView listMeal = findViewById(R.id.CheckedItemsList);

        listMeal.setAdapter(adapter);


        Cursor cursor1 = db.rawQuery("SELECT SUM(" + CheckedItems.Meals.PRICE + ") FROM " + CheckedItems.Meals.TABLE, null);
        int totalPrice = 0;
        if (cursor1.moveToFirst()) {
            totalPrice = cursor1.getInt(0);
        }

        totalPriceView = findViewById(R.id.totalPrice);
        totalPriceView.setText(totalPrice + "$");


        listMeal.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, final long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("confirm").setMessage("Are you sure you want to delete the employee")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        }).setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        db.delete(CheckedItems.Meals.TABLE, "_ID = " + id, null);

                        Cursor c = db.query(CheckedItems.Meals.TABLE, null, null, null, null, null, null, null);
                        adapter.changeCursor(c);
                        totalPriceView.setText("00.0$");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


                return true;
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        db.delete(CheckedItems.Meals.TABLE, null, null);
        totalPriceView.setText("00.0$");
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.delete(CheckedItems.Meals.TABLE, null, null);
        totalPriceView.setText("00.0$");
    }
}
