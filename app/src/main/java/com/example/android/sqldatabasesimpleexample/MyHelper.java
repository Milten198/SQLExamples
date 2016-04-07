package com.example.android.sqldatabasesimpleexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "exampledatabase";
    private static String TABLE_NAME = "EXAMPLETABLENAME";
    private static int DATABASE_VERSION = 2;
    private static String UID = "_id";
    private static String NAME = "Name";
    private static String ADDRESS = "Address";
    private static String PHONE = "Phone";
    private static String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " VARCHAR(255), " + ADDRESS + " VARCHAR(255), " + PHONE + " VARCHAR(255));";

    private Context context;

    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        Message.message(context, "constructor called");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
            Message.message(context, "onCreateCalled");
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Message.message(context, "onUpgrageCalled");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (SQLException e) {
            Message.message(context, "" + e);
        }
    }
}
