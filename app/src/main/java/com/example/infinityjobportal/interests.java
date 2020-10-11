package com.example.infinityjobportal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infinityjobportal.Adapters.InterestsAdapter;
import com.example.infinityjobportal.Adapters.LOEAdapter;
import com.example.infinityjobportal.model.InterestsModel;
import com.example.infinityjobportal.model.LOEModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class interests extends AppCompatActivity {
EditText ed_interests;
 Button bt_add;
    FirebaseFirestore db;
    StorageReference mstorageRef;
    RecyclerView rec;
    InterestsAdapter InteAdapter;
    Context c;

    ArrayList<InterestsModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        ed_interests = findViewById(R.id.ed_interests);
        bt_add = findViewById(R.id.bt_add);
        rec=findViewById(R.id.rec);

        db = FirebaseFirestore.getInstance();
      //  mstorageRef = FirebaseStorage.getInstance().getReference("Images");
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterestsModel i =new InterestsModel();

                i.setInterests(ed_interests.getText().toString());
                i.setFaltu("extra");
                i.setId("0");
                db.collection("Interests").add(i)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(getApplicationContext(),"Interests added successfull",Toast.LENGTH_SHORT).show();
                                Intent ii = new Intent(getApplicationContext(),interests.class);
                                startActivity(ii);
                            }


                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

                    }
                })
                ;
                //View krvana



            }

        });
        db.collection("Interests").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {


                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                InterestsModel p = d.toObject(InterestsModel.class);
                                p.setInterests(d.getString("interests"));

                                list.add(p);
                            }
                            InteAdapter.notifyDataSetChanged();
                        }
                    }
                });
        InteAdapter =new InterestsAdapter(list, c, "af");

        rec.setHasFixedSize(true);
        rec.setLayoutManager(new LinearLayoutManager(c,RecyclerView.VERTICAL,false));
        rec.setAdapter(InteAdapter);


    }
}