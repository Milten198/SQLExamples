package com.example.android.mytrainingdatabaseapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MyDatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertPerson(View view) {
        Intent intent = new Intent(this, AddUserComponent.class);
        startActivity(intent);
    }

    public void showAll(View view) {
        Intent intent = new Intent(this, AllRecordsDisplay.class);
        startActivity(intent);
    }
}
