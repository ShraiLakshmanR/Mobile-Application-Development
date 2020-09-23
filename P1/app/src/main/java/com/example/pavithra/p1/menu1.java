package com.example.pavithra.p1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class menu1 extends AppCompatActivity {

    ListView list;
    String[] web = {
            "STUDENT",
            "PARENT",
            "ATTENDENCE",
            "NOTES",
            "REMAINDER",
            "BROADCAST"

    } ;
    Integer[] imageId = {
            R.drawable.student,
            R.drawable.parent,
            R.drawable.attendence,
            R.drawable.notes,
            R.drawable.remainder,
            R.drawable.broadcast


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        CustomList1 adapter = new CustomList1(menu1.this, web, imageId);
        list=(ListView)findViewById(R.id.subbu);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent i=null;
                switch(position)
                {
                    case 0:
                        i=new Intent(menu1.this,Stud.class);
                        startActivity(i);
                        break;
                    case 1: i=new Intent(menu1.this,Parent.class);
                        startActivity(i);
                        break;
                    case 2: i=new Intent(menu1.this,Attendence.class);
                        startActivity(i);
                        break;
                    case 3:

                        final String str_text = "https://drive.google.com/drive/folders/1OGrj0Bjs27ZZGZTVgd4gRxLUfw2NQ3TT?usp=sharing";
                        Intent browserIntent = new Intent
                                (
                                        Intent.ACTION_VIEW, Uri.parse(str_text));
                        startActivity(browserIntent);
                        break;
                    case 4:
                        i=new Intent(menu1.this,AndroidTimeActivity.class);
                        startActivity(i);
                        break;
                    case 5:
                        i=new Intent(menu1.this,BoardCast.class);
                        startActivity(i);
                        break;


                }
            }
        });

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
