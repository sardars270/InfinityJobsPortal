package com.example.infinityjobportal.ui.postedJobs.closedJobs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.infinityjobportal.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class JobDetailsClosedJobs extends AppCompatActivity {

    TextView jobTitleClosed, companyNameClosed, locationClosed, salaryClosed, languageClosed, applicationDeadlineClosed, joiningDateClosed, jobDescriptionClosed, skillsRequiredClosed, qualificationClosed, industryClosed;
    Button markActive;

    FirebaseFirestore db;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details_closed_jobs);

        jobTitleClosed = findViewById(R.id.jobTitleTextViewActiveJob);
        companyNameClosed = findViewById(R.id.companyNameTextViewActiveJob);
        locationClosed = findViewById(R.id.locationTextViewActiveJob);
        salaryClosed = findViewById(R.id.salaryTextViewActiveJob);
        languageClosed = findViewById(R.id.languageTextViewActiveJobs);
        applicationDeadlineClosed = findViewById(R.id.applicationDeadlineTextViewActiveJob);
        joiningDateClosed = findViewById(R.id.joiningDateTextViewActiveJob);
        jobDescriptionClosed = findViewById(R.id.descriptionTextViewActiveJob);
        skillsRequiredClosed = findViewById(R.id.skillNeededTextViewActiveJob);
        qualificationClosed = findViewById(R.id.qualificationTextViewActiveJob);
        industryClosed = findViewById(R.id.industryTextViewActiveJob);
        markActive = findViewById(R.id.markClosedButton);

    }
}