package com.example.infinityjobportal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ClientLogin extends AppCompatActivity {

    private static final String TAG = "ClientLogin";

    public EditText emailEditText, password;
    public Button login, signUp, forgotPassword;
    ProgressBar progressBar;

    TextView errorView;
    public FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: has started.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);


        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signup);
        forgotPassword = findViewById(R.id.bt_forgotpassword);
        emailEditText = findViewById(R.id.email);
        password = findViewById(R.id.password);
        errorView = findViewById(R.id.errorView);
        progressBar = findViewById(R.id.progressBar);


        mAuth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: signUp started");
                startActivity(new Intent(getApplicationContext(), ClientSignUp.class));
            }
        });


    }

    public void logInFunction(View view) {
        Log.d(TAG, "logInFunction: has started");
        if (emailEditText.getText().toString().contentEquals("")) {


            errorView.setText("Email cant be empty");


        } else if (password.getText().toString().contentEquals("")) {

            errorView.setText("Password cant be empty");

        } else {

            progressBar.setVisibility(View.VISIBLE);

            mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(ClientLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null) {
                                    if (user.isEmailVerified()) {

                                        progressBar.setVisibility(View.GONE);
                                        //errorView.setText("");
                                        // errorView.setVisibility(View.GONE);
                                        Intent HomeActivity = new Intent(getApplicationContext(), MainActivity.class);
                                        //   setResult(RESULT_OK, null);
                                        startActivity(HomeActivity);


                                    } else {

                                        progressBar.setVisibility(View.GONE);
                                        errorView.setText("Please Verify your EmailID and SignIn");

                                    }
                                }

                            } else {
                                // If sign in fails, display a message to the user.

                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());

                                }

                            }

                        }
                    });


        }
        Log.d(TAG, "logInFunction: has ended.");

    }

}