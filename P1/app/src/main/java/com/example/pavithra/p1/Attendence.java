package com.example.pavithra.p1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Attendence extends AppCompatActivity {
Button mark,view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        mark=(Button)findViewById(R.id.bu3);
        view=(Button)findViewById(R.id.bu1);
        mark.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent i=new Intent(Attendence.this,Mark_attendance.class);
                startActivity(i);
            }
        });

        view.setOnClickListener(new View.OnClickListener()
        {


            @Override
            public void onClick(View v)
            {
                Intent i1=new Intent(Attendence.this,View_attendance.class);
                startActivity(i1);
            }
        });





    }
}
