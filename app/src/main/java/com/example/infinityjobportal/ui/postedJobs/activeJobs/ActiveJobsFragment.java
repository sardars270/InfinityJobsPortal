package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.JobDetails;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ActiveJobsFragment extends Fragment {

    private static final String TAG = "ActiveJobsFragment";

    RecyclerView recyclerView;
    View view;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference jobsReference = db.collection("Jobs");

    private ActiveJobsAdapter activeJobsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_active_jobs, container, false);
        setUpRecyclerView();

        return view;
    }

    private void setUpRecyclerView() {
        Log.d(TAG, "setUpRecyclerView: called");

        //Query
        Query query = jobsReference.whereEqualTo("status", "active");

        //Recycler Options
        FirestoreRecyclerOptions<PostJobPojo> options = new FirestoreRecyclerOptions.Builder<PostJobPojo>()
                .setQuery(query, PostJobPojo.class)
                .build();
        activeJobsAdapter = new ActiveJobsAdapter(options);

        recyclerView = view.findViewById(R.id.active_posted_jobs_recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(activeJobsAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        activeJobsAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        activeJobsAdapter.stopListening();
    }
}