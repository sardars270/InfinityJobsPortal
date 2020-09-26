package com.example.myprofileeducation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerViewEducation;
    private NewEducationAdapter adapter;
    private List<pojoAddNewEducation> educationList;
    // private FirestoreRecyclerAdapter adapter;
    ImageView addEducation;

//private CollectionReference reference =db.collection("Education");
    //private AddNewEducationAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addEducation = findViewById(R.id.addEducation);
        recyclerViewEducation = findViewById(R.id.recyclerEducation);
        recyclerViewEducation.setHasFixedSize(true);
        recyclerViewEducation.setLayoutManager(new LinearLayoutManager(this));

        educationList=new ArrayList<>();
        adapter=new NewEducationAdapter(this, educationList);
        recyclerViewEducation.setAdapter(adapter);
        db.collection("Education").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(!queryDocumentSnapshots.isEmpty())
                {
                    List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
                    for(DocumentSnapshot d: list){

                        pojoAddNewEducation ed= d.toObject(pojoAddNewEducation.class);
                        ed.setId(d.getId());
                        educationList.add(ed);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });












        addEducation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNewEducation.class);
                startActivity(intent);
            }
        });






    }






}


















/*
        Query query = db.collection("Education");

        FirestoreRecyclerOptions<pojoAddNewEducation> options = new FirestoreRecyclerOptions.Builder<pojoAddNewEducation>().setQuery(query, pojoAddNewEducation.class).build();


        adapter = new FirestoreRecyclerAdapter<pojoAddNewEducation, EducationViewHolder>(options) {
            @NonNull
            @Override
            public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item_recyclerview, parent, false);
                return new EducationViewHolder(view);


            }

            @Override
            protected void onBindViewHolder(@NonNull EducationViewHolder holder, int position, @NonNull com.example.myprofileeducation.pojoAddNewEducation model) {
                holder.school.setText(model.getSchool());
                holder.details.setText(model.getDegree() + model.getFieldOfStudy() + model.getStartDate() + model.getEndDate());
                holder.grade.setText(model.getGrade());
                holder.extraacts.setText(model.getExtraActs());
                holder.description.setText(model.getdescription());

            }

        };

        recyclerViewEducation.setHasFixedSize(true);
        recyclerViewEducation.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewEducation.setAdapter(adapter);
        //   setUpRecyclerView();
    }

    private class EducationViewHolder extends RecyclerView.ViewHolder {

        private TextView school;
        private TextView details;
        private TextView grade;
        private TextView extraacts;
        private TextView description;


        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            school = itemView.findViewById(R.id.institution_name);
            details = itemView.findViewById(R.id.details_0f_education);
            grade = itemView.findViewById(R.id.txtgrade);
            extraacts = itemView.findViewById(R.id.txtextraAct);
            description = itemView.findViewById(R.id.txtdescription);
        }
    }

    //private void  setUpRecyclerView() {
    // Query query=reference;
    // recyclerViewEducation = findViewById(R.id.recyclerEducation);
    //FirestoreRecyclerOptions<pojoAddNewEducation> options=new FirestoreRecyclerOptions.Builder<pojoAddNewEducation>().setQuery(query,pojoAddNewEducation.class).build();
    // adapter=new AddNewEducationAdapter(options);
    // recyclerViewEducation.setHasFixedSize(true);
    //recyclerViewEducation.setLayoutManager(new LinearLayoutManager(this));
    // recyclerViewEducation.setAdapter(adapter);


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (adapter != null) {
            adapter.stopListening();
        }


    }
}

 */