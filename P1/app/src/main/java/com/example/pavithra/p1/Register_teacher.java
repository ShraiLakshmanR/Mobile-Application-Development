package com.example.pavithra.p1;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register_teacher extends AppCompatActivity implements View.OnClickListener {
    EditText name,email,pw,sub;
    Button register;
    SQLiteDatabase db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_teacher);


        name=(EditText)findViewById(R.id.editText2);
        email=(EditText)findViewById(R.id.editText3);
        pw=(EditText)findViewById(R.id.editText4);
        sub=(EditText)findViewById(R.id.editText5);

        register=(Button)findViewById(R.id.button2);


        register.setOnClickListener(this);

        db=openOrCreateDatabase("department1.db", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS teacher1_table(name VARCHAR,email VARCHAR,password VARCHAR,subject VARCHAR);");
    }
    public void onClick(View view)
    {
        if(view==register)
        {
            if(
                    name.getText().toString().trim().length()==0||
                    email.getText().toString().trim().length()==0||
                    pw.getText().toString().trim().length()==0||
                    sub.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO teacher1_table VALUES('"+name.getText()+"','"+email.getText()+"','"+pw.getText()+"','"+sub.getText()+"');");
            showMessage("Success", "Registeration Sucessfull");
            clearText();
        }

// Displaying all the records

    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder( this );
        builder.setCancelable( true );
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {

        name.setText( "" );
        email.setText( "" );
        pw.setText( "" );
        sub.setText( "" );
        name.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logoutpart, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // Handle action bar item clicks here. The action bar will
         // automatically handle clicks on the Home/Up button, so long
         // as you specify a parent activity in AndroidManifest.xml.
         int id = item.getItemId();
         if (id == R.id.action_logoutpart) {
             Intent i1=new Intent(this,MainActivity.class);
             startActivity(i1);
             }
         return super.onOptionsItemSelected(item);
        }


}
