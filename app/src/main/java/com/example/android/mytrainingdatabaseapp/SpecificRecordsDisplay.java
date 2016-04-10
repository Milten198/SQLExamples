package com.example.android.mytrainingdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class SpecificRecordsDisplay extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    EditText valueEditText, specificRecordsDisplayEditText;
    TextView valueTextView, findByTextView;
    Spinner spinner;
    String[] spinnerValues = {"id", "Name", "Surname", "Phone"};
    MyDatabaseAdapter databaseAdapter;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_records_view);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    public void backFromSpecificRecords(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void search(View view) {
        databaseAdapter = new MyDatabaseAdapter(this);
        specificRecordsDisplayEditText = (EditText) findViewById(R.id.specificRecordsDisplay);
        valueEditText = (EditText) findViewById(R.id.valueEditText);
        String value = valueEditText.getText().toString();
        String record;
        if (position == 0) {
            record = databaseAdapter.findById(value);
            specificRecordsDisplayEditText.setText(record);
        } else if (position == 1) {
            record = databaseAdapter.findByName(value);
            specificRecordsDisplayEditText.setText(record);
        } else if (position ==2) {
            record = databaseAdapter.findBySurname(value);
            specificRecordsDisplayEditText.setText(record);
        } else if (position == 3) {
            record = databaseAdapter.findByPhoneNumber(value);
            specificRecordsDisplayEditText.setText(record);
        } else {
            ToastMessage.message(this, "Something went wrong");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.position = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        ToastMessage.message(this, "Something went wrong");
    }
}
