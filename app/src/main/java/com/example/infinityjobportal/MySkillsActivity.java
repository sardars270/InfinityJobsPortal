package com.example.infinityjobportal;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MySkillsActivity extends AppCompatActivity {

    String userId = "1234";
    FirebaseFirestore firebaseFirestore;
    private List<MySkill> skillList;
    MySkillAdapter adapter;
    RecyclerView recyclerView;
    String TAG = "SkillActivity";

    AppCompatButton btnAdd;
    FirebaseAuth mAuth;
    String email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_skills);
        mAuth = FirebaseAuth.getInstance();
        email = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();
        firebaseFirestore = FirebaseFirestore.getInstance();

        skillList = new ArrayList<>();
        // setup recycler view
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new MySkillAdapter(this, skillList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapter);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MySkillsActivity.this, AddSkillsActivity.class);
                startActivity(i);
            }
        });


    }


    private void getSkills() {
        skillList.clear();
        firebaseFirestore.collection("myskills").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if (documentSnapshots.isEmpty()) {
                            Log.d(TAG, "onSuccess: LIST EMPTY");
                            return;
                        } else {
                            // Convert the whole Query Snapshot to a list
                            // of objects directly! No need to fetch each
                            // document.
                            List<MySkill> skills = documentSnapshots.toObjects(MySkill.class);

                            for (int i = 0; i < skills.size(); i++) {
                                if (TextUtils.equals(skills.get(i).getUserId(), userId)) {
                                    MySkill mySkill = new MySkill(skills.get(i).getId(), skills.get(i).getName(),
                                            skills.get(i).getSkillId(), skills.get(i).getUserId());
                                    skillList.add(mySkill);
                                }


                            }


                            adapter.notifyDataSetChanged();
                        }
                    }


                });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null)
            getSkills();
    }
}