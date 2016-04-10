package com.example.android.mytrainingdatabaseapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AddUserComponent extends AppCompatActivity{

    EditText insertName, insertSurname, insertPhone;
    MyDatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_user_view);
    }

    public void addUser(View view) {
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

    public void backFromAddUserComponents(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
