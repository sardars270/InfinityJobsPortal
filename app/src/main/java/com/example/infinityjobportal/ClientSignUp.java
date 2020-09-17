package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import   com.example.infinityjobportal.model.*;

public class ClientSignUp extends AppCompatActivity {
    EditText firstName,lastName,email,password,mobile;
    Button login,signup;

    public FirebaseAuth mAuth;
    FirebaseFirestore db;

    TextView errorView;
    String emailString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_sign_up);


        firstName=findViewById(R.id.firstName);
        lastName=(EditText)findViewById(R.id.lastName);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        mobile=(EditText)findViewById(R.id.mobile);
        login=(Button) findViewById(R.id.login);
        signup=(Button)findViewById(R.id.signup);
        emailString= email.getText().toString();

        errorView = findViewById(R.id.errorView);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ClientLogin.class);
                startActivity(i);
            }
        });




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().contentEquals("")) {


                    errorView.setText("Email cannot be empty");


                } else if (password.getText().toString().contentEquals("")) {


                    errorView.setText("Password cannot be empty");


                } else {


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

                                                                //Toast.makeText(getApplicationContext(),"Verfy Email",Toast.LENGTH_LONG).show();
                                                                //  loadOtherDetails();
                                                                AlertDialog.Builder builder = new AlertDialog.Builder(ClientSignUp.this);
                                                                builder.setMessage("A verification Email Is Sent To Your Registered EmailID, please click on the link and Sign in again!")
                                                                        .setCancelable(false)
                                                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                            public void onClick(DialogInterface dialog, int id) {
                                                                                Intent i = new Intent(getApplicationContext(),ClientLogin.class);
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





    }
}