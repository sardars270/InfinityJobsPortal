package com.example.infinityjobportal.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.infinityjobportal.R;

public class ViewQuery extends AppCompatActivity {
TextView firstname, subject, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_view_query);
        firstname=findViewById(R.id.firstName);
        subject=findViewById(R.id.subject);
        description=findViewById(R.id.description);
        firstname.setText("");
        subject.setText("");
        description.setText("");
        //= getIntent().getStringExtra("sub");
        firstname.setText( getIntent().getStringExtra("usname"));
        subject.setText( getIntent().getStringExtra("sub"));
        description.setText( getIntent().getStringExtra("maincontent"));
        //String s =getIntent().getStringExtra("maincontent");

    }
}