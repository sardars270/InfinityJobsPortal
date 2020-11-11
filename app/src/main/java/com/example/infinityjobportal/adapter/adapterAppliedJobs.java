package com.example.infinityjobportal.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infinityjobportal.EditEducation;
import com.example.infinityjobportal.JobDetails;
import com.example.infinityjobportal.MainEducation;
import com.example.infinityjobportal.MyJobDetails;
import com.example.infinityjobportal.R;
import com.example.infinityjobportal.interests;
import com.example.infinityjobportal.model.PostJobPojo;
import com.example.infinityjobportal.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class adapterAppliedJobs extends RecyclerView.Adapter<adapterAppliedJobs.ViewHolder>{
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    String rr="";
    Context context;
    public FirebaseAuth mAuth;
    ArrayList<PostJobPojo> ar1;

    public adapterAppliedJobs(Context context, ArrayList<PostJobPojo> ar1) {

        this.context=context;
        this.ar1=ar1;
    }

    @NonNull
    @Override
    public adapterAppliedJobs.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_applied_job, parent, false);

        adapterAppliedJobs.ViewHolder holder=new adapterAppliedJobs.ViewHolder(listItem);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull final adapterAppliedJobs.ViewHolder holder, int position) {

        PostJobPojo pj=ar1.get(position);

        holder.title.setText(pj.getJobTitle());
        holder.at.setText(pj.getCompanyName());
        holder.location.setText(pj.getCityAddress());
        holder.language.setText(pj.getLanguage());
        holder.category.setText(pj.getJobCategory());
        holder.salary.setText("$"+pj.getMinSalary()+" - $"+ pj.getMaxSalary());
        holder.id.setText(pj.getId());
        holder.faltu_st.setText(pj.getProvinceAddress());


        holder.lout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, MyJobDetails.class);
                String s =  holder.faltu_st.getText().toString();
                i.putExtra("id", holder.id.getText().toString());
                i.putExtra("status",s );
                context.startActivity(i);
            }
        });

    }
    private void delsave() {
        db.collection("MyJobs").document(rr).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, rr +"deleted", Toast.LENGTH_LONG).show();
                            //  finish();
                            // startActivity(new Intent(EditEducation.this, MainEducation.class));
                        }
                    }
                });


    }

    @Override
    public int getItemCount() {
        return ar1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        LinearLayout lout;

        TextView title, at, location,id, faltu_st, category, language, salary;
        ImageView saveJob;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title=itemView.findViewById(R.id.title);
            at=itemView.findViewById(R.id.at);
            location=itemView.findViewById(R.id.location);

            lout=itemView.findViewById(R.id.lout);
            saveJob  = itemView.findViewById(R.id.saveJob);
            id  = itemView.findViewById(R.id.id);
            faltu_st  = itemView.findViewById(R.id.faltu_st);
            language  = itemView.findViewById(R.id.language);
            salary  = itemView.findViewById(R.id.salary);
            category  = itemView.findViewById(R.id.category);


        }
    }
}


