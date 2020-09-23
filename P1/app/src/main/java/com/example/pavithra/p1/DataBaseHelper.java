package com.example.pavithra.p1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pavithra on 3/1/2018.
 */

public class DataBaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "department1.db";
    public static final String TABLE_NAME = "student1_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "EMAIL";
    public static final String COL_3 = "PASSWORD";

    public static final String COL_4 = "PHONE";
    public static final String COL_5 = "PARENT";
    public static final String COL_6= "USN";
    SQLiteDatabase db;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "" + "( NAME TEXT,EMAIL TEXT,PASSWORD TEXT,PHONE TEXT,PARENT TEXT,USN TEXT,Attendance1 TEXT DEFAULT \'0 0 0\')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void insert_record(String name, String email,String pass,String phone,String parent,String usn) {

        String query = "INSERT INTO student1_table(NAME,EMAIL,PASSWORD,PHONE,PARENT,USN) VALUES ('" + name + "','" + email + "','" + pass + "',"  + phone + "," + parent + ",'" + usn + "');";
        db.execSQL(query);
    }


    public void update_record(String name, String email) {
        String query = "UPDATE student1_table SET Email='" + email + "' WHERE Name='" + name + "';";
        db.execSQL(query);
    }



    public void delete_record(String name) {
        String query = "DELETE FROM student1_table WHERE Name='" + name + "';";
        db.execSQL(query);
    }




}
