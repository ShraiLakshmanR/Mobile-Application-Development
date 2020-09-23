package com.example.pavithra.p1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Shubha on 4/1/2018.
 */

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "department1.db";
    public static final String TABLE_NAME = "student1_table";
    public static final String TABLE_NAME1= "parent1_table";

    public Database(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }





    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table IF NOT EXISTS " + TABLE_NAME + "" + "( NAME TEXT,EMAIL TEXT,PASSWORD TEXT,PHONE TEXT,PARENT TEXT,USN TEXT,Attendance1 TEXT DEFAULT \'0 0 0\')");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME1 + "" + "(usn VARCHAR,name VARCHAR,email VARCHAR,password VARCHAR,phno VARCHAR);");
        Log.d("BMSCE","Table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);

        onCreate(db);

    }
}
