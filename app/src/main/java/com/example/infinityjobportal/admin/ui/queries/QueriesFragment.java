package com.example.infinityjobportal.admin.ui.queries;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.adapter.adapterquerylist;
import com.example.infinityjobportal.adapter.adapteruserlist;
import com.example.infinityjobportal.admin.ViewQuery;
import com.example.infinityjobportal.model.Query;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class QueriesFragment extends Fragment {
    public String a="";
    public String s="", name;
    Button bt_add;
    // FirebaseFirestore db;
    StorageReference mstorageRef;
    RecyclerView recy;
    FirebaseAuth fbauth;
    adapterquerylist InteAdapter;
    Context c;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseFirestore db2 = FirebaseFirestore.getInstance();
    ArrayList<Query> list=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.admin_fragment_queries, container, false);
        recy = root.findViewById(R.id.recy);
        fbauth=FirebaseAuth.getInstance();
        ///////////////////////////fetch data for query
db2=FirebaseFirestore.getInstance();
        db = FirebaseFirestore.getInstance();
        db.collection("Query").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()) {
                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list1) {
                                Query p = d.toObject(Query.class);
                                assert p != null;
                                p.setEditSubject(d.getString("editSubject"));
                                p.setUserid(d.getString("userid"));
                                 list.add(p);
                            }
                            InteAdapter.notifyDataSetChanged();
                        }
                    }
                });
        InteAdapter =new adapterquerylist(list, getContext(), "af");

        recy.setHasFixedSize(true);
        recy.setLayoutManager(new LinearLayoutManager(c,RecyclerView.VERTICAL,false));
        recy.setAdapter(InteAdapter);
        return root;
    }
}