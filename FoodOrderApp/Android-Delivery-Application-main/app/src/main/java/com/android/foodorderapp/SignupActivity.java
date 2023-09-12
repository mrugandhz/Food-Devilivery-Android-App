package com.android.foodorderapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignupActivity extends AppCompatActivity
{


    private EditText username, email, password, Confirmpwd;
    private String uname;
    private String Email;
    private String pwd;
    private String confirmpwd;
    private FirebaseAuth mAuth;

    DataBaseHelper myDB;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDB = new DataBaseHelper(this);



        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();



        // Getting the respective id's
        username = findViewById(R.id.inputUsername);
        email = findViewById(R.id.inputEmail);
        password = findViewById(R.id.inputPassword);
        Confirmpwd = findViewById(R.id.inputConfirmPassword);
    }






    //Firebase Registration
    public void Firereg(View v){

        uname  = username.getText().toString();
        Email = email.getText().toString();
        pwd = password.getText().toString();
        confirmpwd = Confirmpwd.getText().toString();





        //Write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();


        if(uname.isEmpty() || Email.isEmpty() || pwd.isEmpty() || confirmpwd.isEmpty())
        {

            Toast.makeText(SignupActivity.this, "Please complete al the fields ", Toast.LENGTH_SHORT).show();


        }



        else
        {

            if(pwd.equals(confirmpwd)){

                // create new user or register new user
                mAuth
                        .createUserWithEmailAndPassword(Email, pwd)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Registration successful!",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                    // if the user created intent to login activity
                                    Intent intent
                                            = new Intent(SignupActivity.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                } else {

                                    // Registration failed
                                    Toast.makeText(
                                                    getApplicationContext(),
                                                    "Registration failed!!"
                                                            + " Please try again later",
                                                    Toast.LENGTH_LONG)
                                            .show();


                                }
                            }
                        });








            }




        }








    }


    public void Registerme(View v)
    {

        uname  = username.getText().toString();
        Email = email.getText().toString();
        pwd = password.getText().toString();
        confirmpwd = Confirmpwd.getText().toString();

        //Write message to database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

          User user;


             user = new User(-1, uname,Email,pwd);

             DataBaseHelper dataBaseHelper = new DataBaseHelper(SignupActivity.this);


             if(uname.isEmpty() || Email.isEmpty() || pwd.isEmpty() || confirmpwd.isEmpty())
             {

                 Toast.makeText(SignupActivity.this, "Please complete al the fields ", Toast.LENGTH_SHORT).show();


             }

             else
             {

                 if(pwd.equals(confirmpwd)){






                     boolean success = dataBaseHelper.addData(user);


                     if(success)
                     {
                         Toast.makeText(SignupActivity.this, "You have Successfully Registered, Please Log In", Toast.LENGTH_SHORT).show();
                     }
                     else
                     {
                         Toast.makeText(SignupActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
                     }



                 }




             }





    }



    public void Gotologin(View v)
    {

        Intent intent = new Intent(SignupActivity.this,LoginActivity.class);
        startActivity(intent);

    }



}
