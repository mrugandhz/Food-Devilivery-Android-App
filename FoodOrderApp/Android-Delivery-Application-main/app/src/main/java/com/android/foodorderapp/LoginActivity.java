package com.android.foodorderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private Handler handler = new Handler();
    GoogleSignInClient mGoogleSignInClient;
    private static int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;


    EditText email;
    EditText password;
    Button register;
    Button login;
    String Email;
    String pwd;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressBar = (ProgressBar) findViewById(R.id.progressBar_cyclic);
        progressBar.setVisibility(View.INVISIBLE);


        // taking instance of FirebaseAuth
        mAuth = FirebaseAuth.getInstance();


        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        // Check for existing Google Sign In account, if the user is already signed in
// the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.btnGoogle);
        signInButton.setSize(SignInButton.SIZE_STANDARD);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        // Geeting the respective id's

        email = findViewById(R.id.inputUsername);
        password = findViewById(R.id.inputEmail);

    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
            }
            startActivity(new Intent(LoginActivity.this, MainActivity.class));

            // Signed in successfully, show authenticated UI.
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d("Text", toString());
        }
    }




    public void firbeaseLogin(View v)
    {


        Email = email.getText().toString();
        pwd= password.getText().toString();

        progressBar.setVisibility(View.VISIBLE);

        Intent intentputvalue = new Intent(getApplicationContext(), MainActivity.class);



        if(Email.isEmpty() ||  pwd.isEmpty() )
        {

            Toast.makeText(LoginActivity.this, "Please complete al the fields ", Toast.LENGTH_SHORT).show();


        }


        // signin existing user
        // signin existing user
        mAuth.signInWithEmailAndPassword(Email, pwd)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {


                                    System.out.println("The Email is"+ Email);

                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_LONG)
                                            .show();



                                    // if sign-in is successful
                                    // intent to home activity
                                    /* Intent intent
                                            = new Intent(LoginActivity.this,
                                            MainActivity.class);
                                    startActivity(intent); */


                                    intentputvalue.putExtra("Email_Id", Email);

                                    startActivity(intentputvalue);
                                }
                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_LONG)
                                            .show();

                                    progressBar.setVisibility(View.INVISIBLE);


                                }
                            }
                        });




                            }


    public void loggingin(View v)
    {
       Email = email.getText().toString();
       pwd= password.getText().toString();

       DataBaseHelper dataBaseHelper = new DataBaseHelper(LoginActivity.this);


       int i = dataBaseHelper.Login(Email,pwd);


       if(i>0)
       {
           Intent intent = new Intent(LoginActivity.this,MainActivity.class);
           startActivity(intent);
       }
       else
       {

           Toast.makeText(LoginActivity.this, "User does not exists", Toast.LENGTH_SHORT).show();



    }







    }

    public void Signmeup(View v)
    {

        // when click the signup on login page it should redirect to the signup page

        Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);

    }







}
