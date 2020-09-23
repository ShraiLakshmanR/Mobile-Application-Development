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


public class student_view_attendence extends Fragment {
    String name1;
    String pass1;
    ArrayList<String> lines;
    String usn;
    String[] items;
    String data;


    public student_view_attendence() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_student_view_attendence, container, false);


        Bundle bundle = this.getArguments();
        name1 = bundle.getString("N1");
        pass1 = bundle.getString("P1");


        ListView list2 = (ListView)v.findViewById(R.id.list12);


        lines = new ArrayList<String>();
        Database dat = new Database(getContext());
        SQLiteDatabase db = dat.getReadableDatabase();





        Cursor c = db.rawQuery("select * from student1_table where name='"+ name1 +"' and password='"+ pass1 +"';", null);

        if(c.getCount()!=0){
            c.moveToFirst();
            do{
                data=c.getString(6);
                String[] temp = data.split(" ");

                int total = Integer.parseInt(temp[1]);
                int attended = Integer.parseInt(temp[2]);
                int percent = Integer.parseInt(temp[3]);

                lines.add("\nName:\t"+c.getString(0)+"\n\n"+"Total Class:\t"+total+"\n\n"+"Attended:\t"+attended+"\n\n"+"Percent:\t"+percent+"%"+"\n");

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

      //  ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, items);


        list2.setAdapter(aa);









        return v;
    }



}
