package com.example.infinityjobportal.ui.myProfile;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.infinityjobportal.ClientChangePassword;
import com.example.infinityjobportal.EditNameSection;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.UpdateAbout;
import com.example.infinityjobportal.UpdateContactSection;
import com.example.infinityjobportal.UpdateUserPic;
import com.example.infinityjobportal.model.User;
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

public class MyProfile extends Fragment {
ImageView userPic, editNameSection, editAboutSection, editExperienceSection, editEducationSection, editInterestSection, editSkillsSection, editContcatSection;
TextView name, tagLine, location, about,email, number,website, address;
String websiteUrl="";
LinearLayout changePassword;

FirebaseAuth mAuth;
FirebaseFirestore db;

    public MyProfile() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View root  = inflater.inflate(R.layout.fragment_my_profile, container, false);
        userPic = root.findViewById(R.id.userPic);
        name  = root.findViewById(R.id.name);
        tagLine  = root.findViewById(R.id.tagLine);
        location = root.findViewById(R.id.location);
        about = root.findViewById(R.id.about);
        email = root.findViewById(R.id.email);
        number = root.findViewById(R.id.number);
        website = root.findViewById(R.id.website);
        address = root.findViewById(R.id.address);
        editNameSection = root.findViewById(R.id.editNameSection);
        editAboutSection = root.findViewById(R.id.editAboutSection);
        editEducationSection = root.findViewById(R.id.editEducationSection);
        editExperienceSection = root.findViewById(R.id.editExperienceSection);
        editSkillsSection = root.findViewById(R.id.editSkillsSection);
        editInterestSection = root.findViewById(R.id.editInterestsection);
        editContcatSection = root.findViewById(R.id.editContactInfo);
        changePassword = root.findViewById(R.id.changePassword);


        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        DocumentReference docRef = db.collection("users").document(mAuth.getCurrentUser().getEmail());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {

                        User user = document.toObject(User.class);
                        email.setText(user.getEmail());

                        name.setText(user.getFirstName().substring(0, 1).toUpperCase()+user.getFirstName().substring(1)+ " "+ user.getLastName().substring(0, 1).toUpperCase()+user.getLastName().substring(1));
                        number.setText(user.getNumber());

                        if(user.getTagLine().equals(""))
                        {
                            tagLine.setText("Add your tag line.");
                        }
                        else
                        {
                            tagLine.setText(user.getTagLine());
                        }


                        if(user.getAbout().equals(""))
                        {
                            about.setText("Add your about information.");
                        }
                        else
                        {
                            about.setText(user.getAbout());
                        }

                        if(user.getWebsite().equals(""))
                        {
                            website.setText("Add your website.");
                            websiteUrl="";
                            website.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                        }
                        else
                        {
                            website.setText(user.getWebsite());
                            websiteUrl=user.getWebsite();
                            website.setTextColor(ContextCompat.getColor(getContext(), R.color.mainSecondaryColor));
                        }

                        if(user.getCity().equals("") ||  user.getProvince().equals("")|| user.getCountry().equals(""))
                        {
                            location.setText("Add your location for better experience.");
                            address.setText("Add your Address");

                        }
                        else
                        {
                            location.setText(user.getCity().substring(0, 1).toUpperCase()+user.getCity().substring(1)+ ", " +  user.getProvince().substring(0, 1).toUpperCase()+user.getProvince().substring(1)+", "+ user.getCountry().substring(0, 1).toUpperCase()+user.getCountry().substring(1));
                            address.setText(user.getApartment()+"-"+user.getBuilding()+", "+user.getStreet().substring(0, 1).toUpperCase()+user.getStreet().substring(1)+", "+user.getCity().substring(0, 1).toUpperCase()+user.getCity().substring(1)+", "+user.getProvince().substring(0, 1).toUpperCase()+user.getProvince().substring(1)+", "+user.getCountry().substring(0, 1).toUpperCase()+user.getCountry().substring(1)+", "+user.getZipCode());
                        }

                        FirebaseStorage firebaseStorage;
                        StorageReference storageReference;

                        firebaseStorage = FirebaseStorage.getInstance();
                        storageReference = firebaseStorage.getReference();

//        StorageReference imageRef = storageReference.child("Images/my.png");
                        StorageReference imageRef = storageReference.child("user/"+user.getUserProfilePic());

                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                Glide.with(getContext())
                                        .load(uri)
                                        .circleCrop()
                                        .into(userPic);

                                //Picasso.get().load(uri).into(limg);

                                // Toast.makeText(getContext(),"Success.",Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Profile Pic is not available",Toast.LENGTH_SHORT).show();
                            }
                        });





                    } else {

                    }
                } else {

                }
            }
        });


        userPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), UpdateUserPic.class);
              startActivity(i);
            }
        });



        editNameSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), EditNameSection.class));
            }
        });

        editAboutSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UpdateAbout.class));
            }
        });

        editContcatSection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), UpdateContactSection.class));
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!websiteUrl.equals("")){
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(websiteUrl));
                    startActivity(i);
                }

            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ClientChangePassword.class));
            }
        });



        return root;
    }
}

