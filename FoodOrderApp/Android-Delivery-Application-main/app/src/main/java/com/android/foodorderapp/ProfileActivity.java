package com.android.foodorderapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ProfileActivity extends AppCompatActivity
{



    private Calendar calendar;
    private String date;
    private SimpleDateFormat dateFormat;


    LoginActivity ld = new LoginActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView myemail = findViewById(R.id.myemail1);
        TextView lastLogin = findViewById(R.id.lastLogin);

        calendar = Calendar.getInstance();

        dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        date = dateFormat.format(calendar.getTime());


        Intent intentrecive = getIntent();

        String emailid = intentrecive.getStringExtra("Email_Id");
        System.out.println("The Email in main activity" +emailid );


        // Setting up the Email ID received from main activity
        // Email is going from login to main activity and from main activity to ProfileActivity page

        myemail.setText(emailid);

        // It will set the current system date when the user logged in
        lastLogin.setText(date);

    }

    public void tomainPage(View view)
    {
        Intent intent = new Intent(ProfileActivity.this,MainActivity.class);
        startActivity(intent);

    }









}
