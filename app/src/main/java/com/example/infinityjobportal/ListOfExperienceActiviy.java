package com.example.infinityjobportal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.infinityjobportal.Adapters.LOEAdapter;
import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class ListOfExperienceActiviy extends AppCompatActivity {
 TextView plus;
 RecyclerView rec;
 LOEAdapter loeAdapter;

   ArrayList<LOEModel> list=new ArrayList<>();
 FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_experience_activiy);
        plus=findViewById(R.id.plus);
        rec=findViewById(R.id.rec);
        db = FirebaseFirestore.getInstance();

       db.collection("LOE").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {


                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                LOEModel p = d.toObject(LOEModel.class);
                                p.setCompanyName(d.getString("companyName"));
                                p.setTitle(d.getString("title"));

                              p.setImg(d.getString("img"));
                                p.setId(d.getId());
                                list.add(p);
                            }
                            loeAdapter.notifyDataSetChanged();
                        }
                    }
                });




        loeAdapter =new LOEAdapter(list, this, "af");

        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        rec.setAdapter(loeAdapter);

    }


    public void AddExp(View view) {
        Intent i = new Intent(getApplicationContext(),add_exp.class);
        startActivity(i);
    }




}