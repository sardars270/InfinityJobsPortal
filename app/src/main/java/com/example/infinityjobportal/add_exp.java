package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class add_exp extends AppCompatActivity {
 EditText et_designation,et_institute,et_start_date,et_end_date;
 ImageView img;

 Button pickdate,spickdate;
    int mYear, mMonth, mDay;
 TextView faltu;
 Button submit;
    private Uri filePath;
    private static final int PICK_IMAGE_REQUEST = 71;
    FirebaseFirestore db;
    StorageReference mstorageRef;
    private ByteArrayOutputStream byteArrayOutputStream;
    private byte[] imageinbtye;
    String encodedImage;
    //String encodedImage;
    private Bitmap imagetostore;
    String a;
    String b;
    String userpic;
    public Uri imguri;
    FirebaseAuth mAuth;
    private StorageTask uploadTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exp);
        et_designation = findViewById(R.id.et_designation);
        et_institute = findViewById(R.id.et_institute);
        et_start_date = findViewById(R.id.et_start_date);
        et_end_date = findViewById(R.id.et_end_date);
        pickdate = findViewById(R.id.pickdate);
        spickdate = findViewById(R.id.spickdate);
        faltu = findViewById(R.id.faltu);
        submit = findViewById(R.id.submit);
        img = findViewById(R.id.img);
        db = FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

        final global_vars globalVariable = (global_vars) faltu_context.context;
       spickdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(add_exp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_start_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

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


                DatePickerDialog datePickerDialog = new DatePickerDialog(add_exp.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                et_end_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "submit called..", Toast.LENGTH_SHORT).show();

                Map<String, Object> data = new HashMap<>();
                data.put("a", "extra");
                data.put("designation", et_designation.getText().toString());
                data.put("institute", et_institute.getText().toString());
                data.put("startdate", et_start_date.getText().toString());
                data.put("enddate", et_end_date.getText().toString());
                data.put("userId", String.valueOf(mAuth.getCurrentUser().getEmail()));
                DocumentReference documentReference = db.collection("LOE").document();
                data.put("id", String.valueOf(documentReference.getId()));
                documentReference.set(data);
                Toast.makeText(getApplicationContext(),"LOE added ",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),ListOfExperienceActiviy.class);
                startActivity(i);

             /* final LOEModel loe =new LOEModel();

                loe.setDesignation(et_designation.getText().toString());
                loe.setInstitute(et_institute.getText().toString());
                loe.setStartdate(et_start_date.getText().toString());
                loe.setEnddate(et_end_date.getText().toString());
                loe.setUserId(mAuth.getCurrentUser().getEmail());





               db.collection("LOE").add(loe)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                loe.setId(documentReference.getId());
                                Toast.makeText(getApplicationContext(),"LOE added successfull",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(),ListOfExperienceActiviy.class);
                               startActivity(i);
                            }


                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

                    }
                })
                ;*/

            }
        });




        }






}