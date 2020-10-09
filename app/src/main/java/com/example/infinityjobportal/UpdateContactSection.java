package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UpdateContactSection extends AppCompatActivity {
    EditText website, number, country, province, city, street, building, apartment,zip;


    TextView save, email;
    ImageView back;
    ProgressBar progressBar;

    FirebaseAuth mAuth;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact_section);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        progressBar = findViewById(R.id.progressBar);
        website = findViewById(R.id.website);
        number = findViewById(R.id.number);
        email = findViewById(R.id.email);

        country = findViewById(R.id.country);
        province = findViewById(R.id.province);
        city = findViewById(R.id.city);
        street = findViewById(R.id.street);
        building = findViewById(R.id.building);
        apartment = findViewById(R.id.apartment);
        zip = findViewById(R.id.zip);



        mAuth= FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();

        loadInfo();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                //Toast.makeText(getApplicationContext(),"save called",Toast.LENGTH_SHORT).show();
                DocumentReference docRef = db.collection("users").document(mAuth.getCurrentUser().getEmail());

                Map<String,Object> updates = new HashMap<>();
                updates.put("website", website.getText().toString());
                updates.put("number", number.getText().toString());
                updates.put("apartment", apartment.getText().toString());
                updates.put("building", building.getText().toString());
                updates.put("street",street.getText().toString());
                updates.put("city", city.getText().toString());
                updates.put("province", province.getText().toString());
                updates.put("country", country.getText().toString());
                updates.put("zipCode", zip.getText().toString());



                docRef.update((Map<String, Object>) updates)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(getApplicationContext(),"Data Updated...",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Data Not Updated. Try Again..,",Toast.LENGTH_SHORT).show();

                                progressBar.setVisibility(View.GONE);
                            }
                        });
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }





    private void loadInfo() {
        DocumentReference docRef = db.collection("users").document(mAuth.getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        User user = document.toObject(User.class);
                        email.setText(user.getEmail());

                        if(user.getNumber().equals(""))
                        {
                            number.setHint("Add your phone number.");
                        }
                        else
                        {
                            number.setText(user.getNumber());
                        }

                        if(user.getWebsite().equals(""))
                        {
                            website.setHint("Add your website Link.");
                        }
                        else
                        {
                            website.setText(user.getWebsite());
                        }

                        if(user.getApartment().equals(""))
                        {
                            apartment.setHint("Add your Apartment Number.");

                        }
                        else
                        {
                            apartment.setText(user.getApartment());
                        }



                        if(user.getStreet().equals(""))
                        {
                            street.setHint("Add your Street.");

                        }
                        else
                        {
                            street.setText(user.getStreet());
                        }




                        if(user.getBuilding().equals(""))
                        {
                            building.setHint("Add your building Number");

                        }
                        else
                        {
                            building.setText(user.getBuilding());
                        }



                        if(user.getCity().equals(""))
                        {
                            city.setHint("Add your city.");

                        }
                        else
                        {
                            city.setText(user.getCity());
                        }


                        if(user.getProvince().equals(""))
                        {
                            province.setHint("Add your province");

                        }
                        else
                        {
                            province.setText( user.getProvince());
                        }

                        if(user.getCountry().equals(""))
                        {
                            country.setHint("Add your country.");

                        }
                        else
                        {
                            country.setText( user.getCountry());
                        }

                        if(user.getZipCode().equals(""))
                        {
                            zip.setHint("Add your ZipCode.");

                        }
                        else
                        {
                            zip.setText( user.getZipCode());
                        }

                    } else {

                    }
                } else {

                }
            }
        });

    }
}