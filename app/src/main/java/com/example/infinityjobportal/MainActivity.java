package com.example.infinityjobportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;


public class MainActivity extends AppCompatActivity {

    AppCompatButton btnMySkills, btnSkills;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMySkills = findViewById(R.id.btnMySkills);
        btnSkills = findViewById(R.id.btnSkills);

        btnMySkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, MySkillsActivity.class);
                startActivity(i);

            }
        });

        btnSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this, SkillActivity.class);
                startActivity(i);

            }
        });

    }
}
