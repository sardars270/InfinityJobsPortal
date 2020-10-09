package com.example.infinityjobportal.ui.postJob;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.example.infinityjobportal.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostJobFragment extends Fragment {
    private static final String TAG = "PostJobFragment";

    //Firestore Keys
    private static final String JOB_TITLE = "Job title";
    private static final String JOB_CATEGORY = "Job Category";
    private static final String COMPANY_NAME = "Company Name";
    private static final String STREET_ADDRESS = "Street Address";
    private static final String CITY = "City";
    private static final String PROVINCE = "Province";
    private static final String LANGUAGE_ENGLISH = "Language English";
    private static final String LANGUAGE_FRENCH = "Language French";
    private static final String AVAILABILITY = "Availability";
    private static final String MIN_SALARY = "Minimum Salary";
    private static final String MAX_SALARY = "Maximum Salary";
    private static final String APPLICATION_DEADLINE = "Application Deadline";
    private static final String JOINING_DATE = "Joining Date";
    private static final String JOB_DESCRIPTION = "Job Description";
    private static final String SKILLS_REQUIRED = "Skills Required";
    private static final String QUALIFICATION_REQUIRED = "Qualification Required";


    private PostJobViewModel postJobViewModel;
    private EditText mCompanyNameEditText,mJobTitleEditText, mStreetAddressEditText, mStartSalaryRangeEditText, mSalaryEndRangeEditText,
                        mJoiningEditTextDate, mApplicationDeadlineEditTextDate, mJobDescriptionEditText, mSkillsRequiredEditText,
                        mQualificationRequiredEditText;
    private Spinner mJobCategorySpinner, mCityAddressSpinner, mProvinceAddressSpinner, mTypeOfEmployerSpinner;
    private CheckBox mEnglishCheckBox, mFrenchCheckBox;
    private Button mPostJobSubmitButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Initialize an instance of Cloud Firestore.

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: has Started");

        postJobViewModel =
                ViewModelProviders.of(this).get(PostJobViewModel.class);
        View root = inflater.inflate(R.layout.fragment_post_job, container, false);
        final TextView textView = root.findViewById(R.id.letsGetStartedTextView);
        postJobViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });




        //EditText of Post a Job fragment.
        mCompanyNameEditText = root.findViewById(R.id.companyNameEditText);
        mJobTitleEditText = root.findViewById(R.id.jobTitleEditText);
        mStreetAddressEditText = root.findViewById(R.id.streetAddressEditText);
        mStartSalaryRangeEditText = root.findViewById(R.id.salaryStartRangeEditText);
        mSalaryEndRangeEditText = root.findViewById(R.id.salaryEndRangeEditText);
        mJoiningEditTextDate = root.findViewById(R.id.joiningEditTextDate);
        mApplicationDeadlineEditTextDate = root.findViewById(R.id.applicationDeadlineEditTextDate);
        mJobDescriptionEditText = root.findViewById(R.id.jobDescriptionEditText);
        mSkillsRequiredEditText = root.findViewById(R.id.skillsRequiredEditText);
        mQualificationRequiredEditText = root.findViewById(R.id.qualificationRequiredEditText);

        //Spinners of Post a Job Fragment.
        mJobCategorySpinner = root.findViewById(R.id.jobCategorySpinner);
        mCityAddressSpinner = root.findViewById(R.id.cityAddressSpinner);
        mProvinceAddressSpinner = root.findViewById(R.id.provinceSpinner);
        mTypeOfEmployerSpinner = root.findViewById(R.id.typeOfEmploymentSpinner);

        //CheckBox of Post a Job Spinner.
        mEnglishCheckBox = root.findViewById(R.id.radioEnglish);
        mFrenchCheckBox = root.findViewById(R.id.radioFrench);

        //Submit Button.
        mPostJobSubmitButton = root.findViewById(R.id.postJobSubmitButton);


        // OnClick Listener for post a job submit button.
        mPostJobSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: for job submit started");

                String companyName = mCompanyNameEditText.getText().toString();
                String jobCategory = mJobCategorySpinner.getSelectedItem().toString();
                String jobTitle = mJobTitleEditText.getText().toString();
                String streetAddress = mStreetAddressEditText.getText().toString();
                String city = mCityAddressSpinner.getSelectedItem().toString();
                String province = mProvinceAddressSpinner.getSelectedItem().toString();

                double minSalary = Double.parseDouble(mStartSalaryRangeEditText.getText().toString());
                double maxSalary = Double.parseDouble(mSalaryEndRangeEditText.getText().toString());

                String availability = mTypeOfEmployerSpinner.getSelectedItem().toString();
                String joiningDate = mJoiningEditTextDate.getText().toString();
                String applicationDeadline = mApplicationDeadlineEditTextDate.getText().toString();
                String jobDescription = mJobDescriptionEditText.getText().toString();
                String skillsRequired = mSkillsRequiredEditText.getText().toString();
                String qualificationRequired = mQualificationRequiredEditText.getText().toString();



                Map<String, Object> jobs = new HashMap<>();
                jobs.put(JOB_TITLE, jobTitle);
                jobs.put(JOB_CATEGORY, jobCategory);
                jobs.put(COMPANY_NAME, companyName);
                jobs.put(STREET_ADDRESS, streetAddress);
                jobs.put(CITY, city);
                jobs.put(PROVINCE, province);
                jobs.put(LANGUAGE_ENGLISH, jobTitle);
                jobs.put(LANGUAGE_FRENCH, jobTitle);
                jobs.put(AVAILABILITY, availability);
                jobs.put(MIN_SALARY, minSalary);
                jobs.put(MAX_SALARY, maxSalary);
                jobs.put(APPLICATION_DEADLINE, applicationDeadline);
                jobs.put(JOINING_DATE, joiningDate);
                jobs.put(JOB_DESCRIPTION, jobDescription);
                jobs.put(SKILLS_REQUIRED, skillsRequired);
                jobs.put(QUALIFICATION_REQUIRED, qualificationRequired);


                db.collection("Jobs")
                        .add(jobs)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error adding document", e);
                            }
                        });


                Navigation.findNavController(view).navigate(R.id.myJobsFragment);

                Log.d(TAG, "onClick: for job post has ended");
                
            }
        });




        return root;


    }
}