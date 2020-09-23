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


public class add_parent1 extends Fragment {
    EditText usn,name,email,pw,ph;
    Button add,delete,update,view1;
    SQLiteDatabase db;
    String USNPattern="1BM1[4-7]CS[0-9]{3}";
    String MobilePattern = "[0-9]{10}";

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";



    public add_parent1() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_add_parent1, container, false);

        usn=(EditText)v.findViewById(R.id.editText2);
        name=(EditText)v.findViewById(R.id.editText);
        email=(EditText)v.findViewById(R.id.editText1);
        pw=(EditText)v.findViewById(R.id.editText4);
        ph=(EditText)v.findViewById(R.id.editText3);

        add=(Button)v.findViewById(R.id.button4);
        delete=(Button)v.findViewById(R.id.revButton);
        update=(Button)v.findViewById(R.id.button6);
        view1=(Button)v.findViewById(R.id.button7);
        db = getActivity().openOrCreateDatabase("department1.db",android.content.Context.MODE_PRIVATE ,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS parent1_table(usn VARCHAR,name VARCHAR,email VARCHAR,password VARCHAR,phno VARCHAR);");

        add.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {


                                       if(usn.getText().toString().trim().length()==0||
                                               name.getText().toString().trim().length()==0||
                                               email.getText().toString().trim().length()==0||
                                               pw.getText().toString().trim().length()==0||
                                               ph.getText().toString().trim().length()==0)
                                       {
                                           showMessage("Success", "Please enter all values");



                                       }
                                       else {

                                           if(!email.getText().toString().matches(emailPattern) || !ph.getText().toString().matches(MobilePattern) ||  !usn.getText().toString().matches(USNPattern))

                                           {
                                               showMessage( "Error" , "Inavlid Email Pattern or Inavlid Mobile Number or Invalid USN Pattern " );
                                               usn.requestFocus();
                                           }

                                           else {
                                               db.execSQL("INSERT INTO parent1_table VALUES('" + usn.getText() + "','" + name.getText() + "','" + email.getText() + "','" + pw.getText() + "','" + ph.getText() + "');");
                                               Toast.makeText(getActivity(), "Record added", Toast.LENGTH_SHORT).show();

                                               clearText();
                                           }
                                       }

                                   }
                               });
        delete.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {


                                       if ( usn.getText().toString().trim().length()== 0 )
                                       {
                                           showMessage( "Error" , "Please enter USN" );
                                           return ;
                                       }
                                       Cursor c= db .rawQuery( "SELECT * FROM parent1_table WHERE usn='" + usn.getText()+ "'" , null );
                                       if (c.moveToFirst())
                                       {
                                           db .execSQL( "DELETE FROM parent1_table WHERE usn='" + usn.getText()+ "'" );
                                           Toast.makeText(getActivity(),"Record Deleted", Toast.LENGTH_SHORT).show();
                                           c.close();

                                       }
                                       else
                                       {

                                           showMessage( "Error" , "Invalid USN" );
                                       }
                                       clearText();
                                   }
                               });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if ( usn.getText().toString().trim().length()== 0 )
                {

                    showMessage( "Error" , "Please enter usn" );
                    return ;
                }
                Cursor c= db .rawQuery( "SELECT * FROM parent1_table WHERE usn='" + usn.getText()+ "'" , null );
                if (c.moveToFirst()) {
                    db .execSQL( "UPDATE parent1_table SET name='" + name.getText() + "',email='" + email.getText()+"',password='" + pw.getText()+"',phno='"+ph.getText()+"'WHERE usn='" + usn.getText()+ "'");
                    Toast.makeText(getActivity(),"Record Modified", Toast.LENGTH_SHORT).show();
                    c.close();

                }
                else {

                    showMessage( "Error" , "Invalid Rollno" );
                }
                clearText();

            }
        });
        view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( usn.getText().toString().trim().length()== 0 )
                {
                    showMessage( "Error" , "Please enter usn" );
                    return ;
                }
                Cursor c= db .rawQuery( "'SELECT * FROM parent1_table WHERE usn='" + usn.getText()+ "'" , null );
                if (c.moveToFirst())
                {
                    usn.setText(c.getString(0));
                    name.setText(c.getString(1));

                    email.setText(c.getString(2));
                    pw.setText(c.getString(3));
                    ph.setText(c.getString(4));
                    c.close();
                }
                else
                {
                    showMessage( "Error" , "Invalid Usn" );
                    clearText();
                }
            }


                                              });


        return v;
    }
    public void clearText()
    {
        usn.setText( "" );
        name.setText( "" );
        email.setText( "" );
        pw.setText( "" );
        ph.setText( "" );
        usn.requestFocus();
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
