package com.example.pavithra.p1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class student_login extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String n1;
    String p1;
    Intent i1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        i1=getIntent();
        n1=i1.getStringExtra("NAME");
        p1=i1.getStringExtra("PASS");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent i=new Intent(this,MainActivity.class);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_attendance) {




            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();

            bundle.putString("N1",n1);
            bundle.putString("P1",p1);
            student_view_attendence f1 = new student_view_attendence();
            f1.setArguments(bundle);

            fragmentTransaction.add(R.id.content_student_login, f1);
            fragmentTransaction.commit();

            // Handle the camera action

        } else if (id == R.id.nav_changePassword) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
            Bundle bundle = new Bundle();

            bundle.putString("N1",n1);
            bundle.putString("P1",p1);
            student_change_password f1=new student_change_password();
            f1.setArguments(bundle);
            fragmentTransaction.add(R.id.content_student_login,f1);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_logout) {
            Intent i1=new Intent(this,MainActivity.class);
            startActivity(i1);

        }else if(id == R.id.nav_notes)
        {   final String str_text = "https://drive.google.com/drive/folders/1OGrj0Bjs27ZZGZTVgd4gRxLUfw2NQ3TT?usp=sharing";
            Intent browserIntent = new Intent
                    (
                            Intent.ACTION_VIEW, Uri.parse(str_text));
            startActivity(browserIntent);
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
