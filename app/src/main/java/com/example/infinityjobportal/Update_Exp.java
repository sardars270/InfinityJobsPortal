package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinityjobportal.adapter.LOEAdapter;
import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Update_Exp extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText designation, institute, startdate, enddate;
    TextView faltu;
    LOEModel loeModel;
    String loeid;
    LOEAdapter adapter;
    int mYear, mMonth, mDay;
    LOEModel pro;
    ArrayList<LOEModel> list = new ArrayList<>();
    Button update, spickdate, pickdate;
    FirebaseFirestore db;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__exp);
        designation = findViewById(R.id.et_designation);
        institute = findViewById(R.id.et_institute);
        startdate = findViewById(R.id.et_start_date);
        enddate = findViewById(R.id.et_end_date);
        // recyclerView = findViewById(R.id.recycleview);
        update = findViewById(R.id.update);
        faltu = findViewById(R.id.faltu);
        pickdate = findViewById(R.id.pickdate);
        spickdate = findViewById(R.id.spickdate);
        loeid =  getIntent().getStringExtra("loeid");
        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();


        spickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Update_Exp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                startdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });
        pickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(Update_Exp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                enddate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });



      DocumentReference docRef = db.collection("LOE").document(loeid);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        LOEModel pro = document.toObject(LOEModel.class);
                        designation.setText(pro.getDesignation());
                        institute.setText(pro.getInstitute());
                        startdate.setText(pro.getStartdate());
                        enddate.setText(pro.getEnddate());
                        faltu.setText(pro.getId());
                        FirebaseStorage firebaseStorage;
                        //StorageReference storageReference;

                        firebaseStorage = FirebaseStorage.getInstance();
                        //storageReference = firebaseStorage.getReference();

                    } else {
                        // Log.d(TAG, "No such document");
                    }
                } else {
                    // Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });




       update.setOnClickListener(new View.OnClickListener()

    {
        @Override
        public void onClick (View v){

        DocumentReference docRef = db.collection("LOE").document(loeid);

// Update the timestamp field with the value from the server
        Map<String, Object> updates = new HashMap<>();
        updates.put("designation", designation.getText().toString());
        updates.put("institute", institute.getText().toString());
        updates.put("startdate", startdate.getText().toString());
        updates.put("enddate", enddate.getText().toString());

        docRef.update(updates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Data Updated...", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Update_Exp.this, ListOfExperienceActiviy.class));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Data Not Updated. Try Again..,", Toast.LENGTH_SHORT).show();

                    }
                });


    }
    });
}

    }
