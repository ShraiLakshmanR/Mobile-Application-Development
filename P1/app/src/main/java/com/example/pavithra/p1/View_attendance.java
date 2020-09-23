package com.example.pavithra.p1;

import android.app.Activity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class View_attendance extends Activity {
    ArrayList<String> lines;

    String[] items;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_attendance);
        ListView list2 = (ListView) findViewById(R.id.list20);

        lines = new ArrayList<>();
        Database dat = new Database(getBaseContext());
        SQLiteDatabase db = dat.getReadableDatabase();

        Cursor c = db.rawQuery("select * from student1_table", null);

        if(c.getCount()!=0){
            c.moveToFirst();
            do{
                data=c.getString(6);
                String[] temp = data.split(" ");

                int total = Integer.parseInt(temp[1]);
                int attended = Integer.parseInt(temp[2]);
                int percent = Integer.parseInt(temp[3]);

                lines.add("\nName:\t"+c.getString(0)+"\n\n"+"Total Class:\t"+total+"\n\n"+"Attended:\t"+attended+"\n\n"+"Percent:\t"+percent+"%"+"\n");
                c.close();
            }while(c.moveToNext());




        }

        db.close();
        items = lines.toArray(new String[lines.size()]);
        ArrayAdapter<String> aa = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, items){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.WHITE);


                // Generate ListView Item using TextView
                return view;
            }
        };

       // ArrayAdapter<String> aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, items);


        list2.setAdapter(aa);


    }
}
