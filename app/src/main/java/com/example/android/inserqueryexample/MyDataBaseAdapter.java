package com.example.android.inserqueryexample;


import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDataBaseAdapter {

    MySQLHelper mySQLHelper;

    public MyDataBaseAdapter(Context context) {
        mySQLHelper = new MySQLHelper(context);
    }

    public long insertData(String name, String password) {

        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(mySQLHelper.NAME, name);
        contentValues.put(mySQLHelper.PASSWORD, password);
        long id = db.insert(MySQLHelper.TABLE_NAME, null, contentValues);
        return id;
    }



    class MySQLHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "mydatabase";
        private static final int DATABASE_VERSION =1;
        private static final String TABLE_NAME = "MYTABLE";
        private static final String NAME = "NAME";
        private static final String PASSWORD = "PASSWORD";
        private static final String UID = "_id";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255), " + PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS" + TABLE_NAME;

        private Context context;

        public MySQLHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Message.message(context, "constructor called");
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                Message.message(context, "onCreate called");
            } catch (SQLException e) {

            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Message.message(context, "onUpgrade called");
            } catch (SQLException e) {
                Log.v("DUPA", "Nope2");
            }
        }
    }
}
