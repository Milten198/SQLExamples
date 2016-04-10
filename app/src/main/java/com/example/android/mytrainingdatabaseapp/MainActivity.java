package com.example.android.mytrainingdatabaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText insertName, insertSurname, insertPhone;
    MyDatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void insertPerson(View view) {
        insertName = (EditText) findViewById(R.id.nameText);
        insertSurname = (EditText) findViewById(R.id.surnameText);
        insertPhone = (EditText) findViewById(R.id.phoneText);
        String name = insertName.getText().toString();
        String surname = insertSurname.getText().toString();
        String phoneNumber = insertPhone.getText().toString();
        databaseAdapter = new MyDatabaseAdapter(this);
        long id = databaseAdapter.insertOneRecord(name, surname, phoneNumber);

        if (id > 0) {
            ToastMessage.message(this, "Success " + id);
        } else
        {
            ToastMessage.message(this, "Failed " + id);
        }
    }

    public void showAll(View view) {
        databaseAdapter = new MyDatabaseAdapter(this);
        String allRecords = databaseAdapter.showAllRecords();
        ToastMessage.message(this, allRecords);
    }
}
