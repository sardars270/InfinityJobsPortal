package com.example.infinityjobportal;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ClientSignUp extends AppCompatActivity {
    private static final String TAG = "ClientSignUp";
    EditText firstName, lastName, email, password, mobile;
    Button login, signUp;
    ProgressBar progressBar;

    public FirebaseAuth mAuth;
    FirebaseFirestore db;

    TextView errorView;
    String emailString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: has started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);


        firstName = findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        mobile = (EditText) findViewById(R.id.mobile);
        login = (Button) findViewById(R.id.login);
        signUp = (Button) findViewById(R.id.signup);
        emailString = email.getText().toString();
        progressBar = findViewById(R.id.progressBar);

        errorView = findViewById(R.id.errorView);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: login");
                Intent i = new Intent(getApplicationContext(), ClientLogin.class);
                startActivity(i);
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: signUp");

                if (email.getText().toString().contentEquals("")) {


                    errorView.setText("Email cannot be empty");


                } else if (password.getText().toString().contentEquals("")) {


                    errorView.setText("Password cannot be empty");


                } else {


                    progressBar.setVisibility(View.VISIBLE);

                    mAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                @Override
                                public void onSuccess(AuthResult authResult) {
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    try {
                                        if (user != null)
                                            user.sendEmailVerification()
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                // loadOtherDetails();
                                                                progressBar.setVisibility(View.GONE);
                                                                //Toast.makeText(getApplicationContext(),"Verfy Email",Toast.LENGTH_LONG).show();
                                                                //  loadOtherDetails();
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(ClientSignUp.this);
                                                                builder.setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                Intent i = new Intent(getApplicationContext(), ClientLogin.class);
                                                                                startActivity(i);
                                                                            }
                                                                        });
                                                                AlertDialog alert = builder.create();
                                                                alert.show();

                                                            }
                                                        }

                                                        private void loadOtherDetails() {

                                                            User user = new User();
                                                            user.setFirstName(firstName.getText().toString());
                                                            user.setLastName(lastName.getText().toString());
                                                            user.setNumber(mobile.getText().toString());
                                                            user.setEmail(email.getText().toString());

                                                            db.collection("users").document(user.getEmail()).set(user)
                                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                                        @Override
                                                                        public void onSuccess(Void aVoid) {

                                                                        }
                                                                    })
                                                                    .addOnFailureListener(new OnFailureListener() {
                                                                        @Override
                                                                        public void onFailure(@NonNull Exception e) {

                                                                        }
                                                                    });


                                                        }
                                                    });

                                    } catch (Exception e) {
                                        errorView.setText(e.getMessage());
                                    }

                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    errorView.setText(e.getMessage());
                                }
                            });


                }

            }
        });

        Log.d(TAG, "onCreate: has ended.");
    }
}