package com.example.infinityjobportal;

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

import com.example.infinityjobportal.adapter.InterestsAdapter;
import com.example.infinityjobportal.model.InterestsModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class interests extends AppCompatActivity {
    EditText ed_interests;
    public String a="";
    Button bt_add;
    // FirebaseFirestore db;
    StorageReference mstorageRef;
    RecyclerView rec;
    FirebaseAuth fbauth;
    InterestsAdapter InteAdapter;
    Context c;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<InterestsModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        ed_interests = findViewById(R.id.ed_interests);
        bt_add = findViewById(R.id.bt_add);
        rec=findViewById(R.id.rec);
        fbauth=FirebaseAuth.getInstance();

        db = FirebaseFirestore.getInstance();
      //  mstorageRef = FirebaseStorage.getInstance().getReference("Images");
        bt_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


////////////code of insert

                Map<String, Object> data = new HashMap<>();
                data.put("faltu", "extra");
                data.put("type_int", ed_interests.getText().toString());
                data.put("userid", String.valueOf(fbauth.getCurrentUser().getEmail()));
                DocumentReference washingtonRef = db.collection("interest").document();
                data.put("id", String.valueOf(washingtonRef.getId()));
                washingtonRef.set(data);
                Toast.makeText(getApplicationContext(),"Interests added successfull",Toast.LENGTH_SHORT).show();
                Intent ii = new Intent(getApplicationContext(),interests.class);
                startActivity(ii);




            }

        });
        db.collection("interest").whereEqualTo("faltu","extra").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        if (!queryDocumentSnapshots.isEmpty()) {


                            List<DocumentSnapshot> list1 = queryDocumentSnapshots.getDocuments();

                            for (DocumentSnapshot d : list1) {

                                InterestsModel p = d.toObject(InterestsModel.class);
                                assert p != null;
                                p.setType_int(d.getString("type_int"));
                                p.setId(d.getString("id"));

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