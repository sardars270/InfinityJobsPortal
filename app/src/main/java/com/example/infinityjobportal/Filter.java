package com.example.infinityjobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

public class Filter extends AppCompatActivity {
    Spinner jobCategorySpinner;
    CheckBox mEnglishCheckBox, mFrenchCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    jobCategorySpinner = findViewById(R.id.jobCategorySpinner);
        mEnglishCheckBox = findViewById(R.id.radioEnglish);
        mFrenchCheckBox = findViewById(R.id.radioFrench);
    }

    public void applyFilter(View view) {

        GlobalStorage.jobCatogory = jobCategorySpinner.getSelectedItem().toString();

        if (mEnglishCheckBox.isChecked()) {
            GlobalStorage.language = "English";
        } else if (mFrenchCheckBox.isChecked()) {
            GlobalStorage.language = "Frenche";
        } else if (mFrenchCheckBox.isChecked() && mEnglishCheckBox.isChecked()) {
            GlobalStorage.language = "English & French";
        }

        startActivity(new Intent(getApplicationContext(),Jobs_search.class));
    }
}