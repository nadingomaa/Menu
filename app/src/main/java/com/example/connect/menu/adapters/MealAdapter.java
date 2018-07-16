package com.example.connect.menu.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect.menu.Models.Meal;
import com.example.connect.menu.R;

import java.util.ArrayList;

public class MealAdapter extends CursorAdapter{
    Context context;
    public MealAdapter(Context context, int RId, Cursor cursor) {
        super(context, cursor);
        this.context = context;


    }



    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
       View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

      ;

        TextView title = view.findViewById(R.id.mealTitle);
        title.setText(cursor.getString(1));

        TextView price = view.findViewById(R.id.mealPrice);
        price.setText(cursor.getInt(2)+"$");

        ImageView imageView = view.findViewById(R.id.imageView);

        imageView.setImageResource((cursor.getInt(3)));






    }
}
