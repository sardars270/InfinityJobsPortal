package com.example.infinityjobportal.ui.postJob;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PostJobFragment extends Fragment {
    private static final String TAG = "PostJobFragment";

    DatePickerDialog datePickerDialog;
    double latitude;
    double longitude;

    private EditText mJobTitleEditText, mStreetAddressEditText, mCityAddressEditText, mProvinceAddressEditText, mStartSalaryRangeEditText, mSalaryEndRangeEditText,
            mJoiningEditTextDate, mAvailabilityEditText, mApplicationDeadlineEditTextDate, mJobDescriptionEditText, mSkillsRequiredEditText,
            mQualificationRequiredEditText;
    private Spinner mJobCategorySpinner, mCompanyNameSpinner;
    private CheckBox mEnglishCheckBox, mFrenchCheckBox;
    private Button mPostJobSubmitButton, mPostJobDraftButton;

    private FirebaseFirestore db = FirebaseFirestore.getInstance(); //Initialize an instance of Cloud Firestore.

    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: has Started");
        View root = inflater.inflate(R.layout.fragment_post_job, container, false);

        mCompanyNameSpinner = root.findViewById(R.id.companyNameSpinner);
        mJobCategorySpinner = root.findViewById(R.id.jobCategorySpinner);
        mJobTitleEditText = root.findViewById(R.id.jobTitleEditText);
        mStreetAddressEditText = root.findViewById(R.id.streetAddressEditText);
        mCityAddressEditText = root.findViewById(R.id.cityAddressEditText);
        mProvinceAddressEditText = root.findViewById(R.id.provinceEditText);
        mEnglishCheckBox = root.findViewById(R.id.radioEnglish);
        mFrenchCheckBox = root.findViewById(R.id.radioFrench);
        mAvailabilityEditText = root.findViewById(R.id.availabilityEditText);
        mStartSalaryRangeEditText = root.findViewById(R.id.salaryStartRangeEditText);
        mSalaryEndRangeEditText = root.findViewById(R.id.salaryEndRangeEditText);
        mApplicationDeadlineEditTextDate = root.findViewById(R.id.applicationDeadlineEditTextDate);
        mJoiningEditTextDate = root.findViewById(R.id.joiningEditTextDate);

        mJobDescriptionEditText = root.findViewById(R.id.jobDescriptionEditText);
        mJobDescriptionEditText.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (mJobDescriptionEditText.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        mSkillsRequiredEditText = root.findViewById(R.id.skillsRequiredEditText);
        mSkillsRequiredEditText.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (mSkillsRequiredEditText.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });


        mQualificationRequiredEditText = root.findViewById(R.id.qualificationRequiredEditText);
        mQualificationRequiredEditText.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                if (mQualificationRequiredEditText.hasFocus()) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_SCROLL:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            return true;
                    }
                }
                return false;
            }
        });

        //Submit Button.
        mPostJobSubmitButton = root.findViewById(R.id.postJobSubmitButton);
        mPostJobDraftButton = root.findViewById(R.id.postJobDraftButton);


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


        //Spinner for company names
        CollectionReference myCompaniesCollectionRef = db.collection("mycompanies");
        final List<String> companyNames = new ArrayList<>();
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, companyNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCompanyNameSpinner.setAdapter(adapter);
        myCompaniesCollectionRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String companyName = document.getString("name");
                        companyNames.add(companyName);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });

        // OnClick Listener for post a job submit button.
        mPostJobSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: for job submit started");

                //Firestore values
                String companyName = mCompanyNameSpinner.getSelectedItem().toString();
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

                Float minSalary = Float.parseFloat(mStartSalaryRangeEditText.getText().toString());
                Float maxSalary = Float.parseFloat(mSalaryEndRangeEditText.getText().toString());

                String availability = mAvailabilityEditText.getText().toString();
                String joiningDate = mJoiningEditTextDate.getText().toString();
                String applicationDeadline = mApplicationDeadlineEditTextDate.getText().toString();
                String jobDescription = mJobDescriptionEditText.getText().toString();
                String skillsRequired = mSkillsRequiredEditText.getText().toString();
                String qualificationRequired = mQualificationRequiredEditText.getText().toString();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                String date = currentDate.format(todayDate);

                String Add = mStreetAddressEditText.getText().toString() + "," + mCityAddressEditText.getText().toString();
                Log.d(TAG, "onClick: Address: " + Add);
                List<Address> addressList = null;
                if (Add != null || !Add.equals("")) {
                    Geocoder geocoder = new Geocoder(getContext());
                    try {
                        addressList = geocoder.getFromLocationName(Add, 1);
                        final Address address = addressList.get(0);
                        final LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        latitude = latLng.latitude;
                        longitude = latLng.longitude;

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                final Address address = addressList.get(0);
                final LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                latitude = address.getLatitude();
                longitude = address.getLongitude();


                if (!hasValidationErrors(companyName, jobCategory, jobTitle, streetAddress, city, province, language, minSalary,
                        maxSalary, availability, joiningDate, applicationDeadline, jobDescription,
                        skillsRequired, qualificationRequired)) {

                    PostJobPojo postJobPOJO = new PostJobPojo(companyName, jobCategory, jobTitle, streetAddress, city, province, language,
                            minSalary, maxSalary, availability, joiningDate, applicationDeadline, jobDescription, skillsRequired, qualificationRequired, "active", date, latitude, longitude);

                    db.collection("Jobs")
                            .add(postJobPOJO)
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


                    Navigation.findNavController(view).navigate(R.id.postedJobsFragment);

                    Log.d(TAG, "onClick: for job post has ended");

                }


            }


            //Validations
            private boolean hasValidationErrors(String companyName, String jobCategory, String jobTitle, String streetAddress,
                                                String city, String province, String language, Float minSalary, Float maxSalary, String availability,
                                                String joiningDate, String applicationDeadline, String jobDescription,
                                                String skillsRequired, String qualificationRequired) {
                Log.d(TAG, "hasValidationErrors: started");
                if (companyName.isEmpty()) {
                    TextView errorText = (TextView) mCompanyNameSpinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Company name is required");
                    return true;
                }
                if (jobCategory.isEmpty()) {
                    TextView errorText = (TextView) mJobCategorySpinner.getSelectedView();
                    errorText.setError("");
                    errorText.setTextColor(Color.RED);//just to highlight that this is an error
                    errorText.setText("Job Category is required");
                    return true;
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

                if (minSalary == null) {
                    mStartSalaryRangeEditText.setError("Minimum salary is required");
                    mStartSalaryRangeEditText.requestFocus();
                    return true;
                }
                if (maxSalary == null) {
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

        mPostJobDraftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String companyName = mCompanyNameSpinner.getSelectedItem().toString();
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

                Float minSalary = Float.parseFloat(mStartSalaryRangeEditText.getText().toString());
                Float maxSalary = Float.parseFloat(mSalaryEndRangeEditText.getText().toString());

                String availability = mAvailabilityEditText.getText().toString();
                String joiningDate = mJoiningEditTextDate.getText().toString();
                String applicationDeadline = mApplicationDeadlineEditTextDate.getText().toString();
                String jobDescription = mJobDescriptionEditText.getText().toString();
                String skillsRequired = mSkillsRequiredEditText.getText().toString();
                String qualificationRequired = mQualificationRequiredEditText.getText().toString();
                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                String date = currentDate.format(todayDate);


                PostJobPojo postJobPOJO = new PostJobPojo(companyName, jobCategory, jobTitle, streetAddress, city, province, language,
                        minSalary, maxSalary, availability, joiningDate, applicationDeadline, jobDescription, skillsRequired, qualificationRequired, "draft", date, latitude, longitude);

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


                Navigation.findNavController(view).navigate(R.id.postedJobsFragment);

            }
        });


        return root;


    }

}