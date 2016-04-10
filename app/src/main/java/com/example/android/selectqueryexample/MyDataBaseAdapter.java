package com.example.android.selectqueryexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
        contentValues.put(MySQLHelper.NAME, name);
        contentValues.put(MySQLHelper.PASSWORD, password);
        long id = db.insert(MySQLHelper.TABLE_NAME, null, contentValues);
        return id;
    }

    public String getAllData() {
        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        String[] columns = {MySQLHelper.UID, MySQLHelper.NAME, MySQLHelper.PASSWORD};
        Cursor cursor = db.query(MySQLHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLHelper.UID);
            int index2 = cursor.getColumnIndex(MySQLHelper.NAME);
            int index3 = cursor.getColumnIndex(MySQLHelper.PASSWORD);
            int cid = cursor.getInt(index1);
            String name = cursor.getString(index2);
            String password = cursor.getString(index3);
            buffer.append(cid + " " + name + " " + password + "\n");
        }
        return buffer.toString();
    }

    public String getData(String name, String password) {
        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        String[] columns = {MySQLHelper.UID};
        String[] selectionArgs = {name, password};

        Cursor cursor = db.query(MySQLHelper.TABLE_NAME, columns, MySQLHelper.NAME + "=? AND " + MySQLHelper.PASSWORD + "=? ", selectionArgs, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MySQLHelper.UID);
            int personID = cursor.getInt(index1);
            buffer.append(personID + "\n");
        }
        return buffer.toString();
    }

    public int updateName(String oldName, String newName) {
        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLHelper.NAME, newName);
        String[] whereArgs = {oldName};
        int count = db.update(MySQLHelper.TABLE_NAME, contentValues, MySQLHelper.NAME + " =? ", whereArgs);
        return count;
    }

    public int deleteRow(String nameToDelete) {
        SQLiteDatabase db = mySQLHelper.getWritableDatabase();
        String[] whereArgs= {nameToDelete};
        int count = db.delete(MySQLHelper.TABLE_NAME, MySQLHelper.NAME + " =? ", whereArgs);
        return count;
    }

    class MySQLHelper extends SQLiteOpenHelper {

        private static final String DATABASE_NAME = "mydatabase";
        private static final int DATABASE_VERSION = 1;
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
