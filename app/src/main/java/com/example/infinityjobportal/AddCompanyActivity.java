package com.example.infinityjobportal;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.app.ActivityCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class AddCompanyActivity extends AppCompatActivity {

    AppCompatEditText tvCompanyName, tvLocation, tvLine1, tvLine2, tvCity, tvState, tvCountry, tvAbout, tvDesc, tvWeb, tvEmail, tvContact;
    Spinner spnIndustry;
    AppCompatButton btnSubmit;
    ImageView ivLogo;
    AppCompatImageView ivCamera;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;

    String userId = "1234";

    FirebaseStorage storage;
    StorageReference storageReference;

    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 10;
    private final int PICK_IMAGE_PERMISSION = 100;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_company);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Add Company");
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();

        ivLogo = findViewById(R.id.ivLogo);
     //   ivCamera = findViewById(R.id.ivCamera);

        tvCompanyName = findViewById(R.id.tvCompanyName);
        tvLocation = findViewById(R.id.tvLocation);
        tvLine1 = findViewById(R.id.tvLine1);
        tvLine2 = findViewById(R.id.tvLine2);
        tvCity = findViewById(R.id.tvCity);
        tvState = findViewById(R.id.tvState);
        tvCountry = findViewById(R.id.tvCountry);
        tvAbout = findViewById(R.id.tvAbout);
        tvDesc = findViewById(R.id.tvDesc);
        tvWeb = findViewById(R.id.tvWeb);
        tvEmail = findViewById(R.id.tvEmail);
        tvContact = findViewById(R.id.tvContact);
        spnIndustry = findViewById(R.id.tvIndustry);

        btnSubmit = findViewById(R.id.btnSubmit);

        ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(AddCompanyActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddCompanyActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PICK_IMAGE_PERMISSION);
                } else {
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
                }
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.Bounce)
                        .duration(700)
                        .repeat(2)
                        .playOn(btnSubmit);
                String companyName = tvCompanyName.getText().toString();
                String location = tvLocation.getText().toString();
                String line1 = tvLine1.getText().toString();
                String line2 = tvLine2.getText().toString();
                String city = tvCity.getText().toString();
                String state = tvState.getText().toString();
                String country = tvCountry.getText().toString();
                String about = tvAbout.getText().toString();
                String desc = tvDesc.getText().toString();
                String web = tvWeb.getText().toString();
                String email = tvEmail.getText().toString();
                String contact = tvContact.getText().toString();


                if (TextUtils.isEmpty(companyName)) {



                    tvCompanyName.setError("Invalid");
                    return;
                }

                if (TextUtils.isEmpty(location)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvLocation);
                    tvLocation.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(line1)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvLine1);
                    tvLine1.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(line2)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvLine2);
                    tvLine2.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(city)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvCity);
                    tvCity.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(state)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvState);
                    tvState.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(country)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvCountry);
                    tvCountry.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(about)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvAbout);
                    tvAbout.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(desc)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvDesc);
                    tvDesc.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(web)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvWeb);
                    tvWeb.setError("Invalid");
                    return;
                }
                if (TextUtils.isEmpty(email)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvEmail);
                    tvEmail.setError("Invalid");
                    return;
                }

                if (TextUtils.isEmpty(contact)) {
                    YoYo.with(Techniques.Shake)
                            .duration(700)
                            .repeat(2)
                            .playOn(tvContact);
                    tvContact.setError("Invalid");
                    return;
                }
                Log.e("here", "here");
                String id = firebaseFirestore.collection("mycompanies").document().getId();

                Map<String, Object> order = new HashMap<>();
                order.put("id", id);
                order.put("name", companyName);
                order.put("location", location);
                order.put("line1", line1);
                order.put("line2", line2);
                order.put("city", city);
                order.put("state", state);
                order.put("country", country);
                order.put("about", about);
                order.put("desc", desc);
                order.put("web", web);
                order.put("email", email);
                order.put("contact", contact);
                order.put("userId", mAuth.getCurrentUser().getEmail());
                order.put("industry", spnIndustry.getSelectedItem().toString());


                firebaseFirestore.collection("mycompanies").document(id)
                        .set(order)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddCompanyActivity.this, "Company Added Successfully", Toast.LENGTH_SHORT).show();

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });


            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivLogo.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PICK_IMAGE_PERMISSION && requestCode == RESULT_OK) {
            if (resultCode == PackageManager.PERMISSION_GRANTED) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            } else {
                //do something like displaying a message that he didn`t allow the app to access gallery and you wont be able to let him select from gallery
            }
        }
    }

    private void uploadImage() {

        if (filePath != null) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/" + UUID.randomUUID().toString());
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCompanyActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            Log.e("path", taskSnapshot.getUploadSessionUri().getPath());
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCompanyActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded " + (int) progress + "%");
                        }
                    });
        }
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
