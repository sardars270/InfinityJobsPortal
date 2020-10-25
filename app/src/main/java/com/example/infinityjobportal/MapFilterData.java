package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import com.example.infinityjobportal.model.PostJobPOJO;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;


public class MapFilterData extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    int i;
    private MapFilterDataAdapter adapter;
    RecyclerView recyclerViewMapFilteredJobs;
    private ArrayList<PostJobPOJO> MapJobsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_filter_data);
        recyclerViewMapFilteredJobs = findViewById(R.id.rec_mapfilteredjobs);
        MapJobsList = new ArrayList<>();


        for (int i = 0; i < GlobalStorage.S.size(); i=i+2) {
            DocumentReference docRef = db.collection("Jobs").document(GlobalStorage.S.get(i));
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {

                            PostJobPOJO postJobPOJO= document.toObject(PostJobPOJO.class);


                            MapJobsList.add(postJobPOJO);
                            adapter.notifyDataSetChanged();


                        } else {

                        }


                    } else {

                    }

                }
            });

        }


        adapter = new MapFilterDataAdapter(this, MapJobsList);
        recyclerViewMapFilteredJobs.setHasFixedSize(true);
        recyclerViewMapFilteredJobs.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMapFilteredJobs.setAdapter(adapter);


    }
}


