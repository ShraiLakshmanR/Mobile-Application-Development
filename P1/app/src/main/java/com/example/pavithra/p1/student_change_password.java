package com.example.pavithra.p1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class student_change_password extends Fragment {
    String name1,pass1,str,str1;
    EditText cp,rp;
    Button sub;


    public student_change_password() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.student_change_password, container, false);

        cp=(EditText)v.findViewById(R.id.e81) ;
        str=cp.getText().toString();

        rp=(EditText)v.findViewById(R.id.e91) ;
        str1=rp.getText().toString();
        sub=(Button)v.findViewById(R.id.button33);
        Bundle bundle = this.getArguments();
        name1 = bundle.getString("N1");
        pass1 = bundle.getString("P1");

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database dat = new Database(getContext());
                SQLiteDatabase db = dat.getReadableDatabase();



                String value1 = cp.getText().toString().trim();
                String value2 = rp.getText().toString().trim();
                if (!value1.contentEquals(value2)) {
                    showMessage("Success", "Password did not match Try Again!!");

                } else {
                    Cursor c = db.rawQuery("SELECT * FROM student1_table WHERE NAME='" + name1 + "' and PASSWORD='" + pass1 + "'", null);
                    if (c.moveToFirst()) {
                        db.execSQL("UPDATE student1_table SET password='" + rp.getText() + "' WHERE NAME='"+name1+"'" );
                        showMessage("Success", "Record Modified");
                    }
                }



            }

        });

      return v;


    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setCancelable( true );
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
        cp.requestFocus();

    }


}
