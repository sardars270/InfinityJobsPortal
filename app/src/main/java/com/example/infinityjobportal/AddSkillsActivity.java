package com.example.infinityjobportal;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.adapter.AddSkillAdapter;
import com.example.infinityjobportal.adapter.MySkillAdapter;
import com.example.infinityjobportal.model.MySkill;
import com.example.infinityjobportal.model.Skill;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSkillsActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth mAuth;

    private List<MySkill> skillList;
    AddSkillAdapter adapterSkill;
    RecyclerView recyclerView;
    AppCompatEditText etSkill;
    ImageView back;
    Button btnAdd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_skills);
        firebaseFirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        skillList = new ArrayList<>();
        // setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapterSkill = new AddSkillAdapter(this, skillList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterSkill);

        etSkill = findViewById(R.id.etSkill);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
                String id = firebaseFirestore.collection("myskills").document().getId();

                Map<String, Object> item = new HashMap<>();
                item.put("id", id);
                item.put("skillId", id);
                item.put("name", etSkill.getText().toString());
                item.put("userId", mAuth.getCurrentUser().getUid());

                firebaseFirestore.collection("myskills").document(id)
                        .set(item)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {

                                Toast.makeText(AddSkillsActivity.this, "Skill Added Successfully", Toast.LENGTH_SHORT).show();
                                getSkills();
                                etSkill.setText("");

                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
            }
        });


        getSkills();


    }

    private void getSkills() {
        skillList.clear();
        firebaseFirestore.collection("myskills").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {

                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<MySkill> skills = documentSnapshots.toObjects(MySkill.class);
                            Log.e("Size", "" + skills.size());
                            for (int i = 0; i < skills.size(); i++) {
                                if (TextUtils.equals(skills.get(i).getUserId(), mAuth.getUid())) {
                                    Log.e("Size", "" + skills.size() + " " + skills.get(i).getName());
                                    MySkill mySkill = new MySkill(skills.get(i).getId(), skills.get(i).getName(),
                                            skills.get(i).getSkillId(), skills.get(i).getUserId());
                                    skillList.add(mySkill);
                                }


                            }


                            adapterSkill.notifyDataSetChanged();
                        }
                    }


                });


    }
}