package com.example.pavithra.p1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class view_student1 extends Fragment {
    ArrayList<String> lines;

    String[] items;

    public view_student1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_view_student1, container, false);


        ListView list2 = (ListView) v.findViewById(R.id.list1);

        lines = new ArrayList<String>();
        Database dat = new Database(getContext());
        SQLiteDatabase db = dat.getReadableDatabase();

        Cursor c = db.rawQuery("select * from student1_table", null);

        if(c.getCount()!=0){
            c.moveToFirst();
            do{

                lines.add("USN:\t\t"+c.getString(5)+"\n"+"Name:\t\t"+c.getString(0)+"\n"+"Email:\t\t"+c.getString(1)+"\n"+"Password:\t\t"+c.getString(2)+"\n"+"Phone:\t\t"+c.getString(3)+"\n"+"Parent:\t\t"+c.getString(4)+"\n");

            }while(c.moveToNext());

        }

        db.close();
        items = lines.toArray(new String[lines.size()]);
        ArrayAdapter<String> aa = new ArrayAdapter<String>
                (getActivity(), android.R.layout.simple_list_item_1, items){
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
       // ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);


        list2.setAdapter(aa);





        return v;
    }


}
