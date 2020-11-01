package com.example.infinityjobportal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.adapter.Adapterjoblist;
import com.example.infinityjobportal.model.PostJobPojo;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView recjoblist;
    TextView search;

    private ArrayList<PostJobPojo> list = new ArrayList<PostJobPojo>();
    Adapterjoblist adapter;
    FirebaseFirestore db;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recjoblist = root.findViewById(R.id.recJobList);


        db = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = db.collection("Jobs");


        collectionReference.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                PostJobPojo p = d.toObject(PostJobPojo.class);
                                p.setJobTitle(d.getString("jobTitle"));
                                p.setCompanyName(d.getString("companyName"));
                                p.setCityAddress(d.getString("cityAddress"));
                                p.setId(d.getId());

                                list.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });


        adapter = new Adapterjoblist(getContext(), list);

        recjoblist.setHasFixedSize(true);
        recjoblist.setLayoutManager(new LinearLayoutManager(getContext()));
        recjoblist.setAdapter(adapter);


        return root;
    }


}