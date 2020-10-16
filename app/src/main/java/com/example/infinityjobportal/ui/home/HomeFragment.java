package com.example.infinityjobportal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.adapter.Adapterjoblist;
import com.example.infinityjobportal.model.Jobs;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recjoblist;

    private ArrayList<Jobs> list=new ArrayList<>();
    Adapterjoblist adapter;
    FirebaseFirestore db;


    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recjoblist=root.findViewById(R.id.recJobList);


        db = FirebaseFirestore.getInstance();

        db.collection("Jobs").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {

                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {


                                Jobs p = d.toObject(Jobs.class);
                                p.setTitle(d.getString("Job title"));
                                p.setAt(d.getString("Company Name"));
                                p.setLocation(d.getString("City"));

                                list.add(p);
                            }
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

        adapter =new Adapterjoblist(getContext(), list);

        recjoblist.setHasFixedSize(true);
        recjoblist.setLayoutManager(new LinearLayoutManager(getContext()));
        recjoblist.setAdapter(adapter);




        return root;
    }

    
}