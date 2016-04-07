package com.example.android.sqldatabasesimpleexample;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyHelper myHelper = new MyHelper(this);
        SQLiteDatabase sqLiteOpenHelper = myHelper.getWritableDatabase();
    }

}
