package com.example.infinityjobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Splash extends AppCompatActivity {
    private static final String TAG = "Splash";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(Splash.this, ClientLogin.class);
                    finish();
                    startActivity(i);
                }
            }
        };
        timer.start();
//        Intent i = new Intent(getApplicationContext(),ClientLogin.class);
//        startActivity(i);
    }
}