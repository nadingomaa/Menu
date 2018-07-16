package com.example.connect.menu.adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.connect.menu.R;

public class CheckedMealsAdapter extends CursorAdapter{

    Context context;

    public CheckedMealsAdapter(Context context, int RId, Cursor cursor) {

        super(context, cursor);
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        TextView title =view.findViewById(R.id.mealTitle);
        title.setText(cursor.getString(1));

        TextView price = view.findViewById(R.id.mealPrice);
        price.setText(cursor.getInt(2)+"$");

        ImageView imageView=view.findViewById(R.id.imageView);
        imageView.setImageResource(cursor.getInt(3));
    }
}
