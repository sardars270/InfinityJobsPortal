package com.example.infinityjobportal;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;


public class CompanyProfileActivity extends AppCompatActivity {

    AppCompatImageView ivEdit;
    AppCompatTextView tvCompanyName, tvIndustry, tvLocation, tvWeb, tvAbout, tvDescription, tvEmail, tvPhone;
    String companyId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Company");
        tvCompanyName = findViewById(R.id.tvCompanyName);
        tvIndustry = findViewById(R.id.tvIndustry);
        tvLocation = findViewById(R.id.tvLocation);
        tvWeb = findViewById(R.id.tvWeb);
        tvAbout = findViewById(R.id.tvAbout);
        tvDescription = findViewById(R.id.tvDescription);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);

        companyId = getIntent().getStringExtra("id");
        final String name = getIntent().getStringExtra("name");
        final String industry = getIntent().getStringExtra("industry");
        final String country = getIntent().getStringExtra("country");
        final String email = getIntent().getStringExtra("email");
        final String contact = getIntent().getStringExtra("contact");
        final String desc = getIntent().getStringExtra("desc");
        final String about = getIntent().getStringExtra("about");
        final String web = getIntent().getStringExtra("web");
        final String city = getIntent().getStringExtra("city");
        final String state = getIntent().getStringExtra("state");

        tvCompanyName.setText(name);
        tvIndustry.setText(industry);
        tvLocation.setText(city + ", " + state + ", " + country);
        tvWeb.setText(web);
        tvAbout.setText(about);
        tvDescription.setText(desc);
        tvEmail.setText(email);
        tvPhone.setText(contact);

        ivEdit = findViewById(R.id.ivEdit);
        ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CompanyProfileActivity.this, EditCompanyActivity.class);
                i.putExtra("id", companyId);
                i.putExtra("name", name);
                i.putExtra("industry", industry);
                i.putExtra("country", country);
                i.putExtra("email", email);
                i.putExtra("contact", contact);
                i.putExtra("desc", desc);
                i.putExtra("about", about);
                i.putExtra("web", web);
                i.putExtra("city", city);
                i.putExtra("state", state);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // todo: goto back activity from here


                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
