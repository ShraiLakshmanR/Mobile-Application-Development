package com.example.pavithra.p1;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.Telephony;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class BoardCast extends AppCompatActivity {
    EditText mMessage;
    Button mSubmit;
    //SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_cast);
        ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.SEND_SMS, Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                PackageManager.PERMISSION_GRANTED);

        mMessage = (EditText) findViewById(R.id.editText7);
        mSubmit = (Button) findViewById(R.id.button10);


        mSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(),mMessage.getText().toString(), Toast.LENGTH_LONG).show();
                Database dat = new Database(getBaseContext());
                SQLiteDatabase db = dat.getWritableDatabase();


                //SmsManager sm = SmsManager.getDefault();

                Cursor c = db.rawQuery("select * from student1_table", null);
                String mail[] = new String[c.getCount()];
                int i = 0;

                    if (c.getCount() !=0) {
                        c.moveToFirst();
                        do {
                            Log.d("Myinfo", "Inside do while");
                            String ename = c.getString(1);
                            mail[i] = ename;
                            i++;
                        }while (c.moveToNext());



                    } else {

                        Toast toast=Toast.makeText(getApplicationContext(),"Student email to be added",Toast.LENGTH_SHORT);
                        toast.show();
                        Log.d("Myy infro","under show");
                        return;
                    }

                String SENT = "Message sent";
                String DELIVERED = "Message Delivered";
                String mobile = "8618267464";
                //sm.sendTextMessage(mobile, null, mMessage.getText().toString(), null, null);
                //String[] mail={"shrai.lr@gmail.com,shrai.cs18@bmsce.ac.in,karthik.sanikop@gmail.com,shashankreddy0123@gmail.com,sagarbj.cs17@bmsmce.ac.in"};

                Intent in = new Intent(Intent.ACTION_SEND);
                in.setPackage("com.google.android.gm");
                in.setType("plain/text");
                in.putExtra(Intent.EXTRA_EMAIL, mail);


                in.putExtra(Intent.EXTRA_SUBJECT, "Remainder");
                in.putExtra(Intent.EXTRA_TEXT, mMessage.getText().toString());
                startActivity(in);

            }
        });
    }}





                //Toast.makeText(getApplicationContext(),mMessage.getText().toString(), Toast.LENGTH_LONG).show();
                //if(c.getCount()!=0){
                  //  c.moveToFirst();
                    //do{

                        //String mobile = c.getString(3);
                      //  String mobile="8618267464";
                        //PendingIntent SP=PendingIntent.getBroadcast(getApplicationContext(),0, new Intent(SENT),0);
                        //PendingIntent DSP=PendingIntent.getBroadcast(getApplicationContext(),0, new Intent(DELIVERED),0);
                       //sm.sendTextMessage(mobile, null, mMessage.getText().toString(), SP, DSP);
                       //Toast.makeText(getApplicationContext(),mobile, Toast.LENGTH_LONG).show();

                    //}while(c.moveToNext());

                //}

                //db.close();



     //   c.moveToFirst();
        //do{

        //lines.add("USN:\t\t"+c.getString(5)+"\n"+"Name:\t\t"+c.getString(0)+"\n"+"Email:\t\t"+c.getString(1)+"\n"+"Password:\t\t"+c.getString(2)+"\n"+"Phone:\t\t"+c.getString(3)+"\n"+"Parent:\t\t"+c.getString(4)+"\n");

        //}while(c.moveToNext());
