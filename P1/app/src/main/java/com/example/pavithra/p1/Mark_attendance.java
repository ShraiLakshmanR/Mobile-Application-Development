package com.example.pavithra.p1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Mark_attendance extends AppCompatActivity {
    /** Called when the activity is first created. */

    ArrayList<String> lines,Unslist;
    ListView lv;
    String[] items;
    List<String> uncheck;
    static final int DATE_DIALOG_ID = 1;
    static final int TIME_DIALOG_ID = 2;
    static private int year=2021, month=0, day=30;
    private int hours=1, min=00,a;

    String dateformat;



    String subject = "";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark_attendence);


        uncheck = new ArrayList<String>();
        lines = new ArrayList<String>();
        Unslist = new ArrayList<String>();




        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);

        dateformat = year+"-"+month+"-"+day;
         final ListView lv = (ListView) findViewById(R.id.lt);
        Button btn = (Button) findViewById(R.id.button12);
        Button date = (Button) findViewById(R.id.button9);






        date.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                showDialog(DATE_DIALOG_ID);
            }
        });

        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Toast.makeText(getApplicationContext(), "Attendance taken successfully", Toast.LENGTH_SHORT).show();
                SparseBooleanArray checked = lv.getCheckedItemPositions();

                Database dat = new Database(getBaseContext());
                SQLiteDatabase db = dat.getWritableDatabase();


                for(int i=0; i<items.length; i++) {
                    String usn = Unslist.get(i);



                        Cursor c = db.rawQuery("select * from student1_table where USN='"+usn+"'", null);

                        c.moveToFirst();
                        String data = c.getString(c.getColumnIndex("Attendance1"));
                        String no = c.getString(c.getColumnIndex("PARENT"));

                        String[] temp = data.split(" ");
                        int attended = Integer.parseInt(temp[2]);
                        int total = Integer.parseInt(temp[1]);
                        total++;


                        int percentbef;
                        int percent ;
                        if(!checked.get(i)){
                            attended++;
                            percentbef = attended*100;
                            percent = percentbef/total;


                        }else{
                            percentbef = attended*100;
                            percent = percentbef/total;
                            SmsManager sm = SmsManager.getDefault();
                            sm.sendTextMessage(no, null, "Your ward is absent for today's class, total percent is: "+percent+"% Minimum should be 75%", null, null);


                        }



                        String finaldata = subject+" "+total+" "+attended+" "+percent;
                        ContentValues cv = new ContentValues();
                        cv.put("Attendance1", finaldata);
                        db.update("student1_table", cv, "USN='"+usn+"'", null);
                        c.close();








                    }


                db.close();
                finish();
            }
        });

        Database dat = new Database(getBaseContext());
        SQLiteDatabase db = dat.getWritableDatabase();

        Cursor c = db.rawQuery("select * from student1_table", null);

        if(c.getCount()!=0){
            c.moveToFirst();
            do{

                lines.add(c.getString(c.getColumnIndex("NAME")));
                Unslist.add(c.getString(c.getColumnIndex("USN")));

            }while(c.moveToNext());

        }

        db.close();
        items = lines.toArray(new String[lines.size()]);

        ArrayAdapter<String> aa = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_multiple_choice,items);

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        lv.setAdapter(aa);


    }

    private DatePickerDialog.OnDateSetListener dateListener =
            new DatePickerDialog.OnDateSetListener() {


                @Override
                public void onDateSet(DatePicker view, int yr, int monthOfYear,
                                      int dayOfMonth) {
                    // TODO Auto-generated method stub


                    year = yr;
                    month = monthOfYear+1;
                    day = dayOfMonth;
                    Toast.makeText(getApplicationContext(),day+ " "+month+" "+year, Toast.LENGTH_SHORT).show();
                    //updateDate();
                }
            };

    protected Dialog onCreateDialog(int id){
        switch(id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateListener, year, month, day);

        }
        return null;

    }
}