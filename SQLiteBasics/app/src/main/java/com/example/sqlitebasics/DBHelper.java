package com.example.sqlitebasics;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "tunes.db";
    final static String TABLE_NAME = "tunes";
    final static int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+ TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, artist TEXT, year INT, duration REAL);");
        db.execSQL("INSERT INTO " + TABLE_NAME + " VALUES (1, \"Hello\", \"Adele\", 2015, 6.07);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        updateDB(db, oldVersion, newVersion);
    }

//    private void updateDB(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (oldVersion < 1) {
//
//        }
//        if (oldVersion < 2) {
//            db.execSQL("ALTER TABLE tunes ADD COLUMN duration TEXT;");
//        }
//    }
}
