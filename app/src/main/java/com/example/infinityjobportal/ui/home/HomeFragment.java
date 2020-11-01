package com.example.infinityjobportal.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infinityjobportal.ClientLogin;
import com.example.infinityjobportal.Jobs_search;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.adapter.Adapterjoblist;
import com.example.infinityjobportal.model.*;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView recjoblist;
    TextView search, text;

    private ArrayList<PostJobPojo> list=new ArrayList<PostJobPojo>();

    ArrayList<String> saveIdList = new ArrayList<>();
    Adapterjoblist adapter;
    FirebaseFirestore db;
    CollectionReference collectionReference;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        recjoblist=root.findViewById(R.id.recJobList);
        text=root.findViewById(R.id.text);

        db = FirebaseFirestore.getInstance();



        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadMyJobsList();
            }
        });
       // loadMyJobsList();

         collectionReference = db.collection("Jobs");




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



        adapter =new Adapterjoblist(getContext(), list);

        recjoblist.setHasFixedSize(true);
        recjoblist.setLayoutManager(new LinearLayoutManager(getContext()));
        recjoblist.setAdapter(adapter);



        return root;
    }

    private void loadMyJobsList() {
        db.collection("MyJobs").whereEqualTo("type","application").get()
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





        list.clear();

         collectionReference.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                if (!queryDocumentSnapshots.isEmpty()) {

                    List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                    for (DocumentSnapshot d : list1) {

                        for(int i=0; i<saveIdList.size(); i++) {
                            if(d.getId().equals(saveIdList.get(i))) {
                                PostJobPojo p = d.toObject(PostJobPojo.class);
                                p.setJobTitle(d.getString("jobTitle"));
                                p.setCompanyName(d.getString("companyName"));
                                p.setCityAddress(d.getString("cityAddress"));
                                p.setId(d.getId());

                                list.add(p);
                            }
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });





        for(int i=0; i<saveIdList.size(); i++) {
             // text.setText(saveIdList.get(i));
              Toast.makeText(getContext(), saveIdList.get(i), Toast.LENGTH_SHORT).show();
          }



    }


}