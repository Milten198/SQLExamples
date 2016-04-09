package com.example.android.selectqueryexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText nameText;
    EditText passwordText;
    MyDataBaseAdapter myDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void insertData(View view) {

        nameText = (EditText) findViewById(R.id.editText);
        passwordText = (EditText) findViewById(R.id.editText2);

        String name = nameText.getText().toString();
        String password = passwordText.getText().toString();

        myDataBaseAdapter = new MyDataBaseAdapter(this);
        long id = myDataBaseAdapter.insertData(name, password);

        if (id>0) {
            Message.message(this, "It works!" + id);
        }
        else {
            Message.message(this, "Not this time, bro!" + id);

        }
    }

    public void displayData(View view) {
        myDataBaseAdapter = new MyDataBaseAdapter(this);
        String data = myDataBaseAdapter.getAllData();
        Message.message(this, data);
    }
}
