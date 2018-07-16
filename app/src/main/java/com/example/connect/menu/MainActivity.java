package com.example.connect.menu;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.connect.menu.Database.DbHelper3;
import com.example.connect.menu.Database.LoginInfo;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    DbHelper3 dbHelper3 ;
    SQLiteDatabase db1;
    EditText name , password ;


    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String PASSWORD = "passKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences(mypreference, Context.MODE_PRIVATE);

        try{
            dbHelper3 = new DbHelper3(this);
            db1 = dbHelper3.getWritableDatabase();
           // Toast.makeText(this,"Database created",Toast.LENGTH_SHORT).show();
           } catch (Exception e)

           {
            Log.i("MainActivity", "" + e);
           }


        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);




        if (sharedPreferences.contains(Name)) {
            name.setText(sharedPreferences.getString(Name, ""));
        }
        if (sharedPreferences.contains(PASSWORD)) {
            password.setText(sharedPreferences.getString(PASSWORD, ""));

        }





    }
    public void signIn(View view) {

        String[] columns = {LoginInfo.UserInfo.NAME, LoginInfo.UserInfo.PAEEWORD};
        String[] selectionArg = {name.getText().toString(), password.getText().toString()};
        Cursor confirmUserNameAndPassword = db1.query(LoginInfo.UserInfo.TABLE, columns, "name = ? AND password = ? ", selectionArg, null, null, null, null);
        if (confirmUserNameAndPassword.getCount() == 0) {
            Toast.makeText(this, "Incorrect user name or password", Toast.LENGTH_LONG).show();
        } else {
            String[] userType = {LoginInfo.UserInfo.TYPE};
            Cursor getUserType = db1.query(LoginInfo.UserInfo.TABLE, userType, "name = ? AND password = ? ", selectionArg, null, null, null, null);
            if (getUserType.moveToFirst()) {

                if (getUserType.getInt(0) == 0) {
                    Intent client = new Intent(this, MenuCustomer.class);
                    startActivity(client);
                } else if (getUserType.getInt(0) == 1) {
                    Intent driver = new Intent(this, DriverActivity.class);
                    startActivity(driver);
                } else if (getUserType.getInt(0) == 2) {
                    Intent owner = new Intent(this, MenuOwnerActivity.class);
                    startActivity(owner);
                }

            }
        }



        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(Name,name.getText().toString());
        editor.putString(PASSWORD,password.getText().toString());
        editor.commit();

    }


    public void signUp(View view) {
        Intent intentSignUp=new Intent(this,SignUpActivity.class);
        startActivity(intentSignUp);
    }
}
