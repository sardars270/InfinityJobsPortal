package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ClientLogin extends AppCompatActivity {
    public EditText email, pass;
    public Button login, signup, forgotpassword;

    TextView errorView;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);


        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        forgotpassword = findViewById(R.id.bt_forgotpassword);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        errorView = findViewById(R.id.errorView);


        mAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ClientSignUp.class));
            }
        });



    }

    public void logInFunction(View view) {
        if (email.getText().toString().contentEquals("")) {


            errorView.setText("Email cant be empty");


        } else if (pass.getText().toString().contentEquals("")) {

            errorView.setText("Password cant be empty");

        } else {


            mAuth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString())
                    .addOnCompleteListener(ClientLogin.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();

                                if (user != null) {
                                    if (user.isEmailVerified()) {


                                        //errorView.setText("");
                                        // errorView.setVisibility(View.GONE);
                                        Intent HomeActivity = new Intent(getApplicationContext(), MainActivity.class);
                                        //   setResult(RESULT_OK, null);
                                        startActivity(HomeActivity);



                                    } else {


                                        errorView.setText("Please Verify your EmailID and SignIn");

                                    }
                                }

                            } else {
                                // If sign in fails, display a message to the user.


                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                if (task.getException() != null) {
                                    errorView.setText(task.getException().getMessage());

                                }

                            }

                        }
                    });


        }

    }

}