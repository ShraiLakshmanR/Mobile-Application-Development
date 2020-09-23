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
import android.widget.Toast;


public class add_student1 extends Fragment {

    EditText name_txt, eml_txt, password_txt,phone_txt,parent_txt,usn_txt;
    Button insert_btn,update_btn, delete_btn;
    SQLiteDatabase db;

    String USNPattern="1BM1[4-7]CS[0-9]{3}";
    String MobilePattern = "[0-9]{10}";

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";




    public add_student1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_add_student1, container, false);


        name_txt = (EditText)v.findViewById(R.id.editText6);
        eml_txt = (EditText) v.findViewById(R.id.editText);
        password_txt = (EditText) v.findViewById(R.id.editText1);

        phone_txt = (EditText) v.findViewById(R.id.editText3);
        parent_txt = (EditText) v.findViewById(R.id.editText4);
        usn_txt = (EditText) v.findViewById(R.id.editText5);


        insert_btn = (Button) v.findViewById(R.id.button4);


        update_btn = (Button) v.findViewById(R.id.button6);
        delete_btn = (Button) v.findViewById(R.id.button5);
        db = getActivity().openOrCreateDatabase("department1.db",android.content.Context.MODE_PRIVATE ,null);
        //db.execSQL("drop table student1_table");
        db.execSQL("create table IF NOT EXISTS student1_table( NAME TEXT,EMAIL TEXT,PASSWORD TEXT,PHONE TEXT,PARENT TEXT,USN TEXT PRIMARY KEY,Attendance1 TEXT DEFAULT \'0 0 0\')");

        insert_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(name_txt.getText().toString().trim().length()==0||
                        eml_txt.getText().toString().trim().length()==0||
                        password_txt.getText().toString().trim().length()==0||
                        phone_txt.getText().toString().trim().length()==0||
                        usn_txt.getText().toString().trim().length()==0)
                {
                    showMessage("Success", "Please enter all values");



                }
                else

                {
                    Cursor c = db.rawQuery("SELECT * FROM student1_table WHERE usn='" + usn_txt.getText() + "'", null);
                    if (c.moveToFirst()) {
                        String usn_txt1=c.getString(5);

                        showMessage( "Error" , usn_txt1+" USN already exists");
                        clearText();
                        c.close();

                    } else {



                        if(!eml_txt.getText().toString().matches(emailPattern) || !phone_txt.getText().toString().matches(MobilePattern) || !parent_txt.getText().toString().matches(MobilePattern) || !usn_txt.getText().toString().matches(USNPattern))
                        {

                            showMessage( "Error" , "Inavlid Email Pattern or Inavlid Mobile Number or Invalid USN Pattern " );
                            usn_txt.requestFocus();

                        }
                        else {


                            db.execSQL("INSERT INTO student1_table(NAME,EMAIL,PASSWORD,PHONE,PARENT,USN) VALUES('" + name_txt.getText() + "','" + eml_txt.getText() + "','" + password_txt.getText() + "'," + phone_txt.getText() + "," + parent_txt.getText() + ",'" + usn_txt.getText() + "');");
                            Toast.makeText(getActivity(), "Record Inserted", Toast.LENGTH_SHORT).show();

                            clearText();
                        }
                    }


                }


            } });


        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if ( usn_txt.getText().toString().trim().length()== 0 )
                {
                    showMessage( "Error" , "Please enter USN" );
                    return ;
                }
                Cursor c= db .rawQuery( "SELECT * FROM student1_table WHERE usn='" + usn_txt.getText()+ "'" , null );
                if (c.moveToFirst())
                {
                    db .execSQL( "DELETE FROM student1_table WHERE usn='" + usn_txt.getText()+ "'" );
                    Toast.makeText(getActivity(),"Record Deleted", Toast.LENGTH_SHORT).show();
                    c.close();

                }
                else
                {

                    showMessage( "Error" , "Invalid USN" );
                }
                clearText();



            }});

        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ( usn_txt.getText().toString().trim().length()== 0 )
                {

                    showMessage( "Error" , "Please enter usn" );
                    return ;
                }
                Cursor c= db .rawQuery( "SELECT * FROM student1_table WHERE usn='" + usn_txt.getText()+ "'" , null );
                if (c.moveToFirst()) {
                    db .execSQL( "UPDATE student1_table SET name='" + name_txt.getText() + "',email='" + eml_txt.getText()+"',password='" + password_txt.getText()+"',phone='"+phone_txt.getText()+"',parent='"+parent_txt.getText()+"'WHERE usn='" + usn_txt.getText()+ "'");
                    Toast.makeText(getActivity(),"Record Modified", Toast.LENGTH_SHORT).show();
                    c.close();

                }
                else {

                    showMessage( "Error" , "Invalid Rollno" );
                }
                clearText();

            }});


                return v;
    }

    public void clearText()
    {
        usn_txt.setText( "" );
        name_txt.setText( "" );
        eml_txt.setText( "" );
        password_txt.setText( "" );
        phone_txt.setText( "" );
        parent_txt.setText( "" );

        usn_txt.requestFocus();
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        builder.setCancelable( true );
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }






    }