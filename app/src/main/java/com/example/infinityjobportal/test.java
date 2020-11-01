package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test extends AppCompatActivity {
Button button;

FirebaseAuth mAuth;

    ArrayList<String> saveIdList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mAuth = FirebaseAuth.getInstance();

button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseFirestore db = FirebaseFirestore.getInstance();

                db.collection("MyJobs").get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                                if (!queryDocumentSnapshots.isEmpty()) {

                                    List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                                    for (DocumentSnapshot d : list1) {

                                        // PostJobPojo p = d.toObject(PostJobPojo.class);
                                        //  p.setJobTitle(d.getString("jobTitle"));
                                        // p.setCompanyName(d.getString("companyName"));
                                        //p.setCityAddress(d.getString("cityAddress"));
                                        //p.setId(d.getId());

                                        saveIdList.add(d.getString("jobId"));
                                        // Toast.makeText(getContext(),d.getString("jobId"),Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(getContext(),saveIdList,Toast.LENGTH_SHORT).show();
                                    }
                                    //adapter.notifyDataSetChanged();
                                }
                            }
                        });



                for(int i=0; i<saveIdList.size(); i++) {
                  //  text.setText(saveIdList.get(i));
                    Toast.makeText(getApplicationContext(), saveIdList.get(i), Toast.LENGTH_SHORT).show();
                }











           }
        });
    }
}



/*
<resources>
    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
        <!-- Customize your theme here. -->
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="colorError">@color/colorAccent</item>
    </style>

    <style name="AppTheme.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
        <item name="colorPrimary">@color/colorPrimary</item>
        <item name="colorPrimaryDark">@color/colorPrimary</item>
        <item name="colorAccent">@color/colorAccent</item>
        <item name="colorError">@color/colorAccent</item>
    </style>

    <style name="AppTheme.AppBarOverlay" parent="ThemeOverlay.AppCompat.Dark.ActionBar" />

    <style name="AppTheme.PopupOverlay" parent="ThemeOverlay.AppCompat.Light" />

</resources>







string file
<resources>
    <string name="app_name">Infinity Jobs</string>
    <string name="title_activity_admin_main">MainActivity</string>
    <string name="title_home">Home</string>
    <string name="title_dashboard">Dashboard</string>
    <string name="title_notifications">Notifications</string>
    <string name="title_activity_main">MainActivity</string>
    <string name="navigation_drawer_open">Open navigation drawer</string>
    <string name="navigation_drawer_close">Close navigation drawer</string>
    <string name="nav_header_title">Android Studio</string>
    <string name="nav_header_subtitle">android.studio@android.com</string>
    <string name="nav_header_desc">Navigation header</string>
    <string name="action_settings">Settings</string>

    <string name="menu_home">Home</string>
    <string name="menu_gallery">Gallery</string>
    <string name="menu_slideshow">Slideshow</string>
    <!-- TODO: Remove or change this placeholder text -->
    <string name="hello_blank_fragment">Hello blank fragment</string>
</resources>

 */