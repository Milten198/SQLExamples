package com.example.android.mytrainingdatabaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class SpecificRecordsDisplay extends AppCompatActivity{

    EditText valueEditText;
    TextView valueTextView, findByTextView;
    Spinner spinner;
    String[] spinnerValues = {"id", "Name", "Surname", "Phone"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.specific_records_view);
        valueEditText = (EditText) findViewById(R.id.valueEditText);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerValues);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ToastMessage.message(getApplicationContext(), "You have chosen " + position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                valueEditText.setText("Choose searching parameter and type value");
            }
        });
    }

    public void findById() {

    }

    public void findByName() {

    }

    public void findBySurname() {

    }

    public void findByPhoneNumber() {

    }

    public void backFromSpecificRecords(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
