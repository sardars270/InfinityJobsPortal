package com.example.infinityjobportal.ui.postJob;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class PostJobFragment extends Fragment {
    private static final String TAG = "PostJobFragment";

    DatePickerDialog datePickerDialog;

    private EditText mCompanyNameEditText, mJobTitleEditText, mStreetAddressEditText, mCityAddressEditText, mProvinceAddressEditText, mStartSalaryRangeEditText, mSalaryEndRangeEditText,
            mJoiningEditTextDate, mApplicationDeadlineEditTextDate, mJobDescriptionEditText, mSkillsRequiredEditText,
            mQualificationRequiredEditText;
    private Spinner mJobCategorySpinner, mTypeOfEmployerSpinner;
    private CheckBox mEnglishCheckBox, mFrenchCheckBox;
    private Button mPostJobSubmitButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Initialize an instance of Cloud Firestore.

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: has Started");
        View root = inflater.inflate(R.layout.fragment_post_job, container, false);

        mCompanyNameEditText = root.findViewById(R.id.companyNameEditText);
        mJobCategorySpinner = root.findViewById(R.id.jobCategorySpinner);
        mJobTitleEditText = root.findViewById(R.id.jobTitleEditText);
        mStreetAddressEditText = root.findViewById(R.id.streetAddressEditText);
        mCityAddressEditText = root.findViewById(R.id.cityAddressSpinner);
        mProvinceAddressEditText = root.findViewById(R.id.provinceSpinner);
        mEnglishCheckBox = root.findViewById(R.id.radioEnglish);
        mFrenchCheckBox = root.findViewById(R.id.radioFrench);
        mTypeOfEmployerSpinner = root.findViewById(R.id.typeOfEmploymentSpinner);
        mStartSalaryRangeEditText = root.findViewById(R.id.salaryStartRangeEditText);
        mSalaryEndRangeEditText = root.findViewById(R.id.salaryEndRangeEditText);
        mApplicationDeadlineEditTextDate = root.findViewById(R.id.applicationDeadlineEditTextDate);
        mJoiningEditTextDate = root.findViewById(R.id.joiningEditTextDate);
        mJobDescriptionEditText = root.findViewById(R.id.jobDescriptionEditText);
        mSkillsRequiredEditText = root.findViewById(R.id.skillsRequiredEditText);
        mQualificationRequiredEditText = root.findViewById(R.id.qualificationRequiredEditText);


        //Submit Button.
        mPostJobSubmitButton = root.findViewById(R.id.postJobSubmitButton);

        //Application Deadline calendar
        mApplicationDeadlineEditTextDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mApplicationDeadlineEditTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });

        //joining date calender
        mJoiningEditTextDate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                final int year = calendar.get(Calendar.YEAR);
                final int month = calendar.get(Calendar.MONTH);
                final int day = calendar.get(Calendar.DAY_OF_MONTH);


                datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mJoiningEditTextDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.show();
            }
        });


        // OnClick Listener for post a job submit button.
        mPostJobSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: for job submit started");

                //Firestore values
                String companyName = mCompanyNameEditText.getText().toString();
                String jobCategory = mJobCategorySpinner.getSelectedItem().toString();
                String jobTitle = mJobTitleEditText.getText().toString();
                String streetAddress = mStreetAddressEditText.getText().toString();
                String city = mCityAddressEditText.getText().toString();
                String province = mProvinceAddressEditText.getText().toString();

                String language = "";
                if (mEnglishCheckBox.isChecked()) {
                    language = "English";
                } else if (mFrenchCheckBox.isChecked()) {
                    language = "French";
                } else if (mFrenchCheckBox.isChecked() && mEnglishCheckBox.isChecked()) {
                    language = "English & French";
                }

                Double minSalary = Double.parseDouble(mStartSalaryRangeEditText.getText().toString());
                Double maxSalary = Double.parseDouble(mSalaryEndRangeEditText.getText().toString());

                String availability = mTypeOfEmployerSpinner.getSelectedItem().toString();
                String joiningDate = mJoiningEditTextDate.getText().toString();
                String applicationDeadline = mApplicationDeadlineEditTextDate.getText().toString();
                String jobDescription = mJobDescriptionEditText.getText().toString();
                String skillsRequired = mSkillsRequiredEditText.getText().toString();
                String qualificationRequired = mQualificationRequiredEditText.getText().toString();



                if (!hasValidationErrors(companyName, jobCategory, jobTitle, streetAddress, city, province, language, minSalary,
                        maxSalary, availability, joiningDate, applicationDeadline, jobDescription,
                        skillsRequired, qualificationRequired)) {

                    PostJobPojo postJobPOJO = new PostJobPojo(companyName, jobCategory, jobTitle, streetAddress, city, province, language,
                            minSalary, maxSalary, availability, joiningDate, applicationDeadline, jobDescription, skillsRequired, qualificationRequired, "active");

                    db.collection("Jobs")
                            .add(postJobPOJO)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                                    Toast.makeText(getContext(), "", Toast.LENGTH_SHORT).show();
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


            }

            private boolean hasValidationErrors(String companyName, String jobCategory, String jobTitle, String streetAddress,
                                                String city, String province, String language, Double minSalary, Double maxSalary, String availability,
                                                String joiningDate, String applicationDeadline, String jobDescription,
                                                String skillsRequired, String qualificationRequired) {
                if (companyName.isEmpty()) {
                    mCompanyNameEditText.setError("Company Name is required");
                    mCompanyNameEditText.requestFocus();
                    return true;
                }
                if (jobCategory.equals("Select the job category")) {
                    mCompanyNameEditText.setError("Job category is required");
                    mCompanyNameEditText.requestFocus();
                }
                if (jobTitle.isEmpty()) {
                    mJobTitleEditText.setError("Title is required");
                    mJobTitleEditText.requestFocus();
                    return true;
                }
                if (streetAddress.isEmpty()) {
                    mStreetAddressEditText.setError("Street address is required");
                    mStreetAddressEditText.requestFocus();
                    return true;
                }

                if (city.isEmpty()) {
                    mCityAddressEditText.setError("City is required");
                    mCityAddressEditText.requestFocus();
                    return true;
                }
                if (province.isEmpty()) {
                    mProvinceAddressEditText.setError("Province are required");
                    mProvinceAddressEditText.requestFocus();
                    return true;
                }
                if (language.isEmpty()) {
                    return true;
                }

                if (minSalary.equals(null)) {
                    mStartSalaryRangeEditText.setError("Minimum salary is required");
                    mStartSalaryRangeEditText.requestFocus();
                    return true;
                }
                if (maxSalary.equals(null)) {
                    mSalaryEndRangeEditText.setError("Maximum salary is required");
                    mSalaryEndRangeEditText.requestFocus();
                    return true;
                }
                if (availability.equals("Select the availability requirement")) {
                    mJoiningEditTextDate.setError("Availability is required");
                    mJoiningEditTextDate.requestFocus();
                }
                if (joiningDate.isEmpty()) {
                    mJoiningEditTextDate.setError("joining date is required");
                    mJoiningEditTextDate.requestFocus();
                    return true;
                }
                if (applicationDeadline.isEmpty()) {
                    mApplicationDeadlineEditTextDate.setError("Application deadline is required");
                    mApplicationDeadlineEditTextDate.requestFocus();
                    return true;
                }
                if (jobDescription.isEmpty()) {
                    mSkillsRequiredEditText.setError("Description is required");
                    mSkillsRequiredEditText.requestFocus();
                    return true;
                }
                if (skillsRequired.isEmpty()) {
                    mSkillsRequiredEditText.setError("Skills are required");
                    mSkillsRequiredEditText.requestFocus();
                    return true;
                }
                if (qualificationRequired.isEmpty()) {
                    mQualificationRequiredEditText.setError("Qualification is required");
                    mQualificationRequiredEditText.requestFocus();
                    return true;
                }
                return false;
            }
        });


        return root;


    }

}