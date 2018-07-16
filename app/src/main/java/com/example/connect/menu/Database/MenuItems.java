package com.example.connect.menu.Database;

import android.provider.BaseColumns;

import com.example.connect.menu.R;

public class MenuItems {


    public static class items implements BaseColumns{
        public static final String TABLE = "Items";
        public static final String TITLE = "title";
        public static final String PRICE= "price";
        public static final String IMAGE_RECOURCE_ID = "imageRecourceId";

        public static final String CREATE="CREATE TABLE " +
                TABLE + "( " + _ID +" INTEGER PRIMARY KEY AUTOINCREMENT," +
                TITLE +" TEXT NOT NULL," +
                PRICE +" DOUBLE NOT NULL," +
                IMAGE_RECOURCE_ID  +" DOUBLE NOT NULL );" ;

        public static final String Drop_TABLE = "DROP TABLE IF EXISTS " + TABLE;

        public static final String DUMMY = " INSERT INTO " + TABLE + " (" + TITLE + "," + PRICE + "," + IMAGE_RECOURCE_ID + ")  VALUES " +
                "( 'checken with rice',15,"+R.drawable.checken+"), " +
                "( 'meat with rice',20,"+R.drawable.salad+"), "+
                "( 'pasta',26,"+R.drawable.pasta+"), "+
                "( 'rice',5,"+R.drawable.rice +"), "+
                "( 'shrimp with rice',27,"+R.drawable.shrimp+"), "+
                "( 'rice with small piece of meat',25,"+R.drawable.chienes+"), "+
                "( 'fish',30,"+R.drawable.fish+")";

    }




}
