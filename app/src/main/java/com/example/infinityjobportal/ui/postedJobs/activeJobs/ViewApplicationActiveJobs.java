package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewApplicationActiveJobs extends AppCompatActivity {
    String id;
    private FirebaseFirestore db;
    ArrayList<String> candidateList = new ArrayList<>();

    ViewApplicationAdapter viewApplicationAdapter;
    RecyclerView viewApplicationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_application_active_jobs);
        viewApplicationsRecyclerView = findViewById(R.id.applicationsRecyclerView);
        id = getIntent().getStringExtra("id");
//      Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        db = FirebaseFirestore.getInstance();

        db.collection("MyJobs").whereEqualTo("jobId", id).whereEqualTo("type", "application")
                .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list1) {


                        candidateList.add(d.getString("uid"));
                    }

                    viewApplicationData();
                }
            }
        });

        viewApplicationAdapter = new ViewApplicationAdapter();

        viewApplicationsRecyclerView.setHasFixedSize(true);
        viewApplicationsRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        viewApplicationsRecyclerView.setAdapter(viewApplicationAdapter);


    }

    private void viewApplicationData() {
        for (int i = 0; i < candidateList.size(); i++) {
            db.collection("users").document(candidateList.get(i)).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.exists()) {

                        User user = documentSnapshot.toObject(User.class);
                        Toast.makeText(ViewApplicationActiveJobs.this, user.getFirstName(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}