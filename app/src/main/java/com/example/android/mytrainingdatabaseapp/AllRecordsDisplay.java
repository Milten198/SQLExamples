package com.example.android.mytrainingdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AllRecordsDisplay extends AppCompatActivity{

    EditText allRecordsEditText;
    MyDatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_records_view);

        databaseAdapter = new MyDatabaseAdapter(this);
        String allRecords = databaseAdapter.showAllRecords();
        allRecordsEditText = (EditText) findViewById(R.id.allRecordsEditText);
        allRecordsEditText.setText(allRecords);

    }



    public void backFromAllRecords(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
