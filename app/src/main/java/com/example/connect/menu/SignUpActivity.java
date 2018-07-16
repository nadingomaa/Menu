package com.example.connect.menu;

import android.content.ContentValues;
import android.content.Intent;
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

public class SignUpActivity extends AppCompatActivity {
    DbHelper3 dbHelper3 ;
    SQLiteDatabase db1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        try{
            dbHelper3 = new DbHelper3(this);
            db1 = dbHelper3.getWritableDatabase();
            Toast.makeText(this,"Database created",Toast.LENGTH_SHORT).show();
        } catch (Exception e)

        {
            Log.i("MainActivity", "" + e);
        }



    }

    public void signUp(View view) {
        EditText name , password ,userType;
        name = findViewById(R.id.nameEditText);
        password = findViewById(R.id.passwordEditText);
        userType = findViewById(R.id.userTypeEditText);

        String[] columns = {LoginInfo.UserInfo.NAME,LoginInfo.UserInfo.PAEEWORD};
        String[] selectionArg ={name.getText().toString(),password.getText().toString()};
        Cursor c = db1.query(LoginInfo.UserInfo.TABLE,columns,"name = ? AND password = ? ",selectionArg,null,null,null,null);
        if( c.getCount()==0){
            ContentValues values = new ContentValues();
            values.put(LoginInfo.UserInfo.NAME,name.getText().toString());
            values.put(LoginInfo.UserInfo.PAEEWORD,password.getText().toString());
            values.put(LoginInfo.UserInfo.TYPE,Integer.parseInt(userType.getText().toString()));
            db1.insert(LoginInfo.UserInfo.TABLE,null,values);

            if (Integer.parseInt((userType.getText().toString())) == 0 ){
                Intent client = new Intent(this,MenuCustomer.class);
                startActivity(client);
            }
            else if(Integer.parseInt((userType.getText().toString())) == 1){
                Intent driver = new Intent(this,DriverActivity.class);
                startActivity(driver);
            }

            else if(Integer.parseInt((userType.getText().toString())) == 2){
                Intent owner = new Intent(this,MenuOwnerActivity.class);
                startActivity(owner);
            }

        }
    }
}
