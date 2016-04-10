package com.example.android.mytrainingdatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseAdapter {

    MyHelper myHelper;

    public MyDatabaseAdapter(Context context) {
        myHelper = new MyHelper(context);
    }

    public long insertOneRecord(String name, String surname, String phone) {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyHelper.NAME, name);
        contentValues.put(MyHelper.SURNAME, surname);
        contentValues.put(MyHelper.PHONE, phone);
        long count = db.insert(MyHelper.TABLE_NAME, null, contentValues);
        return count;
    }

    public String showAllRecords() {
        SQLiteDatabase db = myHelper.getWritableDatabase();
        String[] columns = {MyHelper.UID, MyHelper.NAME, MyHelper.SURNAME, MyHelper.PHONE};
        Cursor cursor = db.query(MyHelper.TABLE_NAME, columns, null, null, null, null, null);
        StringBuffer buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int id = cursor.getColumnIndex(MyHelper.UID);
            int nameId = cursor.getColumnIndex(MyHelper.NAME);
            int surnameId = cursor.getColumnIndex(MyHelper.SURNAME);
            int phoneId = cursor.getColumnIndex(MyHelper.PHONE);
            int recordId = cursor.getInt(id);
            String name = cursor.getString(nameId);
            String surname = cursor.getString(surnameId);
            String phone = cursor.getString(phoneId);
            buffer.append(recordId + " " + name + " " + surname + " " + phone + "\n");
        }
        return buffer.toString();
    }


    class MyHelper extends SQLiteOpenHelper {

        Context context;
        private static final String DATABASE_NAME = "katalog";
        private static final String TABLE_NAME = "PERSONDATA";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "NAME";
        private static final String SURNAME = "SURNAME";
        private static final String PHONE = "PHONE";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255), " + SURNAME + " VARCHAR(255), " + PHONE + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        public MyHelper(Context context) {
            super(context, TABLE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                ToastMessage.message(context, "onCreate success");
            } catch (SQLException e) {
                ToastMessage.message(context, "Failed to create database " + db.getVersion());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
                ToastMessage.message(context, "onUpgrade success");
            } catch (SQLException e) {
                ToastMessage.message(context, "Failed to drop table " + TABLE_NAME + "in version " + db.getVersion());
            }
        }
    }
}
