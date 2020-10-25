package com.example.infinityjobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.NumberPicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Filter extends AppCompatActivity {
    Spinner jobCategorySpinner;
    CheckBox mEnglishCheckBox, mFrenchCheckBox;

    SeekBar seekBar;
    NumberPicker dollarPicker;
    NumberPicker centsPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
    jobCategorySpinner = findViewById(R.id.jobCategorySpinner);
        mEnglishCheckBox = findViewById(R.id.radioEnglish);
        mFrenchCheckBox = findViewById(R.id.radioFrench);

        dollarPicker=findViewById(R.id.dollar);
        centsPicker=findViewById(R.id.cents);



        dollarPicker.setMinValue(13);
        dollarPicker.setMaxValue(50);

        centsPicker.setMinValue(0);
        centsPicker.setMaxValue(99);



    }

    public void applyFilter(View view) {
        GlobalStorage.language="";
        GlobalStorage.jobCatogory = jobCategorySpinner.getSelectedItem().toString();

        if (mEnglishCheckBox.isChecked()  && !mFrenchCheckBox.isChecked()) {
            GlobalStorage.language = "English";
        } else if (mFrenchCheckBox.isChecked()  && !mEnglishCheckBox.isChecked()) {
            GlobalStorage.language = "French";
        } else if (mFrenchCheckBox.isChecked() && mEnglishCheckBox.isChecked()) {
            GlobalStorage.language = "English & French";
        }else{
            GlobalStorage.language = "";
        }

        startActivity(new Intent(getApplicationContext(),Jobs_search.class));
    }
}