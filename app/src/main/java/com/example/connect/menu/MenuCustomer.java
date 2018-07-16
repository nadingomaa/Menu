package com.example.connect.menu;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.connect.menu.Database.CheckedItems;
import com.example.connect.menu.Database.DbHelper;
import com.example.connect.menu.Database.DbHelper2;
import com.example.connect.menu.Database.MenuItems;
import com.example.connect.menu.Models.Meal;
import com.example.connect.menu.adapters.CheckedMealsAdapter;
import com.example.connect.menu.adapters.MealAdapter;

import java.util.ArrayList;

public class MenuCustomer extends AppCompatActivity {

    DbHelper dbHelper;
    SQLiteDatabase db;
    final Context context=this;
    private int countItems = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_menu);
        countItems=0;
    //    ArrayList<Meal> meals=new ArrayList<>();
     //   meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
      //  meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
      //  meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
    //    meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
    //    meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
    //    meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));
    //    meals.add(new Meal("checken with rice",15,R.drawable.ic_launcher_background));

        DbHelper2 dbHelper2= new DbHelper2(this);

        SQLiteDatabase db2=dbHelper2.getWritableDatabase();

        final Cursor cursor=db2.query(MenuItems.items.TABLE,null,null,null,null,null,null,null);

        final CheckedMealsAdapter adapter = new CheckedMealsAdapter(this,
                R.layout.list_item, cursor);
        GridView listMeal=findViewById(R.id.list);
        listMeal.setAdapter(adapter);
        listMeal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                countItems +=1;
                TextView count =findViewById(R.id.count);
                count.setVisibility(View.VISIBLE);
                count.setText(countItems +"");
                cursor.moveToPosition(position);
                Meal currentMeal= new Meal(cursor.getString(1),cursor.getInt(2),cursor.getInt(3));

                dbHelper=new DbHelper(context);
                db=dbHelper.getWritableDatabase();

                CheckedItems.addMeal(currentMeal,db);
                Button buy=findViewById(R.id.button);
                buy.setEnabled(true);




            }
        });
    }

    public void buy(View view) {
        Intent intent =new Intent(this,UserItemsCheckedActivity.class);
        startActivity(intent);

    }
}
