package com.example.infinityjobportal.ui.Queries;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infinityjobportal.R;
import com.example.infinityjobportal.model.Query;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

/**

 */
public class QueriesFragment extends Fragment {

    FirebaseAuth mAuth;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private Button send;
    private EditText feedbackQuery1;
    private EditText editsubject1;
    CollectionReference reference =db.collection("Query");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_queries, container, false);

        send=root.findViewById(R.id.btnsend);
        editsubject1=root.findViewById(R.id.editsubject);
        feedbackQuery1= root.findViewById(R.id.editcustomerfeedback);
        mAuth=  FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String editSubject=editsubject1.getText().toString();
                String feedbackQuery= feedbackQuery1.getText().toString();
                String userId=mAuth.getCurrentUser().getEmail();
                CollectionReference reference = db.collection("Query");

                Query Query = new Query(editSubject,feedbackQuery,userId);
                reference.add(Query).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }

        });
        return root;
    }
}