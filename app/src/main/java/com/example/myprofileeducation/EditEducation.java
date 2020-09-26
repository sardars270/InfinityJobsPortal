package com.example.myprofileeducation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EditEducation extends AppCompatActivity implements OnClickListener {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private Button back, update, delete;
    private EditText school1, degree1, fieldOfStudy1, startdate, enddate, grades, extraAct, discription1;
    CollectionReference reference = db.collection("Education");
    private pojoAddNewEducation pojoAddNewEducation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_education);
        pojoAddNewEducation = (pojoAddNewEducation) getIntent().getSerializableExtra("Education");
        back = findViewById(R.id.button_back);
        school1=findViewById(R.id.school);
        degree1 = findViewById(R.id.Degree);
        fieldOfStudy1 = findViewById(R.id.studyField);
        startdate = findViewById(R.id.start_date);
        enddate = findViewById(R.id.end_date);
        grades = findViewById(R.id.grade);
        extraAct = findViewById(R.id.activities_societies);
        discription1 = findViewById(R.id.description);

        school1.setText(pojoAddNewEducation.getSchool());
        degree1.setText(pojoAddNewEducation.getDegree());
        fieldOfStudy1.setText(pojoAddNewEducation.getFieldOfStudy());
        startdate.setText(pojoAddNewEducation.getStartDate());
        enddate.setText(pojoAddNewEducation.getEndDate());
        grades.setText(pojoAddNewEducation.getGrade());
        extraAct.setText(pojoAddNewEducation.getExtraActs());
        discription1.setText(pojoAddNewEducation.getdescription());
        findViewById(R.id.button_update).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);

back.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick(View view) {
        startActivity(new Intent(EditEducation.this, MainActivity.class));

    }
});

    }

    private boolean hasValidationErrors(String school, String degree, String fieldOfStudy, String startDate, String endDate, String grade, String extraActs, String discription) {
        if (school.isEmpty()) {
            school1.setError("School Name is required");
            school1.requestFocus();
            return true;
        }

        if (degree.isEmpty()) {
            degree1.setError("Degree is required");
            degree1.requestFocus();
            return true;
        }

        if (fieldOfStudy.isEmpty()) {
            fieldOfStudy1.setError("Field of study is required");
            fieldOfStudy1.requestFocus();
            return true;
        }

        if (startDate.isEmpty()) {
            startdate.setError("Start date is required");
            startdate.requestFocus();
            return true;
        }

        if (endDate.isEmpty()) {
            enddate.setError("End date is required");
            enddate.requestFocus();
            return true;
        }
        if (grade.isEmpty()) {
            grades.setError("Grades are required");
            grades.requestFocus();
            return true;
        }
        if (extraActs.isEmpty()) {
            extraAct.setError("Activities and societies information is required");
            extraAct.requestFocus();
            return true;
        }
        if (discription.isEmpty()) {
            discription1.setError("Description of Study is required");
            discription1.requestFocus();
            return true;
        }
        return false;
    }

    private void UpdateEducation() {

        String school = school1.getText().toString();
        String degree = degree1.getText().toString();
        String fieldOfStudy = fieldOfStudy1.getText().toString();
        String startDate = startdate.getText().toString();
        String endDate = enddate.getText().toString();
        String grade = grades.getText().toString();
        String extraActs = extraAct.getText().toString();
        String discription = discription1.getText().toString();

        if (!hasValidationErrors(school, degree, fieldOfStudy, startDate, endDate, grade, extraActs, discription)) {

           // CollectionReference reference = db.collection("Education");

            pojoAddNewEducation pojoaddNewEducation = new pojoAddNewEducation(school, degree, fieldOfStudy, startDate, endDate, grade, extraActs, discription);
            db.collection("Education").document(pojoAddNewEducation.getId()).update("school",pojoaddNewEducation.getSchool(), "degree",pojoaddNewEducation.getDegree(),
                    "fieldOfStudy", pojoaddNewEducation.getFieldOfStudy(), "startDate", pojoaddNewEducation.getStartDate(), "endDate",pojoaddNewEducation.getEndDate(), "grade",
                    pojoaddNewEducation.getGrade(), "extraActs", pojoaddNewEducation.getExtraActs(), "description", pojoaddNewEducation.getdescription())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void oid) {
                            Toast.makeText(EditEducation.this, "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(EditEducation.this, MainActivity.class));
                        }
                    });

        }

    }


    private void deleteEducation() {
        db.collection("Education").document(pojoAddNewEducation.getId()).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(EditEducation.this, "Education deleted", Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(EditEducation.this, MainActivity.class));
                        }
                    }
                });


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_update:
                UpdateEducation();
                break;
            case R.id.delete:


                AlertDialog.Builder builder = new AlertDialog.Builder(EditEducation.this);
                builder.setTitle("Are you sure about this?");
                builder.setMessage("Deletion is permanent...");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteEducation();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
                break;


        }
    }
}







