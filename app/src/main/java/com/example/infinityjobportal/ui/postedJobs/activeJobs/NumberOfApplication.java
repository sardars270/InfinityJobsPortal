package com.example.infinityjobportal.ui.postedJobs.activeJobs;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.PostJobPojo;
import com.example.infinityjobportal.model.User;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class NumberOfApplication extends Fragment {
    private static final String TAG = "NumberOfApplication";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference jobsReference = db.collection("Jobs");

    NumberOfApplicationsAdapter numberOfApplicationsAdapter;

    RecyclerView recyclerView;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called");
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_number_of_application, container, false);
        setUpRecyclerView();
        return view;
    }

    private void setUpRecyclerView() {
        Log.d(TAG, "setUpRecyclerView: called");

        //Query
        Query query = jobsReference.whereEqualTo("status", "active");

        //Recycler Options
        FirestoreRecyclerOptions<User> options = new FirestoreRecyclerOptions.Builder<User>()
                .setQuery(query, User.class)
                .build();
        numberOfApplicationsAdapter = new NumberOfApplicationsAdapter(options);

        recyclerView = view.findViewById(R.id.numberOfApplicationsRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(numberOfApplicationsAdapter);


    }

    @Override
    public void onStart() {
        super.onStart();
        numberOfApplicationsAdapter.startListening();

    }

    @Override
    public void onStop() {
        super.onStop();
        numberOfApplicationsAdapter.stopListening();
    }
}