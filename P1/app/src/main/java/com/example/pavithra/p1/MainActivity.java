package com.example.pavithra.p1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText e1, e2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b1 = (Button) findViewById(R.id.button);
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MatchUser();


            }
        });
        TextView b2 = (TextView) findViewById(R.id.textView);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Getting the Values from Views(Edittext & Spinner)

                //Intent For Navigating to Second Activity
                Intent i = new Intent(MainActivity.this, Register_teacher.class);

                //For Passing the Values to Second Activity


                startActivity(i);

            }
        });

    }

    public void MatchUser() {

        Database dat = new Database(getBaseContext());
        SQLiteDatabase db = dat.getWritableDatabase();

        Cursor c = db.rawQuery("select * from teacher1_table where name='" + e1.getText().toString() + "' and password='" + e2.getText().toString() + "'", null);
        if (c.getCount() != 0) {
            c.moveToFirst();
            Toast.makeText(getApplicationContext(), "Login successfull.", Toast.LENGTH_SHORT).show();
            finish();
            // Register_teacher.subject = c.getString(c.getColumnIndex("Subject"));
            Intent intent = new Intent(MainActivity.this, menu1.class);
            startActivity(intent);

        } else {
            Cursor c1 = db.rawQuery("select * from parent1_table where name='" + e1.getText().toString() + "' and password='" + e2.getText().toString() + "'", null);
            String name=e1.getText().toString();
            String pass=e2.getText().toString();
            if (c1.getCount() != 0) {
                c1.moveToFirst();
                Toast.makeText(getApplicationContext(), "Login successfull.", Toast.LENGTH_SHORT).show();
                finish();
                // Register_teacher.subject = c.getString(c.getColumnIndex("Subject"));
                Intent intent = new Intent(MainActivity.this, parent_login.class);
                intent.putExtra("NAME",name);
                intent.putExtra("PASS",pass);
                startActivity(intent);
            } else {
                Cursor c2 = db.rawQuery("select * from student1_table where name='" + e1.getText().toString() + "' and password='" + e2.getText().toString() + "'", null);
                if (c2.getCount() != 0) {
                    c2.moveToFirst();
                    Toast.makeText(getApplicationContext(), "Login successfull.", Toast.LENGTH_SHORT).show();
                    finish();
                    // Register_teacher.subject = c.getString(c.getColumnIndex("Subject"));
                    Intent intent = new Intent(MainActivity.this, student_login.class);
                    intent.putExtra("NAME",name);
                    intent.putExtra("PASS",pass);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid credentials", Toast.LENGTH_SHORT).show();
                }


            }

        }
            c.close();
            db.close();

        }
    }

